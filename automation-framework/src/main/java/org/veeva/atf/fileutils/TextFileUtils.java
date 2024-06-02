package org.veeva.atf.fileutils;


import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.*;

public class TextFileUtils {

    private File filePath;

    @SneakyThrows
    public void createFileHeader(String filename) {

        File newOutputFolder = new File(System.getProperty("user.dir") + File.separator + "output");
        if (!newOutputFolder.exists())
            newOutputFolder.mkdirs();
        File file = new File(newOutputFolder.getAbsoluteFile() + File.separator + filename);
        String text = "|      PRICE    |                           TILE                          |      TOP SELLER MESSAGE                                             |  ";
        if (!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.newLine();
        bw.close();
        this.filePath = file;
    }

    @SneakyThrows
    public void createFileDuplicateFooterLinkReport(String filename) {

        File newOutputFolder = new File(System.getProperty("user.dir") + File.separator + "output");
        if (!newOutputFolder.exists())
            newOutputFolder.mkdirs();
        File file = new File(newOutputFolder.getAbsoluteFile() + File.separator + filename);
        String text = "|      SECTION    |       SUB SECTION       |      HYPERLINK            |   COMMENTS             |  ";
        if (!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.newLine();
        bw.close();
        this.filePath = file;
    }

    @SneakyThrows
    public void appendFooterReport(String contentText) {
        BufferedWriter bw = Files.newBufferedWriter(this.filePath.getAbsoluteFile().toPath(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        bw.write(contentText);
        bw.newLine();
        bw.close();
    }

    @SneakyThrows
    public void appendContentToFile(String contentText) {
        BufferedWriter bw = Files.newBufferedWriter(this.filePath.getAbsoluteFile().toPath(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            bw.write(contentText);
            bw.newLine();
            bw.close();
    }

    public File getFilePath() {
        return this.filePath;
    }
}
