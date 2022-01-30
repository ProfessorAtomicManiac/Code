package InfamousProblems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/** http://www.usaco.org/index.php?page=viewproblem2&cpid=645
 *  This is my first ever solution to a gold problem. Had to look at the editorial a few times though so it is
 *  not completely original.
 */

public class SplittingTheField {
    static Cow[] cows;
    static long max;
    static long min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("split.in"));
        PrintWriter pw = new PrintWriter("split.out");
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());
        // find the one rectangle that fits all by finding the max/min x and y
        cows = new Cow[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            cows[i] = new Cow(x, y);
        }
        long area = solve(n);
        transpose();
        area = Math.min(area, solve(n));
        pw.println(Math.max((cows[n-1].y - cows[0].y)*(max-min) - area, 0));
        //System.err.println("Area: " + area + "      Rectangle: " + (cows[n-1].y - cows[0].y)*(max-min));
        //pw.println((cows[n-1].y - cows[0].y)*(max-min) - area);
        pw.close();
    }

    public static long solve(int n)
    {
        Arrays.sort(cows);
        // Have a "prefix sum" of min/max
        TreeMap<Long, Long> maxX = new TreeMap<>();
        TreeMap<Long, Long> minX = new TreeMap<>();
        TreeMap<Long, Long> revMaxX = new TreeMap<>();
        TreeMap<Long, Long> revMinX = new TreeMap<>();
        // Iterate thru y-values and set as max y and calculate max/min x for the two rectangles formed
        max = Long.MIN_VALUE;
        min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++)
        {
            max = Math.max(max, cows[i].x);
            min = Math.min(min, cows[i].x);
            maxX.put(cows[i].y, max);
            minX.put(cows[i].y, min);
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for (int i = n-1; i >= 0; i--)
        {
            max = Math.max(max, cows[i].x);
            min = Math.min(min, cows[i].x);
            revMaxX.put(cows[i].y, max);
            revMinX.put(cows[i].y, min);
        }
        long area = (cows[n-1].y - cows[0].y)*(max - min);
        for (int i = 0; i < n-1; i++)
        {
            long x = maxX.get(cows[i].y) - minX.get(cows[i].y);
            long y = cows[i].y - cows[0].y;
            long ret = x*y;
            //System.err.println(y);
            if (maxX.higherKey(cows[i].y) == null)
            {
                x = 0;
                y = 0;
            } else {
                x = revMaxX.get(maxX.higherKey(cows[i].y)) - revMinX.get(maxX.higherKey(cows[i].y));
                y = cows[n - 1].y - maxX.higherKey(cows[i].y);
                //System.err.println(maxX.higherKey(cows[i].y));
                //System.err.println(x + "," + y);
            }
            //System.err.println(y);
            ret += x*y;
            area = Math.min(area, ret);
            //System.err.println(ret);
        }
        return area;
    }

    public static void transpose()
    {
        for (Cow cow : cows)
        {
            long temp = cow.x;
            cow.x = cow.y;
            cow.y = temp;
        }
    }

    static class Cow implements Comparable<Cow> {
        long x;
        long y;
        Cow(long _x, long _y)
        {
            x = _x; y = _y;
        }

        @Override
        public int compareTo(Cow o) {
            if (y == o.y)
                return Long.compare(x, o.x);
            return Long.compare(y, o.y);
        }
    }
}
