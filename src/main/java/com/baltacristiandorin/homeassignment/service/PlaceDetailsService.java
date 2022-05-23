package com.baltacristiandorin.homeassignment.service;

import com.baltacristiandorin.homeassignment.entity.PlaceDetailsEntity;
import com.baltacristiandorin.homeassignment.repository.PlaceDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceDetailsService {

    private final PlaceDetailsRepository placeDetailsRepository;

    public PlaceDetailsService(PlaceDetailsRepository placeDetailsRepository) {
        this.placeDetailsRepository = placeDetailsRepository;
    }

    public PlaceDetailsEntity findById(String placeId) {
        return placeDetailsRepository.findById(placeId).orElseThrow();
    }

    public List<PlaceDetailsEntity> findAll() {
        return (List<PlaceDetailsEntity>) placeDetailsRepository.findAll();
    }

    public void save(PlaceDetailsEntity placeDetailsEntity) {
        placeDetailsRepository.save(placeDetailsEntity);
    }
}
