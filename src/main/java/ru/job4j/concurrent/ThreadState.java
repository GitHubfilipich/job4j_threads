package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.printf("first: %s%n", first.getState());
        System.out.printf("second: %s%n", second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.printf("first: %s%n", first.getState());
            System.out.printf("second: %s%n", second.getState());
        }
        System.out.println("The job is done!");
        System.out.printf("first: %s%n", first.getState());
        System.out.printf("second: %s%n", second.getState());
    }
}
