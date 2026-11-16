package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return inventoryRepository.save(item);
    }

    // Mettre à jour la quantité (ex: ajouter 50 draps)
    @PutMapping("/{id}")
    public InventoryItem updateItem(@PathVariable Long id, @RequestBody InventoryItem itemDetails) {
        InventoryItem item = inventoryRepository.findById(id).orElseThrow();
        item.setName(itemDetails.getName());
        item.setCategory(itemDetails.getCategory());
        item.setQuantity(itemDetails.getQuantity());
        item.setMinStock(itemDetails.getMinStock());
        item.setUnit(itemDetails.getUnit());
        return inventoryRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        inventoryRepository.deleteById(id);
    }
}