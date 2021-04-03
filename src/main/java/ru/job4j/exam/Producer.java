package ru.job4j.exam;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Producer {

    private final Storage storage = new Storage();

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final CountDownLatch downLatch = new CountDownLatch(Runtime.getRuntime().availableProcessors());

    //Method of reading URL and return JSONArray

    private JSONArray readUrl() throws IOException {
        //Source URL
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        URL link = new URL(url);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()))) {
            reader.lines().forEach(joiner :: add);
        }
        return new JSONArray(joiner.toString());
    }

    //Method of put JSON from source to storage

    public void putToStore(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            storage.add(array.getJSONObject(i));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("http.proxyHost", "192.168.111.102");
        System.setProperty("http.proxyPort", "3128");
        Producer producer = new Producer();
        producer.putToStore(producer.readUrl());
        while (!producer.storage.isEmpty()) {
            producer.executorService.execute(new Agregator(producer.storage, producer.downLatch));
            Thread.sleep(500);
        }
        producer.downLatch.await();
        producer.executorService.shutdown();
        System.out.println(producer.storage.getResult());
    }
}
