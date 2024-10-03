package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

class ParallelIndexSearchTest {
    @Test
    public void whenSearchInteger() {
        Integer[] array = {10, 15, 20, 30, 15, 20, 30};
        assertThat(ParallelIndexSearch.indexOf(array, 15)).isIn(1, 4);
    }

    @Test
    public void whenSearchIntegerRecursive() {
        Integer[] array = IntStream.range(0, 1000).boxed().toArray(Integer[]::new);
        assertThat(ParallelIndexSearch.indexOf(array, 50)).isEqualTo(50);
    }

    @Test
    public void whenSearchString() {
        String[] array = {"job", "4", "j", "job", "4", "j", "job", "4", "j", "job", "4", "j"};
        assertThat(ParallelIndexSearch.indexOf(array, "j")).isIn(2, 5, 8, 11);
    }

    @Test
    public void whenSearchStringNotFound() {
        String[] array = {"job", "4", "j", "job", "4", "j", "job", "4", "j", "job", "4", "j"};
        assertThat(ParallelIndexSearch.indexOf(array, "java")).isEqualTo(-1);
    }
}