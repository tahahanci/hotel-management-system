package com.hancidev.hotelmanagementsystem.service.impl;

import com.hancidev.hotelmanagementsystem.dto.RoomDto;
import com.hancidev.hotelmanagementsystem.dto.response.RoomResponse;
import com.hancidev.hotelmanagementsystem.entity.Room;
import com.hancidev.hotelmanagementsystem.repository.RoomRepository;
import com.hancidev.hotelmanagementsystem.service.RoomService;
import com.hancidev.hotelmanagementsystem.service.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

    @Override
    public RoomResponse createRoom(RoomDto roomDto) {
        Room room = roomRepository.save(roomMapper.roomFromRoomDto(roomDto));
        return roomMapper.roomResponseFromRoom(room);
    }

    @Override
    public List<RoomResponse> listRooms() {
        return roomRepository.findAll()
                .stream()
                .filter(Room::isAvailable)
                .map(roomMapper::roomResponseFromRoom)
                .toList();
    }
}
