package com.baltacristiandorin.homeassignment.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHoursDto {

    @JsonAlias("days")
    @JsonProperty("days")
    private Map<String, List<DayDetailsDto>> openingHours;
    @JsonAlias("closed_on_holidays")
    private Boolean closedOnHolidays;
    @JsonAlias("open_by_arrangement")
    private Boolean openByArrangement;
}
