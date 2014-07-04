import java.util.*;

class KNAPSACK {

	public static int weight, items;
	public static int[] weights, value;
	public static int[][] memo;                             // memo[itemID][remainingWeight]

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		weight = in.nextInt();
		items = in.nextInt();
		weights = new int[items];
		value = new int[items];
		memo = new int[items + 2][weight + 2];

		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}

		for (int i = 0; i < items; i++) {
			weights[i] = in.nextInt();
			value[i] = in.nextInt();
		}

		System.out.print(val(0, weight));
	}

	public static int val(int id, int remainingWeight) {
		if (remainingWeight == 0 || id == items) {             // if there's no more space or we run out of items, return 0
			return 0;
		} 

		if (memo[id][remainingWeight] != -1) {                 // if we already have a value for this id and weight, return it
			return memo[id][remainingWeight];
		}

		if (weights[id] > remainingWeight) {                   // if we cannot fit this item, move to the next
			return val(id + 1, remainingWeight);
		}

		memo[id][remainingWeight] = Math.max(val(id + 1, remainingWeight), value[id] + val(id + 1, remainingWeight - weights[id]));
		return memo[id][remainingWeight];                      // either take the item, or move to the next and set it's value to the
	}                                                              // corresponding memo value for future use
}
