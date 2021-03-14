package ru.job4j.concurrent;

import junit.framework.TestCase;

public class AreSameTest extends TestCase {

    public void testComp() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};

        assertTrue(AreSame.comp(a, b));
    }
}