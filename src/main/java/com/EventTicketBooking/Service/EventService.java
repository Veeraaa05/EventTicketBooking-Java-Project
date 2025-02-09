package com.EventTicketBooking.Service;

import com.EventTicketBooking.Model.Event;
import com.EventTicketBooking.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get event by ID
    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }


    // Delete event by ID
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

}
