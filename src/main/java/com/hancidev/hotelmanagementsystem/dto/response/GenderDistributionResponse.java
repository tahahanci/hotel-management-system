package com.hancidev.hotelmanagementsystem.dto.response;

import lombok.Builder;

@Builder
public record GenderDistributionResponse(Long male, Long woman) {
}
