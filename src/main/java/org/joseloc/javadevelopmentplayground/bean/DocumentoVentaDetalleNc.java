package org.joseloc.javadevelopmentplayground.bean;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joseloc.javadevelopmentplayground.service.IExcelRow;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeName("documentoVentaDetalleNc")
public class DocumentoVentaDetalleNc  implements IExcelRow {

    private String nDocument;
    private String nLinea;
    private String tipoCodigo;
    private String nProducto;
    private String descripcion;
    private String descripcion2;
    private String cantidad;
    private String precioUnitario;
    private String indExc;
    private String ppto;
    private String unidNegocio;
    private String linea;
    private String region;

    @Override
    public List<String> getColumnValues() {
        List<String> values = new ArrayList<>();
        values.add(this.nDocument);
        values.add(this.nLinea);
        values.add(this.tipoCodigo);
        values.add(this.nProducto);
        values.add(this.descripcion);
        values.add(this.descripcion2);
        values.add(this.cantidad);
        values.add(this.precioUnitario);
        values.add(this.indExc);
        values.add(this.ppto);
        values.add(this.unidNegocio);
        values.add(this.linea);
        values.add(this.region);
        return values;
    }
}

