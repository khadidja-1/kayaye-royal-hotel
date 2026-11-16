package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // Add a new room
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    // Delete a room
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
    }

    // Update a room
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow();

        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setType(roomDetails.getType());
        room.setPrice(roomDetails.getPrice());
        room.setStatus(roomDetails.getStatus());

        return roomRepository.save(room);
    }
    // Route publique pour l'accueil (uniquement les chambres disponibles)
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomRepository.findAll().stream()
                .filter(room -> "Available".equals(room.getStatus()))
                .toList();
    }
}