package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.RoomDto;
import com.hancidev.hotelmanagementsystem.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse createRoom(RoomDto roomDto);

    List<RoomResponse> listRooms();
}
