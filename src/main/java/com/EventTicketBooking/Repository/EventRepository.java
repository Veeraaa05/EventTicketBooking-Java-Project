package com.EventTicketBooking.Repository;

import com.EventTicketBooking.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    // Custom queries can be added here if needed
}
