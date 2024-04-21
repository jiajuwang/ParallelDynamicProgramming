package main;

import java.util.ArrayList;
import java.util.List;

public class knapsack_sequential {


    public static int knapsack_sequential(int[] weights, int[] values, int maxWeight) {

        if (weights == null || values == null || weights.length != values.length || maxWeight < 0)
            throw new IllegalArgumentException("Invalid input");

        final int N = weights.length;
        int[][] DP = new int[N + 1][maxWeight + 1];

        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int sz = 1; sz <= maxWeight; sz++) {
                DP[i][sz] = DP[i - 1][sz];
                if (sz >= w && DP[i - 1][sz - w] + v > DP[i][sz]) DP[i][sz] = DP[i - 1][sz - w] + v;
            }
        }

        int sz = maxWeight;
        List<Integer> itemsSelected = new ArrayList<>();

        for (int i = N; i > 0; i--) {
            if (DP[i][sz] != DP[i - 1][sz]) {
                int itemIndex = i - 1;
                itemsSelected.add(itemIndex);
                sz -= weights[itemIndex];
            }
        }

        return DP[N][maxWeight];
    }

    public static void main(String[] args) {

        int capacity = 5;
        int[] V = {3, 4, 5, 6};
        int[] W = {2, 3, 4, 5};
        System.out.println(knapsack_sequential(W, V, capacity));

        capacity = 7;
        V = new int[] {2, 2, 4, 5, 3};
        W = new int[] {3, 1, 3, 4, 2};
        System.out.println(knapsack_sequential(W, V, capacity));
    }
}
