package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class InterestRatesResponseDto {
    Map<String, List<InterestRateDto>> listCategories;
}
