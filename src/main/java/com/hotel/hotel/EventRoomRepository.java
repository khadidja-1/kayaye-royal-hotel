package com.hotel.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRoomRepository extends JpaRepository<EventRoom, Long> {
}