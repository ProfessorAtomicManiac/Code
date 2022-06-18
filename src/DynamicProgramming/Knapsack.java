package DynamicProgramming;

import java.io.*;
import java.util.*;

/**
 * This was done in Hackerrank's IDE, that is why everything is kinda weird.
 * 0/1 Knapsack problem: https://www.hackerrank.com/challenges/5680/problem
 */

class Result {

    /*
     * Complete the 'unboundedKnapsack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int unboundedKnapsack(int k, List<Integer> arr) {
        // Write your code here
        int[] dp = new int[k+1]; // 0 = cannot sum to, 1 = can sum to
        dp[0] = 1;
        // add each element
        for (Integer integer : arr) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[j] == 1) {
                    if (j + integer <= k) {
                        dp[j + integer] = 1;
                    }
                }
            }
        }
        for (int i = k; i >= 0; i--)
        {
            if (dp[i] == 1)
                return i;
        }
        return 0;
    }

}

public class Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        while (t > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++)
            {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            pw.println(Result.unboundedKnapsack(k, arr));
            --t;

        }
        pw.close();
    }
}
