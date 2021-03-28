package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSumAsync {

    public static class Sums {
        private final int rowSum;
        private final int colSum;
        /* Getter and Setter */

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RolColSumAsync.Sums sums = (Sums) o;
            return rowSum == sums.getRowSum() && colSum == sums.getColSum();
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

    }

    public static RolColSumAsync.Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        RolColSumAsync.Sums[] rsl = new RolColSumAsync.Sums[matrix.length];
        Map<Integer, CompletableFuture<RolColSumAsync.Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            rsl[key] = futures.get(key).get();
        }
        return rsl;
    }

    private static CompletableFuture<RolColSumAsync.Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int sRow = 0;
            int sCol = 0;
            for (int i = 0; i < matrix.length; i++) {
                sRow += matrix[index][i];
                sCol += matrix[i][index];
            }
            return new Sums(sRow, sCol);
        });
    }
}
