package InfamousProblems;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ModernArt {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("art.in"));
        PrintWriter pw = new PrintWriter("art.out");
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
            {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (set.size() <= 2)
                    set.add(grid[i][j]);
            }
        }
        //print(grid);
        if (set.size() == 2)
        {
            pw.println(n*n - 1);
        } else {
            int[][] prefix = new int[n + 2][n + 2];
            int[] top = new int[n * n + 1];
            int[] bot = new int[n * n + 1];
            int[] left = new int[n * n + 1];
            int[] right = new int[n * n + 1];

            // find minimum and maximum
            // top to down
            for (int i = 1; i <= n; i++) {
                // left to right
                for (int j = 1; j <= n; j++) {
                    if (top[grid[i - 1][j - 1]] == 0)
                        top[grid[i - 1][j - 1]] = i;
                }
            }

            // bot to up
            for (int i = n; i > 0; i--) {
                // left to right
                for (int j = 1; j <= n; j++) {
                    if (bot[grid[i - 1][j - 1]] == 0)
                        bot[grid[i - 1][j - 1]] = i;
                }
            }

            // left to right
            for (int i = 1; i <= n; i++) {
                // top to bot
                for (int j = 1; j <= n; j++) {
                    if (left[grid[j - 1][i - 1]] == 0)
                        left[grid[j - 1][i - 1]] = i;
                }
            }

            // right to left
            for (int i = n; i > 0; i--) {
                // top to bot
                for (int j = 1; j <= n; j++) {
                    if (right[grid[j - 1][i - 1]] == 0)
                        right[grid[j - 1][i - 1]] = i;
                }
            }

            // 2d-prefix
            for (int i = 0; i < n * n; i++) {
                // rows/cols are 1-indexed
                if (top[i + 1] != 0 && left[i + 1] != 0)
                    prefix[top[i + 1]][left[i + 1]]++;
                if (top[i + 1] != 0 && right[i + 1] != 0)
                    prefix[top[i + 1]][right[i + 1] + 1]--;
                if (bot[i + 1] != 0 && left[i + 1] != 0)
                    prefix[bot[i + 1] + 1][left[i + 1]]--;
                if (bot[i + 1] != 0 && right[i + 1] != 0)
                    prefix[bot[i + 1] + 1][right[i + 1] + 1]++;
            }

            //System.err.println();
            //print(prefix);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    prefix[i][j] += (prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1]);
                }
            }
            //System.err.println();
            //print(prefix);

            set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (prefix[i][j] > 1)
                        set.add(grid[i - 1][j - 1]);
                }
            }
            pw.println(n * n - set.size());
            //print();
        }
        pw.close();
    }

    static void print(int[][] grid)
    {
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.err.print(ints[j] + " ");
            }
            System.err.println();
        }
    }
}