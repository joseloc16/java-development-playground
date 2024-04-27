package org.joseloc.javadevelopmentplayground.service;

import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;

import java.io.IOException;

public interface IExcelOperationService {
    AttachmentData generateExcel(ExcelDto data) throws IOException;
}
