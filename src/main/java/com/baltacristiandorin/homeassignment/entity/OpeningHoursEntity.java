package com.baltacristiandorin.homeassignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHoursEntity {

    @Id
    private String id;

    @Column
    private Boolean closedOnHolidays;

    @Column
    private Boolean openByArrangement;

    @OneToMany(
            mappedBy = "openingHoursEntity",
            cascade = CascadeType.ALL
    )
    private List<DayDetailsEntity> dayDetailsEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PlaceDetailsEntity placeDetailsEntity;
}
