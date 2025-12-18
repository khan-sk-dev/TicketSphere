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
