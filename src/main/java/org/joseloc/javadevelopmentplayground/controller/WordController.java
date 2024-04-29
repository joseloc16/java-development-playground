package org.joseloc.javadevelopmentplayground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.joseloc.javadevelopmentplayground.dto.DataContratoDto;
import org.joseloc.javadevelopmentplayground.service.IWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api-playground/word")
public class WordController {

    private final IWordService wordService;

    @PostMapping("/generate-word")
    public ResponseEntity<Void> generarDocWord(
            @RequestParam String title,
            @RequestParam String imageName,
            @RequestParam String fileName) throws IOException, InvalidFormatException {
        wordService.generarDocWord(title, imageName, fileName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/generate")
    public ResponseEntity<XWPFDocument> generateWordContract(@RequestBody DataContratoDto contract) {
        try {
            wordService.generarDocWord(contract);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[Generate Contract - POST]. Error al generar el contrato. Detalle del error: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
