package com.baltacristiandorin.homeassignment.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDetailsDto {

    @JsonAlias("displayed_what")
    private String name;
    @JsonAlias("displayed_where")
    private String location;
    @JsonAlias("opening_hours")
    @JsonProperty("openingHours")
    private OpeningHoursDto openingHoursDto;
}
