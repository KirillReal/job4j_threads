package ru.job4j.io;

import java.util.function.Predicate;

public interface ParseStrategy {
   String content(Predicate<Character> filter);

}
