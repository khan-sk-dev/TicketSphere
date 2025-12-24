package com.app.leetjourney.inventory_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueInventoryResponse {

    private Long venueId;
    private String venueName;
    private Long totalCapacty;
}

/**
 * DTO returned by the Inventory Service representing venue-level data.
 */
