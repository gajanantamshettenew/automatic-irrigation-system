package com.turing.service;

import com.turing.exception.ResourceNotFoundException;
import com.turing.model.SoilType;
import com.turing.model.entity.PlotEntity;

import java.util.List;
import java.util.UUID;

public interface PlotService {

    public List<PlotEntity> getPlots();
    public PlotEntity save(PlotEntity plot);
    public PlotEntity update(PlotEntity plot) throws ResourceNotFoundException;
    public PlotEntity configure(UUID plotId, SoilType soilType) throws ResourceNotFoundException;

}
