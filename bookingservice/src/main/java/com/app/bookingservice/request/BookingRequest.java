package com.app.bookingservice.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    private Long userId;
    private Long eventId;
    private Long ticketCount;

}

/**
 * DTO representing a booking request from a client.
 *
 * Fields:
 * - `userId`: identifier of the user making the booking
 * - `eventId`: identifier of the event to book
 * - `ticketCount`: number of tickets requested
 */
