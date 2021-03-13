package ru.job4j.concurrent;

public class WgetInterrupted {
    public static void main(String[] args) {
        Thread load = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            for (int i = 0; i < 101; i++) {
                                System.out.print("\rLoading : " + i  + "%");
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        load.start();
        System.out.println("Load");
    }
}
