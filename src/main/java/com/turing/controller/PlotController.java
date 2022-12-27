package com.turing.controller;

import com.turing.exception.ResourceNotFoundException;
import com.turing.model.PartialUpdatePlotDto;
import com.turing.model.dto.PlotDto;
import com.turing.model.entity.PlotEntity;
import com.turing.service.PlotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Exposed couple of APIs to create & manage plot details
 *
 * @author Ganajan
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/api")
public class PlotController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlotService plotService;

    @GetMapping("/plots")
    public ResponseEntity<List<PlotDto>> getPlots() {
        return new ResponseEntity<List<PlotDto>>(
                plotService.getPlots().stream()
                        .map(plot -> modelMapper.map(plot, PlotDto.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/plot")
    public ResponseEntity<PlotDto> savePlot(@RequestBody PlotDto plot) {
        return new ResponseEntity<PlotDto>(
                modelMapper.map(
                        plotService.save(modelMapper.map(
                                plot, PlotEntity.class)), PlotDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/plot")
    public ResponseEntity<PlotDto> updatePlot(@RequestBody PlotDto plot) throws ResourceNotFoundException {
        return new ResponseEntity<PlotDto>(
                modelMapper.map(
                        plotService.update(modelMapper.map(
                                plot, PlotEntity.class)), PlotDto.class), HttpStatus.OK);
    }

    @PatchMapping("/plot/{plotId}")
    public ResponseEntity<PlotDto> configurePlot(@PathVariable(value = "plotId") UUID plotId,
                                           @RequestBody PartialUpdatePlotDto partialUpdatePlot) throws ResourceNotFoundException {
        return new ResponseEntity<PlotDto>(
                modelMapper.map(plotService.configure(
                        plotId, partialUpdatePlot.getSoilType()), PlotDto.class), HttpStatus.OK);
    }

}
