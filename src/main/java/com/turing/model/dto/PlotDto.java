package com.turing.model.dto;

import com.turing.model.SoilType;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PlotDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID plotId;
    private SoilType soilType;
    private Double length;
    private Double width;
    private Double latitude;
    private Double longitude;
    private Double waterRequired;

}
