package ru.job4j.syn;

public interface Storage<T> {
     boolean add(T elem);

     boolean update(T elem);

     boolean remove(T elem);
}
