package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileImportResponseDto {
    List<DocumentoVentaProcesadoDto> docVentaProcesados;
    List<LineaDocumentoVentaDto> lineaDocVentas;
}
