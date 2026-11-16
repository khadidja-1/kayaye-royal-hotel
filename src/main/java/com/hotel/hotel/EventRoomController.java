package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventRoomController {

    @Autowired
    private EventRoomRepository eventRoomRepository;

    @GetMapping
    public List<EventRoom> getAllEventRooms() {
        return eventRoomRepository.findAll();
    }

    @PostMapping
    public EventRoom addEventRoom(@RequestBody EventRoom eventRoom) {
        return eventRoomRepository.save(eventRoom);
    }

    @PutMapping("/{id}")
    public EventRoom updateEventRoom(@PathVariable Long id, @RequestBody EventRoom details) {
        EventRoom room = eventRoomRepository.findById(id).orElseThrow();
        room.setName(details.getName());
        room.setType(details.getType());
        room.setCapacity(details.getCapacity());
        room.setPrice(details.getPrice());
        room.setStatus(details.getStatus());
        return eventRoomRepository.save(room);
    }
}