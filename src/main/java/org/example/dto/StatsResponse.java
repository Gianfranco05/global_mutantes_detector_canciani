package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Statistics of DNA verifications")
public class StatsResponse {
    @Schema(description = "Count of mutant DNA verifications")
    private long count_mutant_dna;
    @Schema(description = "Count of human DNA verifications")
    private long count_human_dna;
    @Schema(description = "Ratio of mutant to human DNA verifications")
    private double ratio;
}
