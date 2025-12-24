package com.app.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private String address;

}

/**
 * JPA entity representing a Customer in the booking service database.
 *
 * Fields map to columns in the `customer` table. This simple entity is used
 * to look up user information during booking processing.
 */
