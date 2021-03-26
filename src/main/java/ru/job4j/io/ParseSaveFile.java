package ru.job4j.io;

import java.io.*;

public class ParseSaveFile implements ParseSave {

    private final File file;

    ParseSaveFile(File file) {
        this.file = file;
    }

    @Override
    public void content(String content) {
        try (PrintWriter o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            o.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
