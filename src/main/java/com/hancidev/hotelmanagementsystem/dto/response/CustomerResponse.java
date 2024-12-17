package com.hancidev.hotelmanagementsystem.dto.response;

import lombok.Builder;

@Builder
public record CustomerResponse(String customerId, String firstName, String lastName, String mailAddress) {
}
