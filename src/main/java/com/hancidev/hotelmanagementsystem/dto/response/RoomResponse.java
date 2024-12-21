package com.hancidev.hotelmanagementsystem.dto.response;

import com.hancidev.hotelmanagementsystem.entity.enums.RoomType;
import lombok.Builder;

@Builder
public record RoomResponse(RoomType roomType, String roomCode, boolean isAvailable) {
}
