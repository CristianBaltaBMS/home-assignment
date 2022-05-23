package com.baltacristiandorin.homeassignment.service;

import com.baltacristiandorin.homeassignment.dto.PlaceDetailsDto;

public interface PlacesService {

    PlaceDetailsDto getPlaceDetailsById(String requestId);
}
