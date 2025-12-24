package com.app.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.app.bookingservice.event.BookingEvent;
import com.app.orderservice.client.InventoryServiceClient;
import com.app.orderservice.entity.Order;
import com.app.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {

        log.info("Received order event: {}", bookingEvent);

        // Create order object for DB
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);

        // update the inventory
        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(), order.getTicketCount());

        // Create Inventory Client

    }

    private Order createOrder(BookingEvent bookingEvent) {

        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

}

/**
 * Service that listens for booking events on Kafka and creates orders.
 *
 * Responsibilities:
 * - Consume `BookingEvent` messages and persist corresponding `Order`
 * - Call the Inventory Service to decrement capacity for the event
 *
 * Notes:
 * - Kafka consumer configuration (topics, groupId) is wired by the
 * `@KafkaListener` annotation.
 */