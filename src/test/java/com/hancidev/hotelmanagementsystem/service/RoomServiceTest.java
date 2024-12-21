package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.RoomDto;
import com.hancidev.hotelmanagementsystem.dto.response.RoomResponse;
import com.hancidev.hotelmanagementsystem.entity.Room;
import com.hancidev.hotelmanagementsystem.entity.enums.RoomType;
import com.hancidev.hotelmanagementsystem.repository.RoomRepository;
import com.hancidev.hotelmanagementsystem.service.impl.RoomServiceImpl;
import com.hancidev.hotelmanagementsystem.service.mapper.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    private RoomDto roomDto;
    private Room room;
    private Room secondRoom;
    private Room thirdRoom;
    private Room fourthRoom;
    private RoomResponse roomResponse;
    private RoomResponse firstRoomResponse;
    private RoomResponse secondRoomResponse;

    private List<Room> rooms;
    private List<RoomResponse> roomResponses;

    @BeforeEach
    void setUp() {
        roomDto = new RoomDto(RoomType.KING);
        room = Room.builder()
                .id(1L)
                .roomCode("9875")
                .roomType(RoomType.KING)
                .available(true)
                .build();
        roomResponse = RoomResponse.builder()
                .roomCode("9875")
                .roomType(RoomType.KING)
                .isAvailable(true)
                .build();
        secondRoom = Room.builder()
                .id(1L)
                .roomCode("9873")
                .roomType(RoomType.KING)
                .available(true)
                .build();
        thirdRoom = Room.builder()
                .id(1L)
                .roomCode("9874")
                .roomType(RoomType.KING)
                .available(true)
                .build();
        fourthRoom = Room.builder()
                .id(1L)
                .roomCode("9876")
                .roomType(RoomType.KING)
                .available(false)
                .build();

        firstRoomResponse = RoomResponse.builder()
                .roomCode("9873")
                .roomType(RoomType.KING)
                .isAvailable(true)
                .build();

        secondRoomResponse = RoomResponse.builder()
                .roomCode("9874")
                .roomType(RoomType.KING)
                .isAvailable(true)
                .build();

        rooms = List.of(secondRoom, thirdRoom, fourthRoom);
        roomResponses = List.of(firstRoomResponse, secondRoomResponse);
    }

    @Test
    void shouldReturnRoomResponse_WhenRoomCreated() {
        when(roomMapper.roomFromRoomDto(roomDto)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        when(roomMapper.roomResponseFromRoom(room)).thenReturn(roomResponse);

        RoomResponse actualResponse = roomService.createRoom(roomDto);

        assertThat(actualResponse)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(roomResponse);

        verify(roomMapper).roomFromRoomDto(roomDto);
        verify(roomRepository).save(room);
        verify(roomMapper).roomResponseFromRoom(room);
    }

    @Test
    void shouldReturnOnlyAvailableRooms_WhenRoomsRequested() {
        when(roomRepository.findAll()).thenReturn(rooms);
        when(roomMapper.roomResponseFromRoom(secondRoom)).thenReturn(firstRoomResponse);
        when(roomMapper.roomResponseFromRoom(thirdRoom)).thenReturn(secondRoomResponse);

        List<RoomResponse> actualResponses = roomService.listRooms();

        assertThat(actualResponses)
                .hasSize(2)
                .extracting("roomCode", "roomType", "available")
                .containsExactly(tuple("9873", RoomType.KING, true),
                        tuple("9874", RoomType.KING, true));

        assertThat(actualResponses)
                .extracting("roomType")
                .containsExactly(RoomType.KING, RoomType.KING);

        assertThat(actualResponses)
                .filteredOn(RoomResponse::isAvailable)
                .hasSize(2);
    }

    @Test
    void shouldReturnEmptyList_WhenNoRoomExists() {
        when(roomRepository.findAll()).thenReturn(List.of());

        List<RoomResponse> result = roomService.listRooms();

        assertThat(result).isEmpty();
    }


}
