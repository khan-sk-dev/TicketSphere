package com.app.bookingservice.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueResponse {

    private Long id;
    private String name;
    private String address;
    private Long totalCapacity;

}

/**
 * DTO representation of a venue used in inventory responses.
 */
