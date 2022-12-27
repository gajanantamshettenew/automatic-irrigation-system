package com.turing.service.impl;

import com.turing.exception.ResourceNotFoundException;
import com.turing.model.SoilType;
import com.turing.model.entity.PlotEntity;
import com.turing.repository.PlotRepository;
import com.turing.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotRepository plotRepository;

    @Override
    public List<PlotEntity> getPlots() {
        return plotRepository.findAll();
    }

    @Override
    public PlotEntity save(PlotEntity plot) {
        return plotRepository.save(plot);
    }

    @Override
    public PlotEntity update(PlotEntity plot) throws ResourceNotFoundException {
        PlotEntity plotEntity = findByPlotId(plot.getPlotId());
        plotEntity.setSoilType(plot.getSoilType());
        plotEntity.setLength(plot.getLength());
        plotEntity.setWidth(plot.getWidth());
        plotEntity.setLatitude(plot.getLatitude());
        plotEntity.setLongitude(plot.getLongitude());
        plotEntity.setWaterRequired(plot.getWaterRequired());
        return plotRepository.save(plotEntity);
    }

    @Override
    public PlotEntity configure(UUID plotId, SoilType soilType) throws ResourceNotFoundException {
        PlotEntity plotEntity = findByPlotId(plotId);
        plotEntity.setSoilType(soilType);
        return plotRepository.save(plotEntity);
    }

    private PlotEntity findByPlotId(UUID plotId) throws ResourceNotFoundException {
        return plotRepository.findByPlotId(plotId)
                .orElseThrow(() -> new ResourceNotFoundException("Plot not found on :: "+ plotId));
    }

}
