package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

@Data
public class DocumentoVentaProcesadoDto {
    private String nroDocumento;
    private String rut;
    private String tipoDocumentoLegal;
    private String nroDocumentoReciboNeozet;
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
    private String textoRegistrado;
    private String glosaPrincipal;
    private String nroSuministroActivo;
    private String codOrigenUsuario;
    private String creadoPor;
}
