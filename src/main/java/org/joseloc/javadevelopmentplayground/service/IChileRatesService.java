package org.joseloc.javadevelopmentplayground.service;

import org.joseloc.javadevelopmentplayground.dto.InterestRatesResponseDto;

@FunctionalInterface
public interface IChileRatesService {
    InterestRatesResponseDto retrieveLatestInterestRates();
}
