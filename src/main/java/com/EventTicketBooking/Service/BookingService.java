package com.EventTicketBooking.Service;

import com.EventTicketBooking.Model.Booking;
import com.EventTicketBooking.Model.Event;
import com.EventTicketBooking.Repository.BookingRepository;
import com.EventTicketBooking.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;
    // Save or update booking
//    public void saveBooking(Booking booking) {
//        bookingRepository.save(booking);
//    }


    public void saveBooking(Booking booking) {
        // Get the event associated with the booking
        Event event = booking.getEvent();

        // Check if the number of seats booked is less than or equal to the total available seats
        if (booking.getSeatsBooked() <= event.getTotalSeats()) {
            // Reduce the available seats in the event
            event.setTotalSeats(event.getTotalSeats() - booking.getSeatsBooked());
            eventRepository.save(event); // Save the updated event

            bookingRepository.save(booking); // Save the booking
        } else {
            // Handle the error if requested seats exceed available seats
            throw new IllegalArgumentException("Not enough available seats for the event.");
        }
    }



    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    // Delete booking by ID
    public void deleteBookingById(int id) {
        bookingRepository.deleteById(id);
    }
}
