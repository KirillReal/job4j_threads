package ru.job4j.exam;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;

public class Agregator implements Runnable {
    private final Storage storage;

    private final CountDownLatch latch;

    public Agregator(Storage storage, CountDownLatch latch) {
        this.storage = storage;
        this.latch = latch;
    }

    //Method reading url and return JSONObject
    private JSONObject readUrl(String url) {
        URL link = null;
        try {
            link = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try {
            assert link != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()))) {
                reader.lines()
                        .forEach(joiner::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(joiner.toString());
    }

    @Override
    public void run() {
        JSONObject jsonObject = storage.get();
        JSONObject sourceDataUrl = readUrl(jsonObject.getString("sourceDataUrl"));
        JSONObject tokenDataUrl = readUrl(jsonObject.getString("tokenDataUrl"));
        JSONObject result = new JSONObject();
        result.put("id", jsonObject.getInt("id"));
        result.put("urlType", sourceDataUrl.getString("urlType"));
        result.put("videoUrl", sourceDataUrl.getString("videoUrl"));
        result.put("value", tokenDataUrl.getString("value"));
        result.put("ttl", tokenDataUrl.getInt("ttl"));
        storage.addToResult(result);
        latch.countDown();
    }
}
