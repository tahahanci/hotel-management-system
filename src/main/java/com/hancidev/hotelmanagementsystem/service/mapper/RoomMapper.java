package com.hancidev.hotelmanagementsystem.service.mapper;

import com.hancidev.hotelmanagementsystem.dto.RoomDto;
import com.hancidev.hotelmanagementsystem.dto.response.RoomResponse;
import com.hancidev.hotelmanagementsystem.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room roomFromRoomDto(RoomDto from) {
        return Room.builder()
                .roomType(from.roomType())
                .build();
    }

    public RoomResponse roomResponseFromRoom(Room from) {
        return RoomResponse.builder()
                .roomType(from.getRoomType())
                .roomCode(from.getRoomCode())
                .isAvailable(from.isAvailable())
                .build();
    }
}
