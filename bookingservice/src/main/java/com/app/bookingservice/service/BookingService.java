package com.app.bookingservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.app.bookingservice.Response.BookingResponse;
import com.app.bookingservice.Response.InventoryResponse;
import com.app.bookingservice.client.InventoryServiceClient;
import com.app.bookingservice.entity.Customer;
import com.app.bookingservice.event.BookingEvent;
import com.app.bookingservice.repository.CustomerRepository;
import com.app.bookingservice.request.BookingRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(final CustomerRepository customerRepository,
            final InventoryServiceClient inventoryServiceClient,
            final KafkaTemplate kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest bookingRequest) {
        final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException();
        }
        // check if there is enough inventory.
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(bookingRequest.getEventId());
        log.info("Inventory Response: {}", inventoryResponse);
        if (inventoryResponse.getCapacity() < bookingRequest.getTicketCount()) {
            throw new RuntimeException("Not enough inventory");
        }
        // create booking.

        final BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventoryResponse);

        // send bookinng to order-service on a kafka topic.
        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to kafka: {}", bookingEvent);
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest bookingRequest, final Customer customer,
            final InventoryResponse inventoryResponse) {

        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice()
                        .multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
                .build();

    }
}