package org.joseloc.javadevelopmentplayground.service;

import org.joseloc.javadevelopmentplayground.bean.AttachmentData;
import org.joseloc.javadevelopmentplayground.dto.ExcelDto;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IExcelOperationService {
    FileImportResponseDto importFromExcel(MultipartFile file);
    AttachmentData generateExcel(ExcelDto data) throws IOException;
}
