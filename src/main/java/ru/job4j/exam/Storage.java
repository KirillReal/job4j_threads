package ru.job4j.exam;

import net.jcip.annotations.ThreadSafe;
import org.json.JSONObject;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ThreadSafe
public class Storage {
    //Queue from source URL
    private final Queue<JSONObject> queue = new ConcurrentLinkedQueue<>();
    //Result queue for aggregate data
    private final Queue<JSONObject> result = new ConcurrentLinkedQueue<>();

    public synchronized void add(JSONObject jsObject) {
        queue.offer(jsObject);
        notifyAll();
    }

    public synchronized JSONObject get() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }

    public void addToResult(JSONObject object) {
        result.offer(object);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Queue<JSONObject> getResult() {
        return result;
    }
}
