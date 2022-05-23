package com.baltacristiandorin.homeassignment.repository;

import com.baltacristiandorin.homeassignment.entity.PlaceDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceDetailsRepository extends CrudRepository<PlaceDetailsEntity, String> {
}
