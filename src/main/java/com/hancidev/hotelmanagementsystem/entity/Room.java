package com.hancidev.hotelmanagementsystem.entity;

import com.hancidev.hotelmanagementsystem.entity.enums.RoomType;
import com.hancidev.hotelmanagementsystem.utility.RandomCodeGenerator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomCode;
    private boolean available;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @PrePersist
    public void setRoomProperties() {
        if (this.roomCode == null || this.roomCode.isEmpty()) {
            this.roomCode = RandomCodeGenerator.generateRoomCode();
            available = true;
        }
    }
}
