package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CasCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int currentCount;
        int newCount;
        do{
            currentCount = count.get();
            newCount = currentCount++;
        }while (!count.compareAndSet(currentCount,newCount));
    }

    public int get() {
        return count.get();
    }
}
