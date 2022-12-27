package com.turing.repository;

import com.turing.model.entity.PlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlotRepository extends JpaRepository<PlotEntity, UUID> {

    public Optional<PlotEntity> findByPlotId(UUID plotId);
}
