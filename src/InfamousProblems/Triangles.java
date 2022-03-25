package InfamousProblems;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/* http://www.usaco.org/index.php?page=viewproblem2&cpid=1015
 * Surprised that it worked first try despite having so many headaches coding this solution
 * Took like 2+ hours, maybe near 3 hours
 */

public class Triangles {

    static final int mod = 1000000007;
    public static void main(String[] args) throws IOException {
        // 0-indexed
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter("triangles.out");

        // input and store the points in "adjacent lists" of x's and y's
        TreeMap<Integer, TreeSet<Integer>> dpx = new TreeMap<>();
        TreeMap<Integer, TreeSet<Integer>> dpy = new TreeMap<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            TreeSet<Integer> set;
            if (!dpx.containsKey(x))
            {
                set = new TreeSet<>();
            } else {
                set = dpx.get(x);
            }
            set.add(y);
            dpx.put(x, set);

            if (!dpy.containsKey(y))
            {
                set = new TreeSet<>();
            } else {
                set = dpy.get(y);
            }
            set.add(x);
            dpy.put(y, set);
        }

        // Record the previously calculated heights/widths
        HashMap<Point, Long> heights = new HashMap<>();
        HashMap<Point, Long> widths = new HashMap<>();


        // Iterate through the points
        for (int i : dpx.keySet())
        {
            boolean firstY = true;
            int indY = 0;
            for (int j : dpx.get(i)) // height
            {
                if (firstY)
                {
                    long height = 0;
                    for (int y : dpx.get(i))
                        height += Math.abs(j - y);
                    height %= mod;
                    heights.put(new Point(i, j), height);
                    ++indY;
                    firstY = false;
                    //System.err.println(i + "," + j + "   Height: " + height);
                } else {
                    assert dpx.get(i).lower(j) != null;
                    long dif = Math.abs(j - dpx.get(i).lower(j));
                    long height = heights.get(new Point(i, dpx.get(i).lower(j))) + (2L * indY - dpx.get(i).size()) * dif;
                    height %= mod;
                    heights.put(new Point(i, j), height);
                    //System.err.println(i + "," + j + "   Height: " + height);
                    ++indY;
                }
            }
        }

        //System.err.println();
        // Iterate through the points
        for (int i : dpy.keySet())
        {
            boolean firstX = true;
            int indX = 0;
            for (int j : dpy.get(i)) // width
            {
                if (firstX)
                {
                    long width = 0;
                    for (int x : dpy.get(i))
                        width += Math.abs(j - x);
                    width %= mod;
                    widths.put(new Point(j, i), width);
                    ++indX;
                    firstX = false;
                    //System.err.println(j + "," + i + "   Width: " + width);
                } else {
                    assert dpy.get(i).lower(j) != null;
                    long dif = Math.abs(j - dpy.get(i).lower(j));
                    long width = widths.get(new Point(dpy.get(i).lower(j), i)) + (2L * indX - dpy.get(i).size()) * dif;
                    width %= mod;
                    widths.put(new Point(j, i), width);
                    //System.err.println(j + "," + i + "   Width: " + width);
                    ++indX;
                }
            }
        }

        long ans = 0;
        for (int i : dpx.keySet()) {
            for (int j : dpx.get(i)) // height
            {
                ans += heights.get(new Point(i, j)) * widths.get(new Point(i, j));
                ans %= mod;
            }
        }
        pw.println(ans);
        pw.close();
    }
}