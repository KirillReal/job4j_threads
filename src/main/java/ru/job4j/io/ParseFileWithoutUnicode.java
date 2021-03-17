package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFileWithoutUnicode {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

   public Predicate<Integer> getContentWithoutUnicode()
    {
        return data -> data < 0x80;
    }
}
