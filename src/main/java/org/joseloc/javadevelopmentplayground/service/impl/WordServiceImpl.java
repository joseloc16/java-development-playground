package org.joseloc.javadevelopmentplayground.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.joseloc.javadevelopmentplayground.dto.DataContratoDto;
import org.joseloc.javadevelopmentplayground.entity.Contract;
import org.joseloc.javadevelopmentplayground.repository.IContratoRepository;
import org.joseloc.javadevelopmentplayground.service.IWordService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements IWordService {

    private final IContratoRepository contratoRepository;

    @Override
    public void  generarDocWord(DataContratoDto contratoDto) throws IOException {
        Contract contractDocente = contratoRepository.findContratoByTypeContract("c_docente");
        String titulo = contractDocente.getTitulo();
        String contratoPartes = contractDocente.getDescripcionContrato();
        String clausulaPrimera = contractDocente.getClausula();

        var document = new XWPFDocument();

        //Replace
        String paragraphTitulo = titulo.replace("{TITULO}", contratoDto.getTitulo());

        String paragraphContratoPartes = contratoPartes
                .replace("{INTITUCION}", contratoDto.getInstitucion())
                .replace("{DOMICILIO_DIRECTOR}", contratoDto.getDomicilioDirector())
                .replace("{NOMBRE_DIRECTOR}", contratoDto.getNombreDirector())
                .replace("{DNI_DIRECTOR}", contratoDto.getDniDirector())
                .replace("{NUMERO_RESOLUCION}", contratoDto.getNumeroResolucion())
                .replace("{NOMBRE_DOCENTE}", contratoDto.getNombreDocente())
                .replace("{DNI_DOCENTE}", contratoDto.getDniDocente())
                .replace("{DOMICILIO_DOCENTE}", contratoDto.getDomicilioDocente())
                .replace("{CORREO_DOCENTE}", contratoDto.getCorreoDocente());

        String paragraphClausulaPrimera = clausulaPrimera
                .replace("{NOMBRE_DOCENTE}", contratoDto.getNombreDocente());

        createTitleParagraph(document, paragraphTitulo);
        createParagraph(document, paragraphContratoPartes, ParagraphAlignment.BOTH);
        createParagraph(document, paragraphClausulaPrimera, ParagraphAlignment.BOTH);

        FileOutputStream outputStream =
                new FileOutputStream(
                        new File(System.getProperty("user.home") + File.separator + "prueba.docx")
                );
        document.write(outputStream);
        outputStream.close();
        document.close();
    }

    @Override
    public void generarDocWord(String title, String imageName, String fileName) throws IOException, InvalidFormatException {

        String imagePath = "src/main/resources/img/".concat(imageName).concat(".png");

        var document = new XWPFDocument();

        var titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        var titleRun = titleParagraph.createRun();
        titleRun.setText(title);
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(30);

        var imageParagraph = document.createParagraph();
        imageParagraph.setAlignment(ParagraphAlignment.CENTER);

        var imageRun = imageParagraph.createRun();
        imageRun.setTextPosition(20);
        Path path = Paths.get(imagePath);

        imageRun.addPicture(
                Files.newInputStream(path),
                XWPFDocument.PICTURE_TYPE_PNG,
                path.getFileName().toString(),
                Units.toEMU(100),
                Units.toEMU(50)
        );

        FileOutputStream outputStream =
                new FileOutputStream(
                        new File(System.getProperty("user.home") + File.separator + fileName)
                );
        document.write(outputStream);
        outputStream.close();
        document.close();
    }

    private void createTitleParagraph(XWPFDocument document, String titleText) {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText(titleText);
        titleRun.setColor("1C1B1A");
        titleRun.setBold(true);
        titleRun.setFontFamily("Arial");
        titleRun.setFontSize(20);
    }

    private void createParagraph(XWPFDocument document, String text, ParagraphAlignment alignment) {
        createParagraph(document, text, alignment, false);
    }

    private void createParagraph(XWPFDocument document, String text, ParagraphAlignment alignment, boolean italic) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(alignment);

        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(text);
        if (italic) {
            paragraphRun.setItalic(true);
        }
    }

}
