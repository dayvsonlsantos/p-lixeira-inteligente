package com.example.j_api_iot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;


@Data
@Entity
public class Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rgb;

    private float volume;

    private String trash_state;

    @CreationTimestamp
    private Instant creationTimeStamp;


}
