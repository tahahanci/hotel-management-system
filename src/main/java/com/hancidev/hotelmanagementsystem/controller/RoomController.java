package com.hancidev.hotelmanagementsystem.controller;

import com.hancidev.hotelmanagementsystem.dto.RoomDto;
import com.hancidev.hotelmanagementsystem.dto.response.RoomResponse;
import com.hancidev.hotelmanagementsystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomResponse>> listRooms() {
        return ResponseEntity.ok(roomService.listRooms());
    }
}
