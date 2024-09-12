package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var file = new File("tmp.xml");
        try (var input = new URL(url).openStream(); var output = new FileOutputStream(file)) {
            var dataBuffer = new byte[512];
            int bytesRead;
            int totalBytesRead = 0;
            var downloadAt = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                if (totalBytesRead >= speed && System.currentTimeMillis() - downloadAt < 1) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    downloadAt = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        if (args[0].isBlank()) {
            throw new IllegalArgumentException("Incorrect first parameter");
        }
        if (args[1].isBlank() || !args[1].matches("\\d+") || Integer.parseInt(args[1]) == 0) {
            throw new IllegalArgumentException("Incorrect second parameter");
        }
    }
}
