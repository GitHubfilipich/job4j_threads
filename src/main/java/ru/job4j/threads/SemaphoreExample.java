package ru.job4j.threads;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Runnable task = () -> {
            try {
                System.out.println("Нить планирует выполнить задачу");
                semaphore.acquire();
                System.out.println("Нить выполнила задачу");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
        Thread.sleep(3000);
        semaphore.release(1);
    }
}
