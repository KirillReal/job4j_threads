package ru.job4j.concurrent;

import java.util.Arrays;

public class AreSame {
    public static boolean comp(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            if(a[i] * a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}


