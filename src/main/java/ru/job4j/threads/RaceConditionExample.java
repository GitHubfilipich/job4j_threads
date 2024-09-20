package ru.job4j.threads;

public class RaceConditionExample {
    private int number = 0;

    public synchronized void increment() {
        System.out.println(Thread.currentThread().getName() + " start incr");
        for (int i = 0; i < 999999999; i++) {
            int current = number;
            int next = number + 1;
            if (current + 1 != next) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " finish incr");
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample raceCondition = new RaceConditionExample();
        Thread one = new Thread(raceCondition::increment);
        Thread two = new Thread(raceCondition::increment);
        one.start();
        two.start();
        one.join();
        two.join();
    }
}
