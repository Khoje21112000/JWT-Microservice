package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelServiceImpl implements HotelService

{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel with given id is not found on server !: " + id));
    }

    @Override
    public Boolean deleteByHotelId(Long hotelId) {
        if(hotelRepository.existsById(hotelId)){
            hotelRepository.deleteById(hotelId);
            return true;
        }
        else {
            return false;
        }
    }
}
