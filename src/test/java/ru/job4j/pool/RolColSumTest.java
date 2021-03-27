package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] input = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(RolColSum.sum(input), is(expected));
    }

    @Test
    public void sumAsync() throws ExecutionException, InterruptedException {
        final int[][] matrix =
                {
                        {5, 7, 3, 17},
                        {7, 0, 1, 12},
                        {8, 1, 2, 3},
                        {10, 0, 2, 1},
                };
        assertThat(RolColSumAsync.asyncSum(matrix)[0].getColSum(), is(30));
        assertThat(RolColSumAsync.asyncSum(matrix)[1].getRowSum(), is(20));
    }
}
