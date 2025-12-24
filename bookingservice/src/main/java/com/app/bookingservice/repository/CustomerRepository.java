package com.app.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookingservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

/**
 * Spring Data JPA repository for `Customer` entities.
 *
 * Provides standard CRUD and paging operations via `JpaRepository`.
 */