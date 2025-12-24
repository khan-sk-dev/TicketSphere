package com.app.leetjourney.inventory_service.Entity;

import java.math.BigDecimal;

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
@Table(name = "event")
public class Event {

    @Id
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_Capacity")
    private Long totalCapacity;

    @Column(name = "left_capacity")
    private Long leftCapacity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}

/**
 * JPA entity representing an Event stored in the inventory database.
 *
 * Fields include total and remaining capacity as well as the ticket price
 * and a reference to the `Venue` entity.
 */
