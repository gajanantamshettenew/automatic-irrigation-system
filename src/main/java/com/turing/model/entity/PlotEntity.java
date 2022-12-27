package com.turing.model.entity;

import com.turing.model.PostgreSQLEnumType;
import com.turing.model.SoilType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "PLOT")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class PlotEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(generator = "UUID")
    @Id
    @Column(name = "plot_id", updatable = false, nullable = false)
    private UUID plotId;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private SoilType soilType;

    private Double length;
    private Double width;
    private Double latitude;
    private Double longitude;
    private Double waterRequired;
    private String unit = "ltr";
}
