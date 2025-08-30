package com.trebol.travelstats.domainobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Airport origin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Airport destination;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Carrier carrier;

    @Column(nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private Time duration;

    private String number;
}
