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
@JsonTypeName("documentoVentaNc")
public class DocumentoVentaNc implements IExcelRow {

    private String nro;
    private String rut;
    private String tipoDocumentoLegal;
    private String nroDocumentoRecidoNeozet;
    private String fechaEmision;
    private String fechaVencimiento;
    private String indServicio;
    private String tipoTransaccionVenta;
    private String MetodoPago;
    private String TermPago;
    private String grupoRegistroCliente;
    private String documentoElectronico;
    private String mesServicio;
    private String mesFacturacion;
    private String textoRegistro;
    private String glosaPrincipal;
    private String nroSuministroActivo;
    private String codOrigenUsuario;
    private String creadoPor;
    private String procesado;
    private List<DocumentoVentaDetalleNc> lineas;

    @Override
    public List<String> getColumnValues() {
        List<String> values = new ArrayList<>();

        values.add(this.nro);
        values.add(this.rut);
        values.add(this.tipoDocumentoLegal);
        values.add(this.nroDocumentoRecidoNeozet);
        values.add(this.fechaEmision);
        values.add(this.fechaVencimiento);
        values.add(this.indServicio);
        values.add(this.tipoTransaccionVenta);
        values.add(this.MetodoPago);
        values.add(this.TermPago);
        values.add(this.grupoRegistroCliente);
        values.add(this.documentoElectronico);
        values.add(this.mesServicio);
        values.add(this.mesFacturacion);
        values.add(this.textoRegistro);
        values.add(this.glosaPrincipal);
        values.add(this.nroSuministroActivo);
        values.add(this.codOrigenUsuario);
        values.add(this.creadoPor);
        values.add(this.procesado);
        return values;
    }
}
