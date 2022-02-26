package InfamousProblems;

import java.io.*;
import java.util.Arrays;

/** http://www.usaco.org/index.php?page=viewproblem2&cpid=597
 *  This is my second ever gold solution. Pretty much original if you don't consider a slight glimpse at the editorial
 *  Holy crap it took me 2+ hours, close to three maybe, I'm so not ready for silver lmfao
 */

public class AngryCows {

    static long[] arr;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("angry.out"));
        int n = Integer.parseInt(br.readLine());
        arr = new long[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);
        long ans = search();
        pw.println(ans/10 + "." + ans%10);
        pw.close();
    }

    // Binary search on 0.0 to Xn - X1 (one decimal place)
    public static long search()
    {
        long beg = 0; long end = (arr[arr.length-1]-arr[0])*10;
        while (beg < end)
        {
            //System.err.println("Optimal Radius: " + beg + ", " + end);
            long mid = beg + (end - beg)/2;
            if (searchRad(mid))
            {
                end = mid;
            } else {
                beg = mid + 1;
            }
            //System.err.println("Optimal Radius: " + beg + ", " + end);
            //System.err.println();
        }
        return beg;
    }

    // Binary search on X1 to Xn (0.0)
    public static boolean searchRad(long rad)
    {
        //System.err.println(rad);
        long beg = arr[0]*10; long end = arr[arr.length - 1]*10;
        //System.err.println("Optimal target: " + beg + ", " + end);
        while (beg < end)
        {
            long mid = beg + (end - beg+1)/2;
            if (test(rad, mid)) {
                beg = mid;
            } else {
                end = mid - 1;
            }
            //System.err.println("Optimal target: " + beg + ", " + end);
            //System.err.println();
        }

        // check if everything on the right is hit for this optimal impact
        int currHay = -1;
        for (int i = arr.length - 1; i >= 0; i--)
        {
            if (arr[i]*10 <= end + rad && arr[i]*10 >= end) {
                currHay = i;
                break;
            }
        }
        if (currHay == -1)
            return false;
        rad -= 10;
        int prevHay = currHay;
        while (prevHay < arr.length - 1 && rad > 0) {
            //System.err.println(prevHay);
            while (currHay < arr.length - 1 && arr[currHay + 1]*10 <= arr[prevHay]*10 + rad) {
                ++currHay;
            }
            if (currHay == prevHay) {
                //System.err.println(-1);
                return false;
            }
            prevHay = currHay;
            rad -= 10;
        }
        return currHay == arr.length - 1;
    }

    // if everything on the left is hit
    public static boolean test(long rad, long hit)
    {
        //System.err.println("Target: " + hit + "   Radius: " + rad);
        int currHay = -1;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i]*10 >= hit - rad && arr[i]*10 <= hit) {
                currHay = i;
                break;
            }
        }
        if (currHay == -1)
            return false;
        rad -= 10;
        int prevHay = currHay;

        while (prevHay > 0 && rad > 0) {
            //System.err.println("PrevHay: " + arr[prevHay]*10 + "    Rad: " + rad);
            while (currHay > 0 && arr[currHay - 1]*10 >= arr[prevHay]*10 - rad) {
                --currHay;
            }
            if (currHay == prevHay) {
                //System.err.println(-1);
                return false;
            }
            prevHay = currHay;
            rad -= 10;
        }
        //System.err.println("PrevHay: " + arr[prevHay]*10);
        return currHay == 0;
    }
}