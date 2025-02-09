package com.EventTicketBooking.Repository;

import com.EventTicketBooking.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Custom queries can be added if needed

}
