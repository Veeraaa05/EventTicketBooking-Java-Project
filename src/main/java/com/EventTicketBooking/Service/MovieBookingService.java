package com.EventTicketBooking.Service;

import com.EventTicketBooking.Model.Movie;
import com.EventTicketBooking.Model.MovieBooking;
import com.EventTicketBooking.Repository.MovieBookingRepository;
import com.EventTicketBooking.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieBookingService {

    @Autowired
    private MovieBookingRepository movieBookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Save or update a booking
    public void saveBooking(MovieBooking booking) {
        // Get the associated movie
        Movie movie = booking.getMovie();

        // Calculate the total seats already booked for the movie
        int totalSeatsBooked = movieBookingRepository.findByMovie(movie).stream()
                .mapToInt(MovieBooking::getSeatsBooked)
                .sum();

        // Check if the number of seats booked exceeds the limit
        if (booking.getSeatsBooked() <= (100 - totalSeatsBooked)) {
            movieBookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("Not enough seats available for this movie.");
        }
    }

    // Get all bookings
    public List<MovieBooking> getAllBookings() {
        return movieBookingRepository.findAll();
    }

    // Get booking by ID
    public MovieBooking getBookingById(Long id) {
        return movieBookingRepository.findById(id).orElse(null);
    }

    // Delete booking by ID
    public void deleteBookingById(Long id) {
        movieBookingRepository.deleteById(id);
    }
}
