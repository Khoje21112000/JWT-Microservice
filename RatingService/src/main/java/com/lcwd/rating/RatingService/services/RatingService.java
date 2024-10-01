package com.lcwd.rating.RatingService.services;

import com.lcwd.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService
{
    //create

    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingBYUserId(Integer userId);

    //get all BY hotel
    List<Rating> getRatingByHotelId(Long hotelId);
}
