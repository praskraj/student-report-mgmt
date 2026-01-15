package com.api.annualreportmgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "rollno")
    private String rollNo;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_place")
    private String eventPlace;

    @Column(name = "event_location")
    private String eventLocation;

    @Column(name = "event_duration")
    private Long eventDuration;
}

