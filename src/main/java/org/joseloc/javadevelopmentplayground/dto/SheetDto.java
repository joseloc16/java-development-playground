package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;
import org.joseloc.javadevelopmentplayground.service.IExcelRow;

import java.util.List;

@Data
public class SheetDto {
    private String sheetName;
    private List<String> headers;
    private List<? extends IExcelRow> rows;
}
