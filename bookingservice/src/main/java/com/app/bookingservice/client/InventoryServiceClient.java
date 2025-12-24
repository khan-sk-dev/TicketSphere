package com.app.bookingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.bookingservice.Response.InventoryResponse;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long evenId) {

        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + evenId, InventoryResponse.class);

    }

}

/**
 * Lightweight HTTP client used by the Booking Service to query the inventory
 * service for availability and pricing.
 *
 * This client uses a plain `RestTemplate` for simplicity. In production you
 * may want to use a reactive HTTP client or a declarative client (e.g.,
 * OpenFeign) with retries, timeouts and error handling.
 */
