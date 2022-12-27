package com.turing.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartialUpdatePlotDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private SoilType soilType;
}
