package com.baltacristiandorin.homeassignment.repository;

import com.baltacristiandorin.homeassignment.entity.OpeningHoursEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningHoursRepository extends CrudRepository<OpeningHoursEntity, Long> {
}
