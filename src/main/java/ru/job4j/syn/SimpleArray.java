package ru.job4j.syn;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private int size = 0;
    private Object[] array;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
        modCount++;
    }

    private void grow() {
        if (size == array.length) {
            this.array = Arrays.copyOf(array,
                    array.length * 2);
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    public void add(T model) {
        grow();
        array[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pos < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[pos++];
            }
        };
    }
}
