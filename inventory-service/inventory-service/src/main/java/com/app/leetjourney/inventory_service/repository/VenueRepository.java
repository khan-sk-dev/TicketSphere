package com.app.leetjourney.inventory_service.repository;

import com.app.leetjourney.inventory_service.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {

}
