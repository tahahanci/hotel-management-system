package com.hancidev.hotelmanagementsystem.entity;

import com.hancidev.hotelmanagementsystem.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PrePersist
    public void setActivationStatus() {
        active = true;
    }
}
