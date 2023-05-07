package LiveUSACOComps.USOpen2023Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1326">...</a>
 * Full solution
 * Literally all I did was to use Arrays.binarySearch() and got super confused
 * what it actually returned. Once confusion is fixed, got confused with cases
 * regarding the edge of the array, which was stupid. Wasted a lot of time
 * debugging this, but it worked out. Definitely could be better and writing out
 * each "case" really helped.
 */
public class MilkSumUpsolved {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        long[] sorted = new long[n];
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted);

        long sum = 0;
        long[] prefix = new long[n + 1];
        prefix[0] = 0;
        for (int i = 1; i <= n; i++) {
            sum += i * sorted[i - 1];
            prefix[i] += sorted[i - 1];
            prefix[i] += prefix[i - 1];
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int ind = Integer.parseInt(st.nextToken()) - 1;
            long newVal = Long.parseLong(st.nextToken());
            long origVal = arr[ind];
            // find original val in array
            int origInd = Arrays.binarySearch(sorted, origVal) + 1;
            //System.err.println("OrigInd: " + origInd);

            // find index of first element > than new val (goes from 1 to n)
            int newInd = Arrays.binarySearch(sorted, newVal);
            // if newInd > 0, then there already exists an element with the same val
            if (newInd < 0)
                newInd = Math.abs(newInd) - 1;
            newInd++;
            //System.err.println("NewInd: " + newInd);

            long ans = sum;
            //System.err.println(ans);
            ans -= (origInd) * origVal;
            //System.err.println(ans);

            if (newInd == origInd || newInd == origInd + 1) {
                // if the new index doesn't change
                ans += (origInd) * newVal;
            } else if (newInd > origInd) {
                long temp = prefix[newInd - 1] - prefix[origInd];
                //System.err.println(temp);
                ans -= temp;
                ans += (newInd - 1) * newVal;
            } else {
                //System.err.println("bruh");
                long temp = prefix[origInd - 1] - prefix[newInd - 1];
                //System.err.println(temp);
                ans += temp;
                ans += (newInd) * newVal;
            }
            pw.println(ans);
        }
        pw.close();
    }
}
