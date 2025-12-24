package com.app.bookingservice.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private Long userId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}

/**
 * DTO returned by the Booking API after a successful booking.
 *
 * Contains the basic booking summary including `userId`, `eventId`, number of
 * tickets and the `totalPrice` for the booking.
 */