package com.app.leetjourney.inventory_service.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="event")
public class Event {

    @Id
    private Long Id;

    @Column(name="name")
    private String name;

    @Column(name="total_Capacity")
    private Long totalCapacity;

    @Column(name="left_capacity")
    private Long leftCapacity;

    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;
}
