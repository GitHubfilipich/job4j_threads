package ru.job4j;

public final class Cache {
    private static Cache cache;

    public static Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}