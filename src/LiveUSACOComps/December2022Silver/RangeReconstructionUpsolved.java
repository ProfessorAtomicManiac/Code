package LiveUSACOComps.December2022Silver;

import java.io.*;
import java.util.StringTokenizer;

public class RangeReconstructionUpsolved {
    static int[][] ranges;
    static int[] arr;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        ranges = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = i; j < n; j++)
            {
                ranges[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arr = new int[n];

        int j = 0;
        for (; j < n; j++)
        {
            if (ranges[0][j] == 0)
                arr[j] = 0;
            else break;
        }
        if (j < n)
            arr[j] = ranges[0][j];

        add(j + 1, j, 0, false);
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                pw.println(arr[i]);
            else pw.print(arr[i] + " ");
        }
        pw.close();
    }

    // [back, 0, 0, ..., prev, 0, 0, ..., ind]
    static void add(int ind, int prev, int back, boolean prevIsMin)
    {
        if (ind >= n)
            return;
        //System.err.println("(" + prev + ", " + ind + ") " + back + " " + " Range: " + ranges[prev][ind] + " prev is minimum: " + prevIsMin);

        if (ranges[prev][ind] == 0)
        {
            arr[ind] = arr[prev];
            add(ind + 1, prev, back, prevIsMin);
        }
        else if (prevIsMin)
        {
            if (arr[back] - (arr[prev] - ranges[prev][ind]) == ranges[back][ind]) {
                arr[ind] = arr[prev] - ranges[prev][ind];
                add(ind + 1, ind, prev, true);
            }
            else {
                arr[ind] = arr[prev] + ranges[prev][ind];
                add(ind + 1, ind, prev, false);
            }
        } else {
            if (arr[prev] + ranges[prev][ind] - arr[back] == ranges[back][ind]) {
                arr[ind] = arr[prev] + ranges[prev][ind];
                add(ind + 1, ind, prev, false);
            }
            else {
                arr[ind] = arr[prev] - ranges[prev][ind];
                add(ind + 1, ind, prev, true);
            }
        }
    }
}