package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread load = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 101; i++) {
                            System.out.print("\rLoading : " + i  + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                       e.printStackTrace();
                    }
                }
        );
        load.start();
        System.out.println("Load");
    }
}
