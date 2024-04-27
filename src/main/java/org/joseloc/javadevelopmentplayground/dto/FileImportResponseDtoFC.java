package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileImportResponseDtoFC {
    List<DocumentoVentaProcesadoDtoFC> docVentaProcesados;
    List<LineaDocumentoVentaDtoFcNc> lineaDocVentas;
}
