

import java.math.BigDecimal;

/**
 * Simple Event model for Mini Ticket System
 * Academic project for BIT 4043 OOP course
 */
public class Event {
    private int id;
    private String name;
    private String date;
    private String venue;
    private String description;
    private BigDecimal basePrice;
    private int totalSeats;
    private int availableSeats;
    private String imagePath; // Path to event image

    // Constructor
    public Event(int id, String name, String date, String venue, String description,
                 BigDecimal basePrice, int totalSeats, String imagePath) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.description = description;
        this.basePrice = basePrice;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.imagePath = imagePath;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Business methods
    public boolean hasAvailableSeats(int requestedQuantity) {
        return availableSeats >= requestedQuantity;
    }

    public void bookSeats(int quantity) {
        if (hasAvailableSeats(quantity)) {
            availableSeats -= quantity;
        }
    }

    @Override
    public String toString() {
        return name + " - " + date + " at " + venue;
    }
}
