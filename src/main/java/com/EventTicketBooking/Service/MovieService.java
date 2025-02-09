package com.EventTicketBooking.Service;

import com.EventTicketBooking.Model.Movie;
import com.EventTicketBooking.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Save a movie
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // Fetch all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Fetch a movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Delete a movie by ID
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }
}
