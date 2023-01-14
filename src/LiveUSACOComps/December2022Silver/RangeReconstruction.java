package LiveUSACOComps.December2022Silver;

import java.io.*;
import java.util.StringTokenizer;

public class RangeReconstruction {
    static int[][] ranges;
    static int[] arr;
    static int[] min;
    static int[] max;
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

        arr[0] = 0;
        min = new int[n];
        max = new int[n];
        add(0);
        for (int i = 0; i < arr.length; i++)
        {
            if (i == arr.length - 1)
                pw.println(arr[i]);
            else pw.print(arr[i] + " ");
        }
        pw.close();
    }

    static int add(int ind)
    {
        if (ind == n - 1) return 1;
        int diff = ranges[ind][ind + 1];
        // add
        arr[ind + 1] = arr[ind] + diff;
        //System.err.println(ind + ": " + arr[ind + 1]);
        boolean works = true;
        for (int i = 0; i < ind; i++)
        {
            //System.err.println(i + ", " + (ind + 1) + " " + ranges[i][ind + 1] + "   " + (arr[ind + 1] - min[ind]));
            if (arr[ind + 1] > max[i] && arr[ind + 1] - min[i] != ranges[i][ind + 1])
            {
                works = false;
                break;
            }
        }
        if (works) {
            for (int i = 0; i <= ind + 1; i++)
                max[i] = Math.max(max[i], arr[ind + 1]);
            int ret = add(ind + 1);
            if (ret == 1)
                return 1;
        }

        // sub
        arr[ind + 1] = arr[ind] - diff;
        //System.err.println(ind + ": " + arr[ind + 1]);
        works = true;
        for (int i = 0; i < ind; i++)
        {
            if (arr[ind + 1] < min[i] && max[i] - arr[ind + 1] != ranges[i][ind + 1])
            {
                works = false;
                break;
            }
        }
        if (works) {
            for (int i = 0; i <= ind + 1; i++)
                min[i] = Math.min(min[i], arr[ind + 1]);
            int ret = add(ind + 1);
            if (ret == 1)
                return 1;
        }
        return -1;
    }
}