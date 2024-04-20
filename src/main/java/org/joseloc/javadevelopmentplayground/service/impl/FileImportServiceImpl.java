package org.joseloc.javadevelopmentplayground.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joseloc.javadevelopmentplayground.dto.DocumentoVentaProcesadoDto;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDto;
import org.joseloc.javadevelopmentplayground.dto.LineaDocumentoVentaDto;
import org.joseloc.javadevelopmentplayground.enums.IndiceLineaVenta;
import org.joseloc.javadevelopmentplayground.enums.IndiceVentaProcesada;
import org.joseloc.javadevelopmentplayground.service.IFileImportService;
import org.joseloc.javadevelopmentplayground.util.ExcelUtilitis;
import org.joseloc.javadevelopmentplayground.util.UtilString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileImportServiceImpl implements IFileImportService {

    private final String SHEET_DOCUMENTO_VENTA_PROCESO = "Documentos de ventas Procesado";
    private final String SHEET_LINEA_DOCUMENTO_VENTA = "Líneas Documentos de venta";

    @Override
    public FileImportResponseDto importFromExcel(MultipartFile excel) {
        FileImportResponseDto fileFCDto = new FileImportResponseDto();
        Workbook workbook = null;
        try {
            workbook = ExcelUtilitis.getWorkbook(excel);
            fileFCDto.setDocVentaProcesados(readDocumentoVentaProcesado(workbook, excel.getName()));
            fileFCDto.setLineaDocVentas(readLineaDocumentoVenta(workbook, excel.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileFCDto;
    }

    private List<DocumentoVentaProcesadoDto> readDocumentoVentaProcesado(Workbook workbook, String fileName) {

        List<DocumentoVentaProcesadoDto> response = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Sheet sheetDocumentosVentasProcesados = workbook.getSheet(SHEET_DOCUMENTO_VENTA_PROCESO);
        Iterator<Row> rows = sheetDocumentosVentasProcesados.iterator();

        rows.next();

        rows.forEachRemaining( o -> {

            Iterator<Cell> cells = o.cellIterator();

            DocumentoVentaProcesadoDto documentoVenta = new DocumentoVentaProcesadoDto();

            cells.forEachRemaining(cell -> {

                IndiceVentaProcesada indice = IndiceVentaProcesada.getByIndex(cell.getColumnIndex());

                switch (indice) {
                    case NRO:
                        documentoVenta.setNroDocumento(formatter.formatCellValue(cell));
                        break;
                    case RUT:
                        documentoVenta.setRut(formatter.formatCellValue(cell));
                        break;
                    case TIPO_DOCUMENTO_LEGAL:
                        documentoVenta.setTipoDocumentoLegal(formatter.formatCellValue(cell));
                        break;
                    case NRO_DOCUMENTO_RECIBIDO_NEOZET:
                        documentoVenta.setNroDocumentoReciboNeozet(formatter.formatCellValue(cell));
                        break;
                    case FECHA_EMISION:
                        documentoVenta.setFechaEmision(formatter.formatCellValue(cell));
                        break;
                    case FECHA_VENCIMIENTO:
                        documentoVenta.setFechaVencimiento(formatter.formatCellValue(cell));
                        break;
                    case IND_SERVICIO:
                        documentoVenta.setIndServicio(formatter.formatCellValue(cell));
                        break;
                    case TIPO_TRANSACCION_VENTA:
                        documentoVenta.setTipoTransaccionVenta(formatter.formatCellValue(cell));
                        break;
                    case METODO_PAGO:
                        documentoVenta.setMetodoPago(formatter.formatCellValue(cell));
                        break;
                    case TERM_PAGO:
                        documentoVenta.setTermPago(formatter.formatCellValue(cell));
                        break;
                    case GRUPO_REGISTRO_CLIENTE:
                        documentoVenta.setGrupoRegistroCliente(formatter.formatCellValue(cell));
                        break;
                    case DOCUMENTO_ELECTRONICO:
                        documentoVenta.setDocumentoElectronico(formatter.formatCellValue(cell));
                        break;
                    case MES_SERVICIO:
                        documentoVenta.setMesServicio(formatter.formatCellValue(cell));
                        break;
                    case MES_FACTURACION:
                        documentoVenta.setMesFacturacion(formatter.formatCellValue(cell));
                        break;
                    case TEXTO_REGISTRADO:
                        documentoVenta.setTextoRegistrado(formatter.formatCellValue(cell));
                        break;
                    case GLOSA_PRINCIPAL:
                        documentoVenta.setGlosaPrincipal(formatter.formatCellValue(cell));
                        break;
                    case NRO_SUMINISTRO_ACTIVO:
                        documentoVenta.setNroSuministroActivo(formatter.formatCellValue(cell));
                        break;
                    case COD_ORIGEN_USUARIO:
                        documentoVenta.setCodOrigenUsuario(formatter.formatCellValue(cell));
                        break;
                    case CREADO_POR:
                        documentoVenta.setCreadoPor(formatter.formatCellValue(cell));
                        break;
                    default:
                        throw new IllegalStateException("Índice no manejado: " + indice);
                }
            });


            if(
                UtilString.coalesceTrim(documentoVenta.getNroDocumento()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getRut()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTipoDocumentoLegal()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getNroDocumentoReciboNeozet()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getFechaEmision()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getFechaVencimiento()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getIndServicio()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTipoTransaccionVenta()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getMetodoPago()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTermPago()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getGrupoRegistroCliente()).isEmpty() &&
                //UtilString.coalesceTrim(String.valueOf(documentoVenta.getDocumentoElectronico())).isEmpty() && //TODO: mejorar por que no se está obteniendo el valor
                UtilString.coalesceTrim(documentoVenta.getMesServicio()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getMesFacturacion()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTextoRegistrado()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getGlosaPrincipal()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getNroSuministroActivo()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getCodOrigenUsuario()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getCreadoPor()).isEmpty()
            )
                System.out.println("Se encontraron Valores vacíos en el documento " + fileName);
            else
                response.add(documentoVenta);
        } );

        return response;
    }

    private List<LineaDocumentoVentaDto> readLineaDocumentoVenta(Workbook workbook, String fileName) {

        List<LineaDocumentoVentaDto> response = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Sheet sheetDocumentosVentasProcesados = workbook.getSheet(SHEET_LINEA_DOCUMENTO_VENTA);
        Iterator<Row> rows = sheetDocumentosVentasProcesados.iterator();

        rows.next();

        rows.forEachRemaining( o -> {

            Iterator<Cell> cells = o.cellIterator();

            LineaDocumentoVentaDto docLineaVenta = new LineaDocumentoVentaDto();

            cells.forEachRemaining(cell -> {

                IndiceLineaVenta indice = IndiceLineaVenta.getByIndex(cell.getColumnIndex());

                switch (indice) {
                    case NRO_DOCUMENTO:
                        docLineaVenta.setNroDocumento(formatter.formatCellValue(cell));
                        break;
                    case NRO_LINEA:
                        docLineaVenta.setNroLinea(formatter.formatCellValue(cell));
                        break;
                    case TIPO_CODIGO:
                        docLineaVenta.setTipoCodigo(formatter.formatCellValue(cell));
                        break;
                    case NRO_PRODUCTO:
                        docLineaVenta.setNroProducto(formatter.formatCellValue(cell));
                        break;
                    case DESCRIPCION:
                        docLineaVenta.setDescripcion(formatter.formatCellValue(cell));
                        break;
                    case DESCRIPCION_TWO:
                        docLineaVenta.setDescripcionDos(formatter.formatCellValue(cell));
                        break;
                    case CANTIDAD:
                        docLineaVenta.setCantidad(formatter.formatCellValue(cell));
                        break;
                    case PRECIO_UNITARIO:
                        docLineaVenta.setPrecioUnitario(formatter.formatCellValue(cell));
                        break;
                    case INDICE_EXE:
                        docLineaVenta.setIndiceExe(formatter.formatCellValue(cell));
                        break;
                    case PPTO:
                        docLineaVenta.setPpto(formatter.formatCellValue(cell));
                        break;
                    case UNIDAD_NEGOCIO:
                        docLineaVenta.setUnidadNegocio(formatter.formatCellValue(cell));
                        break;
                    case LINEA:
                        docLineaVenta.setLinea(formatter.formatCellValue(cell));
                        break;
                    case REGION:
                        docLineaVenta.setRegion(formatter.formatCellValue(cell));
                        break;
                    default:
                        throw new IllegalStateException("Índice no manejado: " + indice);
                }
            });

            if(
                UtilString.coalesceTrim(docLineaVenta.getNroDocumento()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getLinea()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getTipoCodigo()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getNroProducto()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getDescripcion()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getDescripcionDos()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getCantidad()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getPrecioUnitario()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getIndiceExe()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getPpto()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getUnidadNegocio()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getLinea()).isEmpty() &&
                UtilString.coalesceTrim(docLineaVenta.getRegion()).isEmpty()
            )
                System.out.println("Se encontraron Valores vacíos en el documento " + fileName);
            else
                response.add(docLineaVenta);
        } );

        return response;
    }
}
