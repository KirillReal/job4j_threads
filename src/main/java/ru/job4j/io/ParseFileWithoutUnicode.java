package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFileWithoutUnicode implements ParseStrategy{

    private final File file;

    ParseFileWithoutUnicode(File file) {
        this.file = file;
    }

    @Override public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while((data = (char) i.read()) > 0) {
                if (filter.test((char) data)) {
                    i.lines().forEach(output::append);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
