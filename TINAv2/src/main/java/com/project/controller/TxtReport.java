package com.project.controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtReport implements Report{
    @Override
    public void generateReportFile(String data, String fileName) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(selectedDirectory.getAbsolutePath()).append("\\").append(fileName).append(".txt");
            File reportFile = new File(stringBuilder.toString());

            FileWriter fileWriter = new FileWriter(stringBuilder.toString());
            fileWriter.write(data);
            fileWriter.close();

        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

}
