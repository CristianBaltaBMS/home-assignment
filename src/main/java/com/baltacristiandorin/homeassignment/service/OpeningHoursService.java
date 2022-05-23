package com.baltacristiandorin.homeassignment.service;

import com.baltacristiandorin.homeassignment.entity.OpeningHoursEntity;
import com.baltacristiandorin.homeassignment.repository.OpeningHoursRepository;
import org.springframework.stereotype.Service;

@Service
public class OpeningHoursService {

    private final OpeningHoursRepository openingHoursRepository;

    public OpeningHoursService(OpeningHoursRepository openingHoursRepository) {
        this.openingHoursRepository = openingHoursRepository;
    }

    public void save(OpeningHoursEntity openingHoursEntity) {
        openingHoursRepository.save(openingHoursEntity);
    }
}
