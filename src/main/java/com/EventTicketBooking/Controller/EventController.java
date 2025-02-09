package com.EventTicketBooking.Controller;

import com.EventTicketBooking.Model.Event;
import com.EventTicketBooking.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Display all events
    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();

        // Populate Base64 string for transient field
        events.forEach(event -> {
            if (event.getPosterImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(event.getPosterImage());
                event.setBase64PosterImage(base64Image);
            }
        });

        model.addAttribute("events", events);
        return "eventList"; // Thymeleaf template for event list
    }



    // Display the event creation form
    @GetMapping("/create")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "eventForm"; // Thymeleaf template for event creation form
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @PostMapping("/post")
    public String createEvent(@Validated @ModelAttribute Event event,
                              BindingResult result,
                              @RequestParam("posterImage") MultipartFile posterImage,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("text", "Validation failed. Please correct the errors.");
            return "eventForm";
        }

        try {
            if (posterImage != null && !posterImage.isEmpty()) {
                // Convert MultipartFile to byte[] and set it
                event.setPosterImage(posterImage.getBytes());
            }
            eventService.saveEvent(event); // Save event to the database
        } catch (IOException e) {
            model.addAttribute("text", "Error uploading image. Please try again.");
            return "eventForm";
        }

        return "redirect:/events";
    }





    // Display event details
    @GetMapping("/{id}")
    public String getEventById(@PathVariable int id, Model model) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            return "eventDetails"; // Thymeleaf template for event details
        } else {
            model.addAttribute("message", "Event not found.");
            return "error"; // Error page if event not found
        }
    }

    // Handle event deletion
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
}
