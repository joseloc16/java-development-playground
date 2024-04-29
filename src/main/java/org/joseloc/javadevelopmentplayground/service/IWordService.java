package org.joseloc.javadevelopmentplayground.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joseloc.javadevelopmentplayground.dto.DataContratoDto;

import java.io.IOException;

public interface IWordService {
    void generarDocWord(DataContratoDto contratoDto) throws IOException;

    void generarDocWord(String title, String imgPath, String fileName) throws IOException, InvalidFormatException;

}
