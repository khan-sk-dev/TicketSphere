
package com.app.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final bookingRepository bookingRepository;

    @Autowired
    public BookingService(bookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        return BookingResponse.builder().build();

    }

}
