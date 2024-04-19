package org.joseloc.javadevelopmentplayground.service.impl;

import org.joseloc.javadevelopmentplayground.dto.InterestRateDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.joseloc.javadevelopmentplayground.dto.InterestRatesResponseDto;
import org.joseloc.javadevelopmentplayground.service.IChileRatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChileRatesServiceImpl implements IChileRatesService {

    @Value("${app-playground.scrapper.url-tasas-chile}")
    private String tasasChileUrl;

    @Override
    public InterestRatesResponseDto retrieveLatestInterestRates() {
        InterestRatesResponseDto responseDto = new InterestRatesResponseDto();
        responseDto.setListCategories(scrapeInterestRates());
        return responseDto;
    }

    private Map<String, List<InterestRateDto>> scrapeInterestRates() {

        Map<String, List<InterestRateDto>> mapInterestRate = new LinkedHashMap<>();
        List<InterestRateDto> interestRates = null;
        String currentCategory = null;

        try {
            Document document = Jsoup.connect(tasasChileUrl).get();
            Elements rows = document.select("tbody > tr");

            List<Integer> positionCategories = filCategoryPosition(rows);

            for (int i = 0, j = 0; i < rows.size(); i++) {
                Elements cells = rows.get(i).select("td");

                if (positionCategories.contains(i)) {
                    if (currentCategory != null) {
                        mapInterestRate.put(currentCategory, interestRates);
                    }
                    interestRates = new ArrayList<>();
                    currentCategory = cells.get(0).text();
                    if (i == rows.size() - 1) {
                        // TODO: POR DEFINIR
                    }
                    continue;
                }
                InterestRateDto interestRate = new InterestRateDto();
                interestRate.setDescription(cells.get(0).text());
                interestRate.setCurrentInterest(cells.get(1).text());
                interestRate.setMaximumConventionalInterest(cells.get(2).text());

                interestRates.add(interestRate);
            }

            // Última lista en el mapa después del bucle
            if (currentCategory != null && interestRates != null) {
                mapInterestRate.put(currentCategory, interestRates);
            }

            return mapInterestRate;

        } catch (Exception e) {
            return null;
        }
    }

    private List<Integer> filCategoryPosition(Elements rowsTr) {
        List<Integer> positions = new ArrayList<>();
        for(int i = 0; i < rowsTr.size(); i++) {
            Elements tds = rowsTr.get(i).select("td");
            if(tds.size() == 1 || i == rowsTr.size() - 1) {
                positions.add(i);
            }
        }
        return positions;
    }
}
