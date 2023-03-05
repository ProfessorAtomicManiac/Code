package LiveUSACOComps.January2023Silver;

import java.io.*;
import java.util.*;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1280
 * Zero IQ moment
 */

public class MooRouteUpsolved {

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int direction = 1;
        int currInd = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (arr[0] != 0)
        {
            currInd += direction;
            //System.err.println(currInd);
            if (direction == 1)
                arr[currInd - 1]--;
            else
                arr[currInd]--;
            ans.add(direction);
            // check if the currInd is going out of bounds
            if (!withinBounds(currInd + direction))
                direction *= -1;
            else if (direction == 1 && arr[currInd] == 0)
                direction *= -1;
            // check if the next ind is 1 and act accordingly (should only happen when going left)
            if (direction == -1 && arr[currInd + direction] == 1 && (currInd != n && arr[currInd] > 0))
                direction *= -1;
        }

        for (int i : ans)
        {
            if (i == 1)
                pw.print('R');
            else
                pw.print('L');
        }
        pw.close();
    }

    static boolean withinBounds(int ind)
    {
        return ind >= 0 && ind <= n;
    }
}