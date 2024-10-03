package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static Sums[] sum(int[][] matrix) {
        int length = matrix.length;
        Sums[] sums = new Sums[length];
        for (int i = 0; i < length; i++) {
            sums[i] = rowColSums(matrix, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int length = matrix.length;
        Sums[] sums = new Sums[length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> rowColSums(matrix, row));
    }

    private static Sums rowColSums(int[][] matrix, int row) {
        Sums sums = new Sums();
        for (int i = 0; i < matrix.length; i++) {
            sums.setRowSum(sums.getRowSum() + matrix[row][i]);
            sums.setColSum(sums.getColSum() + matrix[i][row]);
        }
        return sums;
    }
}
