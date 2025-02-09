package com.EventTicketBooking.Controller;

import com.EventTicketBooking.Model.Movie;
import com.EventTicketBooking.Service.MovieService;
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
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    // Display list of movies
    @GetMapping
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();

        // Populate Base64 string for transient field
        movies.forEach(movie -> {
            if (movie.getPosterImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(movie.getPosterImage());
                movie.setBase64PosterImage(base64Image);
            }
        });

        model.addAttribute("movies", movies);
        return "movieList"; // Thymeleaf template for movie list
    }

    // Display the movie creation form
    @GetMapping("/create")
    public String createMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movieForm"; // Thymeleaf template for movie creation form
    }

    // Handle movie creation
    @PostMapping("/post")
    public String createMovie(@Validated @ModelAttribute Movie movie,
                              BindingResult result,
                              @RequestParam("posterImage") MultipartFile posterImage,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("text", "Validation failed. Please correct the errors.");
            return "movieForm";
        }

        try {
            if (posterImage != null && !posterImage.isEmpty()) {
                // Convert MultipartFile to byte[] and set it
                movie.setPosterImage(posterImage.getBytes());
            }
            movieService.saveMovie(movie); // Save movie to the database
        } catch (IOException e) {
            model.addAttribute("text", "Error uploading image. Please try again.");
            return "movieForm";
        }

        return "redirect:/movies";
    }

    // Display movie details
    @GetMapping("/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "movieDetails"; // Thymeleaf template for movie details
        } else {
            model.addAttribute("message", "Movie not found.");
            return "error"; // Error page if movie not found
        }
    }

    // Handle movie deletion
    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return "redirect:/movies";
    }
}
