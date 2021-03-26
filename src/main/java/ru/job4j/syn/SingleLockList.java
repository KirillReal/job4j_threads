package ru.job4j.syn;

import java.util.Iterator;

public class SingleLockList<T> implements Iterable<T> {
    private final SimpleArray<T> arrayList = new SimpleArray<>(16);

    public void add(T value) {
        arrayList.add(value);
    }

    public T get(int index) {
        return arrayList.get(index);
    }

    private synchronized SimpleArray<T> copy() {
        SimpleArray<T> duplicate = new SimpleArray<>(16);
        arrayList.forEach(duplicate::add);
        return duplicate;
    }

    @Override
    public Iterator<T> iterator() {
        return copy().iterator();
    }
}
