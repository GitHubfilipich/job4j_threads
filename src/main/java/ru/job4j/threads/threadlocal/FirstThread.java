package ru.job4j.threads.threadlocal;

public class FirstThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " before set " + ThreadLocalDemo.threadLocal.get());
        ThreadLocalDemo.threadLocal.set("Это поток 1. " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " after set " + ThreadLocalDemo.threadLocal.get());
    }
}
