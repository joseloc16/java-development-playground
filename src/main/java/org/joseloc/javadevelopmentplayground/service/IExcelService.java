package org.joseloc.javadevelopmentplayground.service;

import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;

import java.io.IOException;

@FunctionalInterface
public interface IExcelService {
    AttachmentData getExcel(ExcelDto dataExcel) throws IOException;
}
