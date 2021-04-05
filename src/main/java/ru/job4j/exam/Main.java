package ru.job4j.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            CompletableFuture<String> processing = CompletableFuture.supplyAsync(() ->
                    new Main().connect("id", "sourceDataUrl", "tokenDataUrl"))
                    .thenApply((result) -> new Producer().getJson(result));
            String response = processing.get();
            LOGGER.info(response);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String[] connect(String... params) {
        try {
            URL url = new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
            InputStream input = url.openStream();
            byte[] buffer = input.readAllBytes();
            String response = new String(buffer);
            return new Storage().getDataUrl(response, params);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return new String[0];
    }
}
