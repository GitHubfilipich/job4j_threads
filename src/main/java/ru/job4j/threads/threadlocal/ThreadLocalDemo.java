package ru.job4j.threads.threadlocal;

public class ThreadLocalDemo {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread first = new FirstThread();
        Thread first2 = new FirstThread();
        Thread second = new SecondThread();
        Thread second2 = new SecondThread();
        threadLocal.set("Это поток main.");
        System.out.println(threadLocal.get());
        first.start();
        first2.start();
        second.start();
        second2.start();
        first.join();
        first2.join();
        second.join();
        second2.join();
    }
}
