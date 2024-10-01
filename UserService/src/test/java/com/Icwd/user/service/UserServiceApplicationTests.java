package com.Icwd.user.service;

import com.Icwd.user.service.entities.Rating;
import com.Icwd.user.service.external_Feign.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client").build();
		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);

		System.out.println("new rating created");


	}

}
