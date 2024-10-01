package com.lcwd.rating.RatingService.repository;

import com.lcwd.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long>
{
    //custom finder methods
    List<Rating> findByUserId(Integer userId);

    List<Rating> findByHotelId(Long hotelId);
}
