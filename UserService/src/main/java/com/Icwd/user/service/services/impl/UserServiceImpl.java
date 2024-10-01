package com.Icwd.user.service.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.Icwd.user.service.entities.Hotel;

import com.Icwd.user.service.entities.Rating;

import com.Icwd.user.service.external_Feign.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.exceptions.ResourceNotFoundException;
import com.Icwd.user.service.repositories.UserRepository;
import com.Icwd.user.service.services.UserService;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// generate unique user id

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUserId(Integer userId) {
		// Get user from database with the help of user repository
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !: " + userId));

		// Fetch ratings of the user from RATING SERVICE
		String url = "http://RATING-SERVICE/ratings/users/" + userId;
		Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);
		logger.info("Fetched ratings: {}", Arrays.toString(ratingsOfUser));

		List<Rating> ratings = Arrays.asList(ratingsOfUser);

		// Process each rating to fetch the associated hotel
		List<Rating> ratingList = ratings.stream().map(rating -> {
			String hotelUrl = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
			try {
//				ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(hotelUrl, Hotel.class);

				Hotel hotel=hotelService.getHotel(rating.getRatingId());

//				logger.info("Response status code for hotel: {}", hotelResponse.getStatusCode());
				rating.setHotel(hotel);
			} catch (ResourceNotFoundException e) {
				logger.warn("Hotel not found for id: {}", rating.getHotelId());
				// Optionally, set a default or null hotel if necessary
				rating.setHotel(null);
			}

			// Return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}


	public boolean deleteUser(Integer userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		} else {
			return false;
		}
	}

	public User findByEmail(String email) {
		// Retrieve the user by email from the database
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isEmpty()) {
			return null;
		}

		return user.orElse(null);
	}


	@Override
	public User findByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user == null) {
			throw new ResourceNotFoundException("User with given email and password is not found.");
		}
		return user;
	}


}
