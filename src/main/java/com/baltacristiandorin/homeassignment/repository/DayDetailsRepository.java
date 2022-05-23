package com.baltacristiandorin.homeassignment.repository;

import com.baltacristiandorin.homeassignment.entity.DayDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayDetailsRepository extends CrudRepository<DayDetailsEntity, Long> {
}
