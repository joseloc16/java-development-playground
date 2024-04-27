package org.joseloc.javadevelopmentplayground.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDtoFC;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDtoNC;
import org.joseloc.javadevelopmentplayground.service.IExcelImportService;
import org.joseloc.javadevelopmentplayground.service.IExcelOperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api-playground/excel")
public class ExcelController {

    private final IExcelOperationService operationService;
    private final IExcelImportService excelImportService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<AttachmentData> generarExcel(@RequestBody ExcelDto data, HttpServletResponse response) {
        try {
            AttachmentData attachmentData = operationService.generateExcel(data);
            attachmentData.setDataDownload(response);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(attachmentData.getContent());
            outputStream.flush();
        } catch (IOException ex) {
            log.error("[Generate Contract - POST]. Error al generar el archivo Excel: " +ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @PostMapping(value = "/import-excel-fc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileImportResponseDtoFC> importExcelFileFC(@RequestParam("file") MultipartFile file) {
        FileImportResponseDtoFC importResponse = (FileImportResponseDtoFC) excelImportService.importExcel(file);
        return new ResponseEntity<>(importResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/import-excel-nc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileImportResponseDtoNC> importExcelFileNC(@RequestParam("file") MultipartFile file) {
        FileImportResponseDtoNC importResponse = (FileImportResponseDtoNC) excelImportService.importExcel(file);
        return new ResponseEntity<>(importResponse, HttpStatus.OK);
    }
}
