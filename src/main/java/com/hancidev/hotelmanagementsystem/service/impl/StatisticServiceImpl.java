package com.hancidev.hotelmanagementsystem.service.impl;

import com.hancidev.hotelmanagementsystem.dto.response.GenderDistributionResponse;
import com.hancidev.hotelmanagementsystem.entity.enums.Gender;
import com.hancidev.hotelmanagementsystem.repository.CustomerRepository;
import com.hancidev.hotelmanagementsystem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final CustomerRepository customerRepository;

    @Override
    public GenderDistributionResponse showGenderDistribution() {
        Long male = calculateGenderDistribution(Gender.MALE);
        Long female = calculateGenderDistribution(Gender.FEMALE);
        return GenderDistributionResponse.builder()
                .male(male)
                .woman(female)
                .build();
    }

    private Long calculateGenderDistribution(Gender gender) {
        return customerRepository.showGenderDistribution(gender);
    }
}
