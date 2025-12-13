package com.app.leetjourney.inventory_service.service;

import com.app.leetjourney.inventory_service.Entity.Event;
import com.app.leetjourney.inventory_service.Entity.Venue;
import com.app.leetjourney.inventory_service.repository.EventRepository;
import com.app.leetjourney.inventory_service.repository.VenueRepository;
import com.app.leetjourney.inventory_service.response.EventInventoryResponse;
import com.app.leetjourney.inventory_service.response.VenueInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(final Long venueId){
        final Venue venue = venueRepository.findById(venueId).orElseGet(null);

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacty(venue.getTotalCapacity())
                .build();
    }
}
