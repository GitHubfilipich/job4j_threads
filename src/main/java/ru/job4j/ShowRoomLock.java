package ru.job4j;

public class ShowRoomLock {
    /* Монитор - это объект ShowRoomLock */
    public synchronized void lockOfInstance() {
        synchronized (this) {
            System.out.println();
        }
    }

    /* Монитор будет сам класс ShowRoomLock */
    public static synchronized void lockOfClass() {
        synchronized (ShowRoomLock.class) {
            System.out.println();
        }
    }
}
