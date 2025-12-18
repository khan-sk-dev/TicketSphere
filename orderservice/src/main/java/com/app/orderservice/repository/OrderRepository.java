package com.app.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
