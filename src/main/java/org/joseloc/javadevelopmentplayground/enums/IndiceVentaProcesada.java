package org.joseloc.javadevelopmentplayground.enums;

import lombok.Getter;

@Getter
public enum IndiceVentaProcesada {

    NRO(0),
    RUT(1),
    TIPO_DOCUMENTO_LEGAL(2),
    NRO_DOCUMENTO_RECIBIDO_NEOZET(3),
    FECHA_EMISION(4),
    FECHA_VENCIMIENTO(5),
    IND_SERVICIO(6),
    TIPO_TRANSACCION_VENTA(7),
    METODO_PAGO(8),
    TERM_PAGO(9),
    GRUPO_REGISTRO_CLIENTE(10),
    DOCUMENTO_ELECTRONICO(11),
    MES_SERVICIO(12),
    MES_FACTURACION(13),
    TEXTO_REGISTRADO(14),
    GLOSA_PRINCIPAL(15),
    NRO_SUMINISTRO_ACTIVO(16),
    COD_ORIGEN_USUARIO(17),
    CREADO_POR(18);

    private final int index;

    IndiceVentaProcesada(int index) {
        this.index = index;
    }

    public static IndiceVentaProcesada getByIndex(int index) {
        for (IndiceVentaProcesada value : IndiceVentaProcesada.values()) {
            if (value.getIndex() == index) {
                return value;
            }
        }
        throw new IllegalArgumentException("Índice no encontrado: " + index);
    }
}
