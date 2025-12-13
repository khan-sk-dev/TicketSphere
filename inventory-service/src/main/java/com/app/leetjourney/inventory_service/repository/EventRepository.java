package com.app.leetjourney.inventory_service.repository;

import com.app.leetjourney.inventory_service.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


}
