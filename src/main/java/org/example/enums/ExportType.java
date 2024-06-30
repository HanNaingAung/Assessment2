package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExportType {

    PDF("pdf", "application/pdf"),
    EXCEL("xlsx", "application/octet-stream"),
    CSV("csv", "text/csv"),
    DOCX("docx", "application/octet-stream");

    private final String extension;
    private final String contentType;

}
