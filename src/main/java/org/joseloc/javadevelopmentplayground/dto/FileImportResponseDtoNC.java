package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileImportResponseDtoNC {
    List<DocumentoVentaProcesadoDtoNC> docVentaProcesados;
    List<LineaDocumentoVentaDtoFcNc> lineaDocVentas;
}
