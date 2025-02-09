package com.EventTicketBooking.Model;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String location;
    private String description;

    @Column(nullable = false)
    private int totalSeats;

    private int availableSeats;  // Remaining seats for booking


    @Lob
    @Column(name = "poster_image", columnDefinition = "LONGBLOB")
    private byte[] posterImage;

    public byte[] getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(byte[] posterImage) {
        this.posterImage = posterImage;
    }

    @Transient
    private String base64PosterImage; // Transient field for Base64 representation

    public String getBase64PosterImage() {
        return base64PosterImage;
    }

    public void setBase64PosterImage(String base64PosterImage) {
        this.base64PosterImage = base64PosterImage;
    }


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


}