package com.app.orderservice.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public ResponseEntity<Object> updateInventory(final Long eventId, final Long ticketCount) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(inventoryServiceUrl + "/event/" + eventId + "/capacity/" + ticketCount, null);
        return ResponseEntity.ok().build();

    }

}

/**
 * Small HTTP client used by the Order Service to tell the Inventory Service
 * to decrement capacity after an order is created.
 *
 * This client uses `RestTemplate` for simplicity; consider adding error
 * handling and retries for robustness.
 */
