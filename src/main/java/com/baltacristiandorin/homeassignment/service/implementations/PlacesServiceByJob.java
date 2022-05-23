package com.baltacristiandorin.homeassignment.service.implementations;

import com.baltacristiandorin.homeassignment.dto.DayDetailsDto;
import com.baltacristiandorin.homeassignment.dto.OpeningHoursDto;
import com.baltacristiandorin.homeassignment.dto.PlaceDetailsDto;
import com.baltacristiandorin.homeassignment.entity.DayDetailsEntity;
import com.baltacristiandorin.homeassignment.entity.PlaceDetailsEntity;
import com.baltacristiandorin.homeassignment.service.PlaceDetailsService;
import com.baltacristiandorin.homeassignment.service.PlacesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Qualifier(value = "byJob")
@Log4j2
public class PlacesServiceByJob implements PlacesService {

    private final PlaceDetailsService placeDetailsService;
    private final PlacesService placesService;

    public PlacesServiceByJob(
            PlaceDetailsService placeDetailsService,
            @Qualifier(value = "byRequest") PlacesService placesService
    ) {
        this.placeDetailsService = placeDetailsService;
        this.placesService = placesService;
    }


    @Override
    public PlaceDetailsDto getPlaceDetailsById(String requestId) {

        PlaceDetailsDto placeDetailsDto = new PlaceDetailsDto();
        PlaceDetailsEntity placeDetailsEntity = new PlaceDetailsEntity();
        try {
            placeDetailsEntity = placeDetailsService.findById(requestId);
        } catch (Exception e) {
            log.info("Place with id %s has not been found. Proceeding to fetch from external sources.".formatted(requestId));
        }
        if (placeDetailsEntity.getId() == null) {
            placeDetailsDto = placesService.getPlaceDetailsById(requestId);
        } else {
            placeDetailsDto.setName(placeDetailsEntity.getName());
            placeDetailsDto.setLocation(placeDetailsEntity.getLocation());

            OpeningHoursDto openingHoursDto = OpeningHoursDto.builder()
                    .openByArrangement(placeDetailsEntity.getOpeningHoursEntity().getOpenByArrangement())
                    .closedOnHolidays(placeDetailsEntity.getOpeningHoursEntity().getClosedOnHolidays())
                    .build();

            Map<String, List<DayDetailsDto>> openingHours = new HashMap<>();

            Set<String> days = placeDetailsEntity.getOpeningHoursEntity().getDayDetailsEntities()
                    .stream().map(dayDetailsEntity -> dayDetailsEntity.getDayDetailsEntityEmbeddedKey().getDay())
                    .collect(Collectors.toSet());

            for (String day : days) {
                List<DayDetailsDto> dayDetailsDtos = new ArrayList<>();

                for (DayDetailsEntity dayDetailsEntity :
                        placeDetailsEntity.getOpeningHoursEntity().getDayDetailsEntities()
                                .stream().filter(dayDetailsEntity ->
                                        dayDetailsEntity.getDayDetailsEntityEmbeddedKey().getDay().equals(day)).toList()) {
                    DayDetailsDto dayDetailsDto = DayDetailsDto.builder()
                            .start(dayDetailsEntity.getDayDetailsEntityEmbeddedKey().getStart())
                            .end(dayDetailsEntity.getDayDetailsEntityEmbeddedKey().getEnd())
                            .type(dayDetailsEntity.getDayDetailsEntityEmbeddedKey().getType())
                            .build();
                    dayDetailsDtos.add(dayDetailsDto);
                }
                openingHours.put(day, dayDetailsDtos);
            }

            openingHoursDto.setOpeningHours(openingHours);
            placeDetailsDto.setOpeningHoursDto(openingHoursDto);
        }

        return placeDetailsDto;
    }
}
