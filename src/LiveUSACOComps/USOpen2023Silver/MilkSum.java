package LiveUSACOComps.USOpen2023Silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1326">...</a>
 * Got idea, just bad at solving edge cases such as "no shift", "shift on edge of array", etc
 * 7/10 test cases
 */
public class MilkSum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n]; // 0-indexed, problem is 1-indexed
        Cow[] sorted = new Cow[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            sorted[i] = new Cow(Long.parseLong(st.nextToken()), i);
            arr[i] = sorted[i].milk;
        }
        Arrays.sort(sorted);
        long[] prefix = new long[n+1];
        prefix[0] = 0;

        long total = 0;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] += prefix[i] + sorted[i].milk;
            total += (i + 1) * sorted[i].milk;
            //System.err.println(prefix[i]);
        }
        for (int i = 0; i < n + 1; i++) {

        }
        //System.err.println(prefix[n]);

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int ind = Integer.parseInt(st.nextToken()) - 1;
            long milk = Long.parseLong(st.nextToken());
            // binary search to find element
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l)/2;
                if (sorted[mid].milk < arr[ind]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            long newTotal = total;
            //System.err.println(newTotal);
            int remove = l;
            //System.err.println(remove + " " + sorted[remove].milk);
            newTotal -= (remove + 1) * arr[ind];

            l = 0; r = n - 1;
            while (l < r) {
                int mid = l + (r - l)/2;
                if (sorted[mid].milk <= milk) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            int insert = l;
            //System.err.println(newTotal);

            if (insert < remove + 1) {
                newTotal += prefix[remove - 1 + 1] - prefix[insert - 1 + 1];
                newTotal += (insert + 1) * milk;

            } else if (insert == remove + 1) {
                newTotal += (insert) * milk;
            } else {
                newTotal -= prefix[insert - 1 + 1] - prefix[remove + 1];
                newTotal += (insert) * milk;

            }
            //System.err.println(insert + " " + sorted[insert].milk);
            //System.err.println("Insert: " + insert + "  Remove: " + remove);
            //System.err.println(newTotal);

            pw.println(newTotal);
        }
        pw.close();
    }

    static class Cow implements Comparable<Cow>{
        long milk;
        int ind;
        Cow (long m, int i) {
            milk = m; ind = i;
        }

        @Override
        public int compareTo(Cow o) {
            return Long.compare(milk, o.milk);
        }
    }
}