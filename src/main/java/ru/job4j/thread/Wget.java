package ru.job4j.thread;

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
        try (var input = new URL(url).openStream()) {
            var dataBuffer = new byte[512];
            var downloadAt = System.nanoTime();
            while (input.read(dataBuffer, 0, dataBuffer.length) != -1) {
                var realSpeed = 512 * 1000000 / (System.nanoTime() - downloadAt);
                if (realSpeed > speed) {
                    try {
                        Thread.sleep(realSpeed / speed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                downloadAt = System.nanoTime();
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
