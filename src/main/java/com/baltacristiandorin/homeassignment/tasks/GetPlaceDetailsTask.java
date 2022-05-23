package com.baltacristiandorin.homeassignment.tasks;

import com.baltacristiandorin.homeassignment.service.PlaceDetailsService;
import com.baltacristiandorin.homeassignment.service.PlacesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class GetPlaceDetailsTask {

    private final PlacesService placesService;
    private final PlaceDetailsService placeDetailsService;

    public GetPlaceDetailsTask(
            @Qualifier(value = "byRequest") PlacesService placesService,
            PlaceDetailsService placeDetailsService
    ) {
        this.placesService = placesService;
        this.placeDetailsService = placeDetailsService;
    }

    @Scheduled(fixedRate = 1000000L)
    public void getPlaceDetailsJob() {

        placeDetailsService.findAll().forEach(placeDetailsEntity -> {
            placesService.getPlaceDetailsById(placeDetailsEntity.getId());
        });
    }
}
