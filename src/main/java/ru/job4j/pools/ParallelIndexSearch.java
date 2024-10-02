package ru.job4j.pools;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<V> extends RecursiveTask<Integer> {
    private final V[] array;
    private final int from;
    private final int to;
    private final V value;

    public ParallelIndexSearch(V[] array, int from, int to, V value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return getLinearResult();
        }
        int middle = (from + to) / 2;
        ParallelIndexSearch<V> leftSearch = new ParallelIndexSearch<>(array, from, middle, value);
        ParallelIndexSearch<V> rightSearch = new ParallelIndexSearch<>(array, middle + 1, to, value);
        leftSearch.fork();
        rightSearch.fork();
        return Math.max(leftSearch.join(), rightSearch.join());
    }

    private int getLinearResult() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (Objects.equals(array[i], value)) {
                result = i;
            }
        }
        return result;
    }

    public static <T> int indexOf(T[] array, T value) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, value));
    }
}
