package org.joseloc.javadevelopmentplayground.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtilitis {
    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            return WorkbookFactory.create(inputStream);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
