package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExcelDto {
    private List<SheetDto> sheetList;
}
