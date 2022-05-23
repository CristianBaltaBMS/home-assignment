package com.baltacristiandorin.homeassignment.dto;

import com.baltacristiandorin.homeassignment.dto.enums.Type;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayDetailsDto implements Serializable {

    @JsonAlias("start")
    private String start;
    @JsonAlias("end")
    private String end;
    @JsonAlias("type")
    private Type type;
}