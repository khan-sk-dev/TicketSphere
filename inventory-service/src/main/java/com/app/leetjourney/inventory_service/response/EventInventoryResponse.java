package com.app.leetjourney.inventory_service.response;

import java.math.BigDecimal;

import com.app.leetjourney.inventory_service.Entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {

    private Long eventId;
    private String event;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;
}

/**
 * DTO containing event-level inventory information used by clients.
 */
