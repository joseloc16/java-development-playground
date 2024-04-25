package org.joseloc.javadevelopmentplayground.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.joseloc.javadevelopmentplayground.bean.DocumentoVentaDetalleFc;
import org.joseloc.javadevelopmentplayground.bean.DocumentoVentaDetalleNc;
import org.joseloc.javadevelopmentplayground.bean.DocumentoVentaFc;
import org.joseloc.javadevelopmentplayground.bean.DocumentoVentaNc;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DocumentoVentaFc.class, name = "documentoVentaFc"),
        @JsonSubTypes.Type(value = DocumentoVentaDetalleFc.class, name = "documentoVentaDetalleFc"),
        @JsonSubTypes.Type(value = DocumentoVentaNc.class, name = "documentoVentaNc"),
        @JsonSubTypes.Type(value = DocumentoVentaDetalleNc.class, name = "documentoVentaDetalleNc"),
})
@FunctionalInterface
public interface IExcelRow {
    List<String> getColumnValues();
}