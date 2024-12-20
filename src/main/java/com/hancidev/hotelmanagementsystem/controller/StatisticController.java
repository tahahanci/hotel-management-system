package com.hancidev.hotelmanagementsystem.controller;

import com.hancidev.hotelmanagementsystem.dto.response.GenderDistributionResponse;
import com.hancidev.hotelmanagementsystem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/distribution")
    public ResponseEntity<GenderDistributionResponse> showDistribution() {
        return ResponseEntity.ok(statisticService.showGenderDistribution());
    }
}
