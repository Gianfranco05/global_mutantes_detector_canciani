package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.StatsResponse;
import org.example.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {
        long mutantCount = repository.countByIsMutant(true);
        long humanCount = repository.countByIsMutant(false);
        double ratio = calculateRatio(mutantCount, humanCount);
        return new StatsResponse(mutantCount, humanCount, ratio);
    }

    private double calculateRatio(long mutantCount, long humanCount) {
        if (humanCount == 0) {
            return mutantCount > 0 ? mutantCount : 0.0;
        }
        return (double) mutantCount / humanCount;
    }
}
