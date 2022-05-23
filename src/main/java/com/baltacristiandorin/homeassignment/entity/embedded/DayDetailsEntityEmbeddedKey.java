package com.baltacristiandorin.homeassignment.entity.embedded;

import com.baltacristiandorin.homeassignment.dto.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayDetailsEntityEmbeddedKey implements Serializable {

    @Column
    private String day;

    @Column(name = "starts")
    private String start;

    @Column(name = "ends")
    private String end;

    @Column
    private Type type;
}
