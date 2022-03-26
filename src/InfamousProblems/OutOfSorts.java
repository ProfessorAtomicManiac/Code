package InfamousProblems;

import java.io.*;
import java.util.Arrays;

/** http://www.usaco.org/index.php?page=viewproblem2&cpid=834
 *  Despite it looking simple and sort, it took me almost 2 hours to think of a solution
 *  and implement it. Had many headaches trying to think of the solution.
 *  Really surprised it worked first try!
 */

public class OutOfSorts {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter("sort.out");
        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++)
        {
            elements[i] = new Element(Long.parseLong(br.readLine()), i);
        }
        Arrays.sort(elements);
        int ret;
        for (int i = 0; i < n; i++)
        {
            if (elements[i].ind - i < 0) {
                ret = 0;
            }
            else {
                ret = Math.min(elements[i].ind - i, n - i - 1);
            }
            //System.err.println(elements[i].ind + "," + i + "   " + ret);
            ans = Math.max(ans, ret);
        }
        pw.println(ans+1);
        pw.close();
    }

    static class Element implements Comparable<Element> {
        long val;
        int ind;
        Element(long v, int i)
        {
            val = v; ind = i;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(val, o.val);
        }
    }
}