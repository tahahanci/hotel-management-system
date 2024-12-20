package com.hancidev.hotelmanagementsystem.dto;

import com.hancidev.hotelmanagementsystem.entity.enums.Gender;
import lombok.Builder;

@Builder
public record CustomerDto(String firstName, String lastName, String mailAddress, Gender gender) {
}
