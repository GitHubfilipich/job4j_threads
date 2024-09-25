package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int value, newValue;
        do {
            value = count.get();
            newValue = value + 1;
        } while (!count.compareAndSet(value, newValue));
    }

    public int get() {
        return count.get();
    }
}
