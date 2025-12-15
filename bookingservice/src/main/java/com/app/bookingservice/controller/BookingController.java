package main.java.com.app.bookingservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import main.java.com.app.bookingservice.Response.BookingResponse;
import main.java.com.app.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import main.java.com.app.bookingservice.request.BookingRequest;

@RestController("/api/v1")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {

        return bookingService.createBooking(bookingRequest);
    }

}
