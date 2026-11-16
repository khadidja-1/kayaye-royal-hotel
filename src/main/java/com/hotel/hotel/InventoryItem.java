package com.hotel.hotel;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // ex: "Savon", "Draps", "Pâtes"
    private String category;   // ex: "Housekeeping", "Kitchen", "Bar"
    private Integer quantity;  // Quantité actuelle en stock
    private Integer minStock;  // Seuil d'alerte (si en dessous, il faut commander)
    private String unit;       // ex: "pièces", "kg", "litres"

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}