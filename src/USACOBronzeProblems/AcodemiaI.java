package USACOBronzeProblems;
// Comment out the package when submitting

/*
  For this problem I had to look at the editorial after at least 1.5 hours of trying because I still suck.
  The original approach was using a counting sort and manipulating values for the h value
  however I did not consider the fact that h can only be increased to h+1. This fact alone made the
  algorithm much easier to code.
  There were also other numerous problems with my original solution.
  The original solution is obviously better than this one as it doesn't use as much memory.
  AND IT STILL TOOK 2 ADDITIONAL HOURS TO DEBUG BECAUSE I DID IT TOO SLOPPILY, EVEN WHEN KNOWING THE TEST CASES.
 */

import java.util.Scanner;

public class AcodemiaI {
    static int hIndex(int[] count)
    {
        int h = 0;
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] >= i)
            {
                h = i;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        // reverse counting sort
        Scanner sc = new Scanner(System.in);
        int n, l;

        n = sc.nextInt(); l = sc.nextInt();
        int[] arr = new int[n];
        int[] count = new int[100001];
        for (int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
            count[arr[i]]++;
        }
        for (int i = 100001-1; i > 0; i--)
        {
            count[i-1] = count[i]+count[i-1];
        }

        int h = hIndex(count);
        //System.out.println((h+1) - count[h+1]);
        //System.out.println(count[h+1] - count[h]);
        //System.out.println(l);
        if ((h+1) - count[h+1] <= Math.abs(count[h+1] - count[h]) && (h+1) - count[h+1] <= l)
            System.out.println(h+1);
        else System.out.println(h);
    }
}
