package ru.job4j;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    simpleBlockingQueue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " offer " + i);
            }
        }, "producer");
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                int value = 0;
                try {
                    value = simpleBlockingQueue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " poll " + value);
            }
        }, "consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}