package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private volatile boolean masterWorking = true;

    public synchronized void tryMaster() {
        while(!masterWorking) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void trySlave() {
        while (masterWorking) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doneMaster() {
        masterWorking = false;
        notifyAll();
    }

    public synchronized void doneSlave() {
        masterWorking = true;
        notifyAll();
    }
}
