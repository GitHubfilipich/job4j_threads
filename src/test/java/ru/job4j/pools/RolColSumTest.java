package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {
    @Test
    public void whenSumMatrix3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = {new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)};
        assertThat(RolColSum.sum(matrix)).containsExactly(expected);
    }

    @Test
    public void whenAsyncSumMatrix3() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = {new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)};
        assertThat(RolColSum.asyncSum(matrix)).containsExactly(expected);
    }

}