package com.hotel.hotel;

import jakarta.persistence.*;

@Entity
@Table(name = "event_rooms")
public class EventRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // ex: "Salle de Conférence A", "Salle des Fêtes"
    private String type;        // ex: "Meeting", "Wedding", "Birthday"
    private Integer capacity;   // Nombre de personnes max
    private Double price;       // Prix de location
    private String status;      // "Available", "Booked"

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}