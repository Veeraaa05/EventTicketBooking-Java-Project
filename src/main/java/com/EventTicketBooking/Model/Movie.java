package com.EventTicketBooking.Model;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "Movie_poster_image", columnDefinition = "LONGBLOB")
    private byte[] posterImage;

    @Transient
    private String base64PosterImage; // Transient field for Base64 representation

    private double rating;
    private String language;
    private String description;
    private String duration;

    @Column(nullable = false)
    private int totalSeats;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(byte[] posterImage) {
        this.posterImage = posterImage;
    }

    public String getBase64PosterImage() {
        return base64PosterImage;
    }

    public void setBase64PosterImage(String base64PosterImage) {
        this.base64PosterImage = base64PosterImage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
