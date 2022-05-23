package com.baltacristiandorin.homeassignment.service;

import com.baltacristiandorin.homeassignment.entity.DayDetailsEntity;
import com.baltacristiandorin.homeassignment.repository.DayDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class DayDetailsService {

    private final DayDetailsRepository dayDetailsRepository;

    public DayDetailsService(DayDetailsRepository dayDetailsRepository) {
        this.dayDetailsRepository = dayDetailsRepository;
    }

    public void save(DayDetailsEntity dayDetailsEntity){
        dayDetailsRepository.save(dayDetailsEntity);
    }
}
