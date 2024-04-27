package org.joseloc.javadevelopmentplayground.service;

import org.springframework.web.multipart.MultipartFile;

public interface IExcelImportService {
    Object importExcel(MultipartFile file);

}
