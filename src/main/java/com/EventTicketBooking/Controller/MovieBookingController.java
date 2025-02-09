package com.EventTicketBooking.Controller;

import com.EventTicketBooking.Model.Movie;
import com.EventTicketBooking.Model.MovieBooking;
import com.EventTicketBooking.Service.MovieBookingService;
import com.EventTicketBooking.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movie-bookings")
public class MovieBookingController {

    @Autowired
    private MovieBookingService movieBookingService;

    @Autowired
    private MovieService movieService;

    // Display all movie bookings
    @GetMapping
    public String getAllBookings(Model model) {
        List<MovieBooking> bookings = movieBookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "movieBookingList"; // Make sure movieBookingList.html exists in /templates/
    }

    // Display the booking form
    @GetMapping("/create")
    public String createBookingForm(Model model) {
        model.addAttribute("booking", new MovieBooking());
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies); // Populate dropdown with movie options
        return "movieBookingForm"; // Ensure movieBookingForm.html exists
    }

    // Handle booking creation
    @PostMapping("/post")
    public String createBooking(@ModelAttribute("booking") MovieBooking booking, Model model) {
        try {
            movieBookingService.saveBooking(booking);
            model.addAttribute("message", "Booking created successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "movieBookingForm"; // Return to form in case of error
        }
        return "redirect:/movies"; // Redirect to the movie booking list after creation
    }

    // Delete a booking
    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        movieBookingService.deleteBookingById(id);
        return "redirect:/movie-bookings"; // Redirect to the list after deletion
    }
}
