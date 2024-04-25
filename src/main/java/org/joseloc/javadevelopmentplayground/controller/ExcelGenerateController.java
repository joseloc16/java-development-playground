package org.joseloc.javadevelopmentplayground.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;
import org.joseloc.javadevelopmentplayground.service.IExcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("//api-playground/excel")
public class ExcelGenerateController {

    private final IExcelService excelService;

    @PostMapping(value = "/obtener-excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<AttachmentData> generarExcel(@RequestBody ExcelDto data, HttpServletResponse response) {
        try {
            log.info("Datos al generar el archivo excel: " +data);
            AttachmentData attachmentData = excelService.getExcel(data);
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
}
