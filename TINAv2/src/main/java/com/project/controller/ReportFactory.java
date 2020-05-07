package com.project.controller;

public class ReportFactory {

    public Report getReport(String reportType) {

        switch (reportType) {
            case "txt" : return new TxtReport();
            case "pdf" : return new PdfReport();
            default: break;
        }

        return null;
    }
}
