package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile implements ParseStrategy {
    private final File file;

    ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            i.lines().forEach(output::append);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file))))
        {
            o.write(content);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            if (filter.test((char) i.read())) {
                    i.lines().forEach(output::append);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
