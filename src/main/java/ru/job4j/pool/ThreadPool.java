package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for(Thread threadUniq : threads) {
            threadUniq.interrupt();
            try {
                threadUniq.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.work(new Thread(
                    () -> System.out.println(finalI + "task")
            ));
        }
        threadPool.shutdown();
    }
}
