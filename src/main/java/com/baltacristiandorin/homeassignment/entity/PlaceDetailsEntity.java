package com.baltacristiandorin.homeassignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDetailsEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String location;

    @OneToOne(mappedBy = "placeDetailsEntity", cascade = CascadeType.ALL)
    private OpeningHoursEntity openingHoursEntity;
}
