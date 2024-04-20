package main;
import java.util.concurrent.atomic.AtomicInteger;


public class Knapsack extends LLP {
    private int[] weights;
    private int[] values;
    private int capacity;

    public Knapsack(int[] weights, int[] values, int capacity) {
        super(capacity + 1); // knapsack size
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;

        // each index i in G represents the maximum profit that can be achieved with a knapsack capacity of i
        for (int i = 0; i <= capacity; i++) {
            // by default, the maximum profit that can be achieved with zero items or zero capacity is 0
            this.G[i] = new AtomicInteger(0);
        }
    }

    @Override
    public boolean forbidden(int j) {
        // check knapsack capacity j if it can be improved

        // no capacity means can't be improved
        if (j == 0) return false;

        // check all available items to see if including any can increase profit at capacity j
        boolean isForbidden = false;
        for (int i = 0; i < weights.length; i++) {
            if (j >= weights[i]) {
                int newProfit = G[j - weights[i]].get() + values[i];
                if (newProfit > G[j].get()) {
                    isForbidden = true;
                    break;
                }
            }
        }
        return isForbidden;
    }

    public void advance(int j) {
        // try adding each item to current capacity j and update if higher profit is found
        for (int i = 0; i < weights.length; i++) {
            if (j >= weights[i]) {
                int newProfit = G[j - weights[i]].get() + values[i];
                System.out.println("Considering item " + i + " at capacity " + j + ": newProfit = " + newProfit + ", current G[j] = " + G[j].get());
                G[j].getAndUpdate(x -> Math.max(x, newProfit));
            }
        }
    }


    public int getSolution() {
        return G[capacity].get();
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 5, 6};
        int[] values = {1, 6, 18, 22};
        int capacity = 10;
        Knapsack knapsack = new Knapsack(weights, values, capacity);
        knapsack.solve();
        // prints `Maximum profit: 36` with this test case which is wrong, should be 29...?
        System.out.println("Maximum profit: " + knapsack.getSolution());
    }

}
















