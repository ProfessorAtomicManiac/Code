package DynamicProgramming;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * This was done in Hackerrank's IDE, which is why everything is weird
 * Longest Common Subsequence: https://www.hackerrank.com/challenges/5235/problem
 * This problem was much more of a hassle than I expected
 * The solution is probably pretty atrocious
 */

public class LongestCommonSubsequence {

    /*
     * Complete the 'longestCommonSubsequence' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    static int[][] dp;
    public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
        // dp[i][j] of max length ending at element i in a and j in b
        dp = new int[a.size()][b.size()];
        // dp[i][j] = { dp[i-1][j-1] + 1 if a[i] = b[i]
        //            { Math.max(dp[i-1][j], dp[i][j-1]) otherwise
        for (int i = 0; i < a.size(); i++)
        {
            for (int j = 0; j < b.size(); j++)
            {
                if (a.get(i).equals(b.get(j))) dp[i][j] = j-1 >= 0 && i-1 >= 0 ? dp[i-1][j-1] + 1 : 1;
                else {
                    if(i - 1 >= 0) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                    if(j - 1 >= 0) dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        int j = b.size()-1;
        for (int i = a.size()-1; i > 0; i--)
        {
            if (j < 0)
                break;
            int temp = dp[i][j];
            if (temp == 0)
                break;

            if (dp[i - 1][j] != temp)
            {
                for (; j >= 0; j--)
                    if (a.get(i).equals(b.get(j)))
                    {
                        --j;
                        break;
                    }
                //System.err.println(a.get(i) + " " + i);
                list.add(0, a.get(i));
            }
        }
        if (j >= 0 && dp[0][j] > 0)
            list.add(0, b.get(j));
        return list;
    }
    public static void print() {
        for (int[] row : dp) {
            for (int i : row) {
                System.err.print(i + " ");
            }
            System.err.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = longestCommonSubsequence(a, b);
        //print();
        pw.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        pw.close();
    }
}

