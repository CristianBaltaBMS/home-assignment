package com.baltacristiandorin.homeassignment.controller;

import com.baltacristiandorin.homeassignment.dto.PlaceDetailsDto;
import com.baltacristiandorin.homeassignment.service.PlacesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(path = "api/v1/places")
public class PlacesControllerByRequest {

    private final PlacesService placesService;

    public PlacesControllerByRequest(@Qualifier(value = "byRequest") PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping(path = "/place/{placeId}")
    public ResponseEntity<PlaceDetailsDto> getPlaceDetailsById(@PathVariable String placeId) {
        try {
            log.info("Get place details by id request received for id: %s.".formatted(placeId));
            PlaceDetailsDto response = placesService.getPlaceDetailsById(placeId);
            log.info("Place details for id: %s found.".formatted(placeId));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //""
        }
    }
}
