package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CasCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer currentCount;
        int newCount;
        do{
            currentCount = count.get();
            newCount = currentCount + 1;
        }while (!count.compareAndSet(currentCount,newCount));
    }

    public int get() {
        return count.get();
    }
}
