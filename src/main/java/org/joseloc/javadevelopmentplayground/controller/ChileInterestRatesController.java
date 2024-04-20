package org.joseloc.javadevelopmentplayground.controller;

import lombok.RequiredArgsConstructor;
import org.joseloc.javadevelopmentplayground.dto.InterestRatesResponseDto;
import org.joseloc.javadevelopmentplayground.service.IChileRatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api-playground/scrapper")
public class ChileInterestRatesController {

    private final IChileRatesService chileRatesService;

    @GetMapping(value = "/fetch-latest")
    public ResponseEntity<InterestRatesResponseDto> fetchLatestChileanMarketInterestRates() {
        try {
            InterestRatesResponseDto response = chileRatesService.retrieveLatestInterestRates();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
