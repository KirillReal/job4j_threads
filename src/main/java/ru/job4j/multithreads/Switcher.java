package ru.job4j.multithreads;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier slaveBarrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    slaveBarrier.doneMaster();
                    while (true) {
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    slaveBarrier.doneSlave();
                    //slaveBarrier.doneMaster();
                    while (true) {
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}

