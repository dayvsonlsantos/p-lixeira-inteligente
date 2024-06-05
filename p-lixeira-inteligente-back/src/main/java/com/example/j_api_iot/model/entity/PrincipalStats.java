package com.example.j_api_iot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class PrincipalStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tempo;

    private String volume;

    private String rgb;

    private Integer quant_updates;

    private Integer quant_open;

    private Integer quant_close;

    private Integer quant_orange_level;


}
