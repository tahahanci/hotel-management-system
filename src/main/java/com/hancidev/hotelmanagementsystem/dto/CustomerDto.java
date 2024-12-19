package com.hancidev.hotelmanagementsystem.dto;

import lombok.Builder;

@Builder
public record CustomerDto(String firstName, String lastName, String mailAddress) {
}
