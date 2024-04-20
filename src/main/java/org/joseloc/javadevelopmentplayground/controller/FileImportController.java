package org.joseloc.javadevelopmentplayground.controller;

import lombok.RequiredArgsConstructor;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDto;
import org.joseloc.javadevelopmentplayground.service.impl.FileImportServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-playground/files")
public class FileImportController {

    private final FileImportServiceImpl fileImportService;

    @PostMapping("/import/excel")
    public ResponseEntity<FileImportResponseDto> importExcelFile(@RequestParam("file") MultipartFile file) {
        FileImportResponseDto importResponse = fileImportService.importFromExcel(file);
        return new ResponseEntity<>(importResponse, HttpStatus.OK);
    }

}
