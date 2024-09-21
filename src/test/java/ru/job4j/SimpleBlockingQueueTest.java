package ru.job4j;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                simpleBlockingQueue.offer(i);
                System.out.println(Thread.currentThread().getName() + " offer " + i);
            }
        }, "producer");
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                int value = simpleBlockingQueue.poll();
                System.out.println(Thread.currentThread().getName() + " poll " + value);
            }
        }, "consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}