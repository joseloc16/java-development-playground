package org.joseloc.javadevelopmentplayground.dto;

import lombok.Data;

@Data
public class DocumentoVentaProcesadoDtoFC {
    private String nro;
    private String rut;
    private String tipoDocumentoLegal;
    private String nroDocumentoRecibidoNeozet;
    private String fechaEmision;
    private String fechaVencimiento;
    private String indServicio;
    private String tipoTransaccionVenta;
    private String metodoPago;
    private String termPago;
    private String grupoRegistroCliente;
    private String documentoElectronico;
    private String mesServicio;
    private String mesFacturacion;
    private String textoRegistro;
    private String glosaPrincipal;
    private String nroSuministroActivo;
    private String codOrigenUsuario;
    private String creadoPor;
}
