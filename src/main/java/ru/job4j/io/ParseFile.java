package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            i.lines().forEach(output::append);
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file))))
        {
            o.write(content);
        }
    }
}
