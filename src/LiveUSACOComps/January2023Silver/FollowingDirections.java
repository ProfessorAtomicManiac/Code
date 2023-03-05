package LiveUSACOComps.January2023Silver;

import java.io.*;
import java.util.*;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1279
 * Surprised this was in silver, it was pretty easy. Still slow at impl cuz took about an hour in total
 * Full AC
 */

public class FollowingDirections {

    // -1 = right, -2 = down
    static long[][] grid;
    static int[][] cowsPerCell;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        grid = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String signs = st.nextToken();
            for (int j = 0; j < n; j++)
            {
                grid[i][j] = signs.charAt(j) == 'R' ? -1 : -2;
            }
            grid[i][n] = Long.parseLong(st.nextToken());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++)
        {
            grid[n][j] = Long.parseLong(st.nextToken());
        }

        cowsPerCell = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cowsPerCell[i][j] += 1;
                if (grid[i][j] == -1)
                    cowsPerCell[i][j + 1] += cowsPerCell[i][j];
                else
                    cowsPerCell[i + 1][j] += cowsPerCell[i][j];
            }
        }
        //printCows();
        pw.println(calculateCost());

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++)
        {

            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            // find the original path and subtract from all
            int currX = x;
            int currY = y;
            while (currX < n && currY < n)
            {
                if (grid[currY][currX] == -1)
                {
                    currX++;
                    cowsPerCell[currY][currX] -= cowsPerCell[y][x];
                } else {
                    currY++;
                    cowsPerCell[currY][currX] -= cowsPerCell[y][x];
                }
            }

            if (grid[y][x] == -1) grid[y][x] = -2;
            else grid[y][x] = -1;

            // find the new path and add to all
            currX = x;
            currY = y;
            while (currX < n && currY < n)
            {
                if (grid[currY][currX] == -1)
                {
                    currX++;
                    cowsPerCell[currY][currX] += cowsPerCell[y][x];
                } else {
                    currY++;
                    cowsPerCell[currY][currX] += cowsPerCell[y][x];
                }
            }
            pw.println(calculateCost());
        }
        //System.err.println();

        //printCows();
        pw.close();
    }

    static void printCows()
    {
        for (int i = 0; i < cowsPerCell.length; i++)
        {
            for (int j = 0; j < cowsPerCell.length; j++)
            {
                System.err.print(cowsPerCell[i][j] + " ");
            }
            System.err.println();
        }
    }

    public static long calculateCost()
    {
        long cost = 0;
        for (int i = 0; i < grid.length - 1; i++)
        {
            cost += cowsPerCell[i][cowsPerCell.length - 1] * grid[i][grid.length - 1];
        }
        for (int i = 0; i < grid.length - 1; i++)
        {
            cost += cowsPerCell[cowsPerCell.length - 1][i] * grid[grid.length - 1][i];
        }
        return cost;
    }
}