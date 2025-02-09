package com.EventTicketBooking.Controller;

import com.EventTicketBooking.Model.Booking;
import com.EventTicketBooking.Model.Event;
import com.EventTicketBooking.Service.BookingService;
import com.EventTicketBooking.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    // Display all bookings
    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);  // Pass the list of bookings to the Thymeleaf template
        return "bookingList"; // Thymeleaf template to display bookings
    }


    // Display the booking form

    @GetMapping("/bookform")
    public String createBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);  // Pass the list of events to the Thymeleaf template
        return "bookingForm"; // Thymeleaf template to show the form for creating a booking
    }


    // Create a new booking
    @PostMapping("/create")
    public String createBooking(@ModelAttribute Booking booking, Model model) {
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Booking created successfully.");
        return "redirect:/bookings"; // Redirect to booking list page after creating
    }

    // Display booking details
    @GetMapping("/{id}")
    public String getBookingById(@PathVariable int id, Model model) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            model.addAttribute("booking", booking.get());
            return "bookingDetails"; // Thymeleaf template to show booking details
        } else {
            model.addAttribute("message", "Booking not found.");
            return "error"; // Error page if booking not found
        }
    }

    // Delete a booking
//    @DeleteMapping("/{id}")
//    public String deleteBooking(@PathVariable int id, Model model) {
//        bookingService.deleteBookingById(id);
//        model.addAttribute("message", "Booking deleted successfully.");
//        return "redirect:/bookings"; // Redirect to booking list page after deletion
//    }

    // Delete a booking using POST (more commonly used in form submissions)
    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Booking deleted successfully.");
        return "redirect:/bookings"; // Redirect to booking list page after deletion
    }

}
