package org.joseloc.javadevelopmentplayground.service;

import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDto;
import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface IFileImportService {
    FileImportResponseDto importFromExcel(MultipartFile file);
}
