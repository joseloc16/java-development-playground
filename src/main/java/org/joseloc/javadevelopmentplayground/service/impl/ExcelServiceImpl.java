package org.joseloc.javadevelopmentplayground.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;
import org.joseloc.javadevelopmentplayground.dto.SheetDto;
import org.joseloc.javadevelopmentplayground.service.IExcelRow;
import org.joseloc.javadevelopmentplayground.service.IExcelService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ExcelServiceImpl implements IExcelService {

    @Override
    public AttachmentData getExcel(ExcelDto dataExcel) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (SheetDto sheetDto : dataExcel.getSheetList()) {
            String sheetName = sheetDto.getSheetName();
            generateSheetExcel(workbook, sheetDto, sheetName);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        log.info("Generación de archivo Excel");

        return AttachmentData.builder()
                .content(byteArrayOutputStream.toByteArray())
                .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .name("Ejemplo.xlsx")
                .extension("xlsx")
                .build();
    }

    public void generateSheetExcel(XSSFWorkbook workbook, SheetDto data, String sheetName) throws IOException {

        Sheet sheet = workbook.createSheet(sheetName);

        Font fontHeader = workbook.createFont();
        fontHeader.setFontName("Aptos Narrow");
        fontHeader.setFontHeightInPoints((short) 11);
        fontHeader.setBold(true);
        fontHeader.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(fontHeader);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Row rowHeader = sheet.createRow(0);
        for (int i = 0; i < data.getHeaders().size(); i++) {
            Cell cellHeader = rowHeader.createCell(i);
            cellHeader.setCellValue(data.getHeaders().get(i));
            cellHeader.setCellStyle(headerStyle);
        }

        if (data.getRows() != null && !data.getRows().isEmpty()) {
            int rowIndex = 1;
            for (IExcelRow excelRow : data.getRows()) {
                Row row = sheet.createRow(rowIndex++);
                List<String> columnValues = excelRow.getColumnValues();
                for (int i = 0; i < columnValues.size(); i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(columnValues.get(i));
                }
            }
        } else {
            log.info("La lista de valores es nula o vacía");
        }

        for (int i = 0; i < data.getHeaders().size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
