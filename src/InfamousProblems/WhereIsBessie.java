package InfamousProblems;

import java.io.*;
import java.util.*;

/**  http://www.usaco.org/index.php?page=viewproblem2&cpid=740
 *   Me being really stupid for 3 hours straight and procrastinating and watching youtube and not actually
 *   trying to solve. Made a huge jumble and read the solution
 *   You should do skeleton code first before writing an actual solution lmao
 */

public class WhereIsBessie {

    static char[][] grid, temp;
    static ArrayList<PCL> rects = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // 0-indexed
        BufferedReader br = new BufferedReader(new FileReader("where.in"));
        PrintWriter pw = new PrintWriter("where.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        grid = new char[n][n];
        for (int row = 0; row < n; row++)
        {
            String str = br.readLine();
            for (int col = 0; col < n; col++)
            {
                grid[row][col] = str.charAt(col);
            }
        }

        // brute force all rectangles
        for (int x1 = 0; x1 < n; x1++)
        {
            for (int y1 = 0; y1 < n; y1++)
            {
                for (int x2 = 0; x2 < n; x2++)
                {
                    for (int y2 = 0; y2 < n; y2++)
                    {
                        temp = new char[n][n];
                        if (isPCL(x1, y1, x2, y2))
                        {
                            //System.err.println(x1 + "," + y1 + "    " + x2 + "," + y2);
                            rects.add(new PCL(x1, y1, x2, y2));
                            for (int i = 0; i < rects.size()-1; i++)
                            {
                                if (PCL_IN_PCL(i, rects.size()-1)) {
                                    //System.err.println("Removing: " + rects.get(i).x1 + "," + rects.get(i).y1 + "   " + rects.get(i).x2 + " " + rects.get(i).y2);
                                    rects.remove(i);
                                    --i;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rects.size(); i++)
        {
            for (int j = 0; j < rects.size(); j++)
            {
                if (i != j && PCL_IN_PCL(i, j)) // if i is in j
                {
                    rects.remove(i);
                    --j;
                    --i;
                }
            }
        }
        pw.println(rects.size());
        pw.close();
    }

    static void dfs(int row, int col, char c, int x1, int y1, int x2, int y2)
    {
        if (col > x2 || col < x1 || row > y2 || row < y1)
            return;
        if (temp[row][col] != c)
            return;
        temp[row][col] = '-';
        dfs(row+1, col, c, x1, y1, x2, y2);
        dfs(row-1, col, c, x1, y1, x2, y2);
        dfs(row, col+1, c, x1, y1, x2, y2);
        dfs(row, col-1, c, x1, y1, x2, y2);
    }

    public static boolean isPCL(int x1, int y1, int x2, int y2) {
        TreeSet<Character> colors = new TreeSet<>();
        int[] comp = new int[26];
        for (int col = x1; col <= x2; col++)
        {
            for (int row = y1; row <= y2; row++)
            {
                temp[row][col] = grid[row][col];
                colors.add(temp[row][col]);
                if (colors.size() > 2)
                    return false;
            }
        }
        for (int col = x1; col <= x2; col++)
        {
            for (int row = y1; row <= y2; row++)
            {
                if (temp[row][col] != '-')
                {
                    comp[temp[row][col]-'A']++;
                    dfs(row, col, temp[row][col], x1, y1, x2, y2);
                }
            }
        }
        boolean one = false, many = false;
        for (int i = 0; i < comp.length; i++)
        {
            if (comp[i] == 1)
                one = true;
            else if (comp[i] > 1)
                many = true;
        }
        return one && many;
    }

    public static boolean PCL_IN_PCL(int i, int j) {
        return ((rects.get(i).x1 >= rects.get(j).x1) && (rects.get(i).y1 >= rects.get(j).y1)
                && (rects.get(i).x2 <= rects.get(j).x2) && (rects.get(i).y2 <= rects.get(j).y2));
    }

    static class PCL {
        int x1, y1, x2, y2;
        PCL (int a, int b, int c, int d)
        {
            x1 = a; y1 = b; x2 = c; y2 = d;
        }
    }
}