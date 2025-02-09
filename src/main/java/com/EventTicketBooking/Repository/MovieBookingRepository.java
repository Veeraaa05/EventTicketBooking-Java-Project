package com.EventTicketBooking.Repository;

import com.EventTicketBooking.Model.Movie;
import com.EventTicketBooking.Model.MovieBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieBookingRepository extends JpaRepository<MovieBooking, Long> {
    List<MovieBooking> findByMovie(Movie movie);
}
