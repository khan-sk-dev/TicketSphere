package com.app.leetjourney.inventory_service.service;

import com.app.leetjourney.inventory_service.repository.EventRepository;
import com.app.leetjourney.inventory_service.repository.VenueRepository;
import com.app.leetjourney.inventory_service.response.EventInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents(){



    }
}
