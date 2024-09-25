package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void whenThreadTo100Then100() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        thread1.start();
        thread1.join();
        assertThat(count.get()).isEqualTo(100);
    }

    @Test
    public void when2ThreadsTo100Then200() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(count.get()).isEqualTo(200);
    }
}