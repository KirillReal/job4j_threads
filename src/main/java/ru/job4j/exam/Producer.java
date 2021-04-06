package ru.job4j.exam;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Producer {

    private static final Logger LOGGER =  LoggerFactory.getLogger(Producer.class.getName());
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ConcurrentLinkedQueue<Future<Camera>> futures =
            new ConcurrentLinkedQueue<>();

    public String getJson(String[] response) {
        Arrays.stream(response).map(dataUrl -> executorService
                .submit(new Agregator(dataUrl))).forEach(futures::offer);
        ArrayList<Camera> arrayList = new ArrayList<>();
        try {
            while (!futures.isEmpty()) {
                arrayList.add(futures.poll().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        executorService.shutdown();
        return new Storage().getJson(arrayList.toArray(Camera[]::new));
    }
}
