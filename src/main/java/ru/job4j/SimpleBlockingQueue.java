package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxLength;

    public SimpleBlockingQueue(int maxLength) {
        this.maxLength = maxLength;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == maxLength) {
            this.wait();
        }
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        T value = queue.poll();
        this.notifyAll();
        return value;
    }
}
