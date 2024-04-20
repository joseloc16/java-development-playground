package org.joseloc.javadevelopmentplayground.enums;

import lombok.Getter;

@Getter
public enum IndiceLineaVenta {

    NRO_DOCUMENTO(0),
    NRO_LINEA(1),
    TIPO_CODIGO(2),
    NRO_PRODUCTO(3),
    DESCRIPCION(4),
    DESCRIPCION_TWO(5),
    CANTIDAD(6),
    PRECIO_UNITARIO(7),
    INDICE_EXE(8),
    PPTO(9),
    UNIDAD_NEGOCIO(10),
    LINEA(11),
    REGION(12);

    IndiceLineaVenta(int index) {
        this.index = index;
    }

    private int index;

    public static IndiceLineaVenta getByIndex(int index) {
        for (IndiceLineaVenta value : IndiceLineaVenta.values()) {
            if (value.getIndex() == index) {
                return value;
            }
        }
        throw new IllegalArgumentException("Índice no encontrado: " + index);
    }
}
