package com.project.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfReport implements Report{
    @Override
    public void generateReportFile(String data, String fileName) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = directoryChooser.showDialog(new Stage());


        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(selectedDirectory.getAbsolutePath()).append("\\").append(fileName).append(".pdf");

            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(stringBuilder.toString()));
            document.open();
            document.add(new Paragraph(data));
            document.close();
            pdfWriter.close();

        }catch(IOException | DocumentException ex){
            ex.printStackTrace();
        }
    }
}
