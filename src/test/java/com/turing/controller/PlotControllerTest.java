package com.turing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.integration.AbstractIntegrationTest;
import com.turing.model.PartialUpdatePlotDto;
import com.turing.model.SoilType;
import com.turing.model.entity.PlotEntity;
import com.turing.repository.PlotRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlotControllerTest extends AbstractIntegrationTest {

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() { plotRepository.deleteAll(); }

    @Test
    @Order(1)
    void should_be_able_to_save_one_plot() throws Exception {
        // Given
        PlotEntity plotEntity = new PlotEntity();
        plotEntity.setSoilType(SoilType.SILT);
        plotEntity.setLength(150.50);
        plotEntity.setWidth(200.50);
        plotEntity.setLatitude(11.12);
        plotEntity.setLongitude(11.13);
        plotEntity.setWaterRequired(50.0);

        // When & Then
        mockMvc.perform(post("/api/plot")
                        .content(new ObjectMapper().writeValueAsString(plotEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.soilType").value("SILT"))
                .andExpect(jsonPath("$.length").value(150.50))
                .andExpect(jsonPath("$.width").value(200.50));
    }

    @Test
    @Order(2)
    void should_be_able_to_update_one_plot() throws Exception {
        // Given
        PlotEntity plotEntity = new PlotEntity();
        plotEntity.setSoilType(SoilType.SILT);
        plotEntity.setLength(150.50);
        plotEntity.setWidth(200.50);
        plotEntity.setLatitude(11.12);
        plotEntity.setLongitude(11.13);
        plotEntity.setWaterRequired(50.0);

        PlotEntity plotEntityUpdated = new PlotEntity();
        plotEntityUpdated.setPlotId(plotRepository.save(plotEntity).getPlotId());
        plotEntityUpdated.setSoilType(SoilType.CLAY);
        plotEntityUpdated.setLength(151.50);
        plotEntityUpdated.setWidth(201.50);
        plotEntityUpdated.setLatitude(12.12);
        plotEntityUpdated.setLongitude(13.13);
        plotEntityUpdated.setWaterRequired(51.0);

        // When & Then
        mockMvc.perform(put("/api/plot")
                        .content(new ObjectMapper().writeValueAsString(plotEntityUpdated))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soilType").value("CLAY"))
                .andExpect(jsonPath("$.length").value(151.50))
                .andExpect(jsonPath("$.width").value(201.50));
    }

    @Test
    @Order(3)
    void should_be_able_to_configure_one_plot() throws Exception {
        // Given
        PlotEntity plotEntity = new PlotEntity();
        plotEntity.setSoilType(SoilType.SILT);
        plotEntity.setLength(150.50);
        plotEntity.setWidth(200.50);
        plotEntity.setLatitude(11.12);
        plotEntity.setLongitude(11.13);
        plotEntity.setWaterRequired(50.0);

        PartialUpdatePlotDto partialUpdatePlotDto = new PartialUpdatePlotDto();
        partialUpdatePlotDto.setSoilType(SoilType.SANDY);

        // When & Then
        mockMvc.perform(patch("/api/plot/"+plotRepository.save(plotEntity).getPlotId())
                        .content(new ObjectMapper().writeValueAsString(partialUpdatePlotDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soilType").value("SANDY"))
                .andExpect(jsonPath("$.length").value(150.50))
                .andExpect(jsonPath("$.width").value(200.50));
    }

    @Test
    @Order(4)
    void should_be_able_to_retrieve_all_plot() throws Exception {
        // Given
        plotRepository.saveAll(List.of(new PlotEntity(), new PlotEntity(), new PlotEntity()));

        // When
        mockMvc.perform(get("/api/plots")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // Then
        assertThat(plotRepository.findAll()).hasSize(3);
    }

    @Test
    @Order(5)
    void should_be_able_to_throw_resource_not_found_exception() throws Exception {
        // Given
        PlotEntity plotEntity = new PlotEntity();
        plotEntity.setSoilType(SoilType.SILT);
        plotRepository.save(plotEntity);

        PartialUpdatePlotDto partialUpdatePlotDto = new PartialUpdatePlotDto();
        partialUpdatePlotDto.setSoilType(SoilType.SANDY);

        // When & Then
        mockMvc.perform(patch("/api/plot/"+ UUID.randomUUID())
                        .content(new ObjectMapper().writeValueAsString(partialUpdatePlotDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}