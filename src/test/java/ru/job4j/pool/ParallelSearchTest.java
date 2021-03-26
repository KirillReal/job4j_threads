package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void foundElement() {
        Integer[] input = new Integer[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        assertEquals(5, ParallelSearch.sort(input, 6));
    }

    @Test
    public void notFoundElement() {
        Integer[] input = new Integer[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        assertEquals(-1, ParallelSearch.sort(input, 100));
    }
}