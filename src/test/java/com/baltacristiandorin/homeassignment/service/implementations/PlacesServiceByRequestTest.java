package com.baltacristiandorin.homeassignment.service.implementations;

import com.baltacristiandorin.homeassignment.dto.PlaceDetailsDto;
import com.baltacristiandorin.homeassignment.service.PlaceDetailsService;
import com.baltacristiandorin.homeassignment.service.PlacesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlacesServiceByRequestTest {

    @Autowired
    @Qualifier("placesServiceObjectMapper")
    private ObjectMapper objectMapper;
    @Value("${service.url.places}")
    private String placesUrl;
    @Value("${service.timeout.connection}")
    private String connectionTimeout;
    @Value("${service.timeout.read}")
    private String readTimeout;
    @Autowired
    private PlaceDetailsService placeDetailsService;

    @Value("")
    private String PlaceDetailsJson;

    @Test
    void getPlaceDetailsById() {

        String goodRequestId = "ohGSnJtMIC5nPfYRi_HTAg";
        String badRequestId = "ohGSnJtMIC5nPfYRi_HTAgaaa";
        PlacesService placesService = new PlacesServiceByRequest(
                objectMapper,
                placesUrl,
                connectionTimeout,
                readTimeout,
                placeDetailsService
        );

        PlaceDetailsDto goodPlaceDetailsDto = placesService.getPlaceDetailsById(goodRequestId);
        Assertions.assertNotNull(goodPlaceDetailsDto);
    }
}