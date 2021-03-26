package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile implements ParseStrategy {
    private final File file;

    ParseFile(File file) {
        this.file = file;
    }

    public String contentWithoutUnicode(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = (char) i.read()) > 0) {
                if (filter.test((char) data)) {
                    i.lines().forEach(output::append);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    @Override
    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            i.lines().forEach(output::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
