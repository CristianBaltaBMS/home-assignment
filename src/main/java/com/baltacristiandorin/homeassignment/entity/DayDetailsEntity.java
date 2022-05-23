package com.baltacristiandorin.homeassignment.entity;

import com.baltacristiandorin.homeassignment.entity.embedded.DayDetailsEntityEmbeddedKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayDetailsEntity {

    @EmbeddedId
    private DayDetailsEntityEmbeddedKey dayDetailsEntityEmbeddedKey;

    @ManyToOne
    private OpeningHoursEntity openingHoursEntity;
}
