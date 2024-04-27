package org.joseloc.javadevelopmentplayground.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joseloc.javadevelopmentplayground.dto.DocumentoVentaProcesadoDtoFC;
import org.joseloc.javadevelopmentplayground.dto.DocumentoVentaProcesadoDtoNC;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDtoFC;
import org.joseloc.javadevelopmentplayground.dto.FileImportResponseDtoNC;
import org.joseloc.javadevelopmentplayground.dto.LineaDocumentoVentaDtoFcNc;
import org.joseloc.javadevelopmentplayground.enums.IndiceLineaVentaFcNc;
import org.joseloc.javadevelopmentplayground.enums.IndiceVentaProcesadaFC;
import org.joseloc.javadevelopmentplayground.enums.IndiceVentaProcesadaNC;
import org.joseloc.javadevelopmentplayground.service.IExcelImportService;
import org.joseloc.javadevelopmentplayground.util.ExcelUtilitis;
import org.joseloc.javadevelopmentplayground.util.UtilString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class ExcelImportServiceImpl implements IExcelImportService {

    private static final String FILE_NAME_FC = "ImportarExcelFC";
    private static final String SHEET_DOCUMENTO_VENTA_PROCESO = "Documentos de ventas Procesado";
    private static final String SHEET_LINEA_DOCUMENTO_VENTA = "Líneas Documentos de ventas";

    @Override
    public Object importExcel(MultipartFile file) {
        String nombreSinExtension = obtenerNombreSinExtension(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Workbook workbook = ExcelUtilitis.getWorkbook(file);
            if (nombreSinExtension.equals(FILE_NAME_FC)) {
                FileImportResponseDtoFC fileFCDto = new FileImportResponseDtoFC();
                fileFCDto.setDocVentaProcesados(readDocumentoVentaProcesadoFC(workbook, file.getName()));
                fileFCDto.setLineaDocVentas(readLineaDocumentoVentaFcNc(workbook, file.getName()));
                return fileFCDto;
            } else {
                FileImportResponseDtoNC fileNCDto = new FileImportResponseDtoNC();
                fileNCDto.setDocVentaProcesados(readDocumentoVentaProcesadoNC(workbook, file.getName()));
                fileNCDto.setLineaDocVentas(readLineaDocumentoVentaFcNc(workbook, file.getName()));
                return fileNCDto;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<DocumentoVentaProcesadoDtoFC> readDocumentoVentaProcesadoFC(Workbook workbook, String fileName) {

        List<DocumentoVentaProcesadoDtoFC> response = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Sheet sheetDocumentosVentasProcesados = workbook.getSheet(SHEET_DOCUMENTO_VENTA_PROCESO);
        Iterator<Row> rows = sheetDocumentosVentasProcesados.iterator();

        rows.next();

        rows.forEachRemaining( o -> {

            Iterator<Cell> cells = o.cellIterator();

            DocumentoVentaProcesadoDtoFC documentoVenta = new DocumentoVentaProcesadoDtoFC();

            cells.forEachRemaining(cell -> {

                IndiceVentaProcesadaFC indice = IndiceVentaProcesadaFC.getByIndex(cell.getColumnIndex());

                switch (indice) {
                    case NRO:
                        documentoVenta.setNro(formatter.formatCellValue(cell));
                        break;
                    case RUT:
                        documentoVenta.setRut(formatter.formatCellValue(cell));
                        break;
                    case TIPO_DOCUMENTO_LEGAL:
                        documentoVenta.setTipoDocumentoLegal(formatter.formatCellValue(cell));
                        break;
                    case NRO_DOCUMENTO_RECIBIDO_NEOZET:
                        documentoVenta.setNroDocumentoRecibidoNeozet(formatter.formatCellValue(cell));
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
                        documentoVenta.setTextoRegistro(formatter.formatCellValue(cell));
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
               UtilString.coalesceTrim(documentoVenta.getNro()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getRut()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getTipoDocumentoLegal()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getNroDocumentoRecibidoNeozet()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getFechaEmision()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getFechaVencimiento()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getIndServicio()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getTipoTransaccionVenta()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getMetodoPago()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getTermPago()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getGrupoRegistroCliente()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getDocumentoElectronico()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getMesServicio()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getMesFacturacion()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getTextoRegistro()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getGlosaPrincipal()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getNroSuministroActivo()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getCodOrigenUsuario()).isEmpty() &&
               UtilString.coalesceTrim(documentoVenta.getCreadoPor()).isEmpty()
            )
                log.info("Se encontraron Valores vacíos en el documento " + fileName);
            else
                response.add(documentoVenta);
        } );
        return response;
    }

    private List<DocumentoVentaProcesadoDtoNC> readDocumentoVentaProcesadoNC(Workbook workbook, String fileName) {
        List<DocumentoVentaProcesadoDtoNC> response = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Sheet sheetDocumentosVentasProcesados = workbook.getSheet(SHEET_DOCUMENTO_VENTA_PROCESO);
        Iterator<Row> rows = sheetDocumentosVentasProcesados.iterator();

        rows.next();

        rows.forEachRemaining( o -> {

            Iterator<Cell> cells = o.cellIterator();

            DocumentoVentaProcesadoDtoNC documentoVenta = new DocumentoVentaProcesadoDtoNC();

            cells.forEachRemaining(cell -> {

                IndiceVentaProcesadaNC indice = IndiceVentaProcesadaNC.getByIndex(cell.getColumnIndex());

                switch (indice) {
                    case NRO:
                        documentoVenta.setNro(formatter.formatCellValue(cell));
                        break;
                    case RUT:
                        documentoVenta.setRut(formatter.formatCellValue(cell));
                        break;
                    case TIPO_DOCUMENTO_LEGAL:
                        documentoVenta.setTipoDocumentoLegal(formatter.formatCellValue(cell));
                        break;
                    case NRO_DOCUMENTO_RECIBIDO_NEOZET:
                        documentoVenta.setNroDocumentoRecibidoNeozet(formatter.formatCellValue(cell));
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
                        documentoVenta.setTextoRegistro(formatter.formatCellValue(cell));
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
                    case PROCESADO:
                        documentoVenta.setProcesado(formatter.formatCellValue(cell));
                        break;
                    case CREADO_POR:
                        documentoVenta.setCreadoPor(formatter.formatCellValue(cell));
                        break;
                    default:
                        throw new IllegalStateException("Índice no manejado: " + indice);
                }
            });

            if(
                UtilString.coalesceTrim(documentoVenta.getNro()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getRut()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTipoDocumentoLegal()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getNroDocumentoRecibidoNeozet()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getFechaEmision()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getFechaVencimiento()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getIndServicio()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTipoTransaccionVenta()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getMetodoPago()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTermPago()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getGrupoRegistroCliente()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getDocumentoElectronico()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getMesServicio()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getMesFacturacion()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getTextoRegistro()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getGlosaPrincipal()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getNroSuministroActivo()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getCodOrigenUsuario()).isEmpty() &&
                UtilString.coalesceTrim(documentoVenta.getProcesado()).isEmpty()&&
                UtilString.coalesceTrim(documentoVenta.getCreadoPor()).isEmpty()
            )
                log.info("Se encontraron Valores vacíos en el documento " + fileName);
            else
                response.add(documentoVenta);
        } );
        return response;
    }

    private List<LineaDocumentoVentaDtoFcNc> readLineaDocumentoVentaFcNc(Workbook workbook, String fileName) {

        List<LineaDocumentoVentaDtoFcNc> response = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Sheet sheetDocumentosVentasProcesados = workbook.getSheet(SHEET_LINEA_DOCUMENTO_VENTA);
        Iterator<Row> rows = sheetDocumentosVentasProcesados.iterator();

        rows.next();

        rows.forEachRemaining( o -> {

            Iterator<Cell> cells = o.cellIterator();

            LineaDocumentoVentaDtoFcNc docLineaVenta = new LineaDocumentoVentaDtoFcNc();

            cells.forEachRemaining(cell -> {

                IndiceLineaVentaFcNc indice = IndiceLineaVentaFcNc.getByIndex(cell.getColumnIndex());

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
                UtilString.coalesceTrim(docLineaVenta.getNroLinea()).isEmpty() &&
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
                log.info("Se encontraron Valores vacíos en el documento " + fileName);
            else
                response.add(docLineaVenta);
        } );
        return response;
    }

    private String obtenerNombreSinExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex == -1) {
            log.info("No se encontró extensión en el nombre del archivo");
            return fileName;
        } else {
            log.info("Devuelve el nombre del archivo sin la extensión");
            return fileName.substring(0, extensionIndex);
        }
    }
}
