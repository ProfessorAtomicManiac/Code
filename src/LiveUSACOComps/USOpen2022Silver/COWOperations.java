package LiveUSACOComps.USOpen2022Silver;

import java.io.*;
import java.util.*;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1232
 * First AC on a problem in Silver during a contest
 * 40 minutes flat to think of solution and implementation
 */

public class COWOperations {

    public static void main(String[] args) throws IOException {
        // 0-indexed
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String str = br.readLine();
        // create prefix sum of the number of C's, O's, W's
        int[] C = new int[str.length()+1];
        int[] O = new int[str.length()+1];
        int[] W = new int[str.length()+1];
        for (int i = 1; i <= str.length(); i++)
        {
            switch(str.charAt(i-1))
            {
                case 'C':
                    C[i] = 1;
                    break;
                case 'O':
                    O[i] = 1;
                    break;
                case 'W':
                    W[i] = 1;
                    break;
            }
            C[i] += C[i-1];
            O[i] += O[i-1];
            W[i] += W[i-1];
        }
        int n = Integer.parseInt(br.readLine());
        // input all queries
        for (int q = 0; q < n; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = C[b] - C[a-1];
            int o = O[b] - O[a-1];
            int w = W[b] - W[a-1];
            //System.err.println("C: " + c + " O: " + o + " W: " + w);
            // if c's = odd or (c's = even and o's = odd and w's = odd)
            if ((c % 2 == 1 && o % 2 == 0 && w % 2 == 0) || (c % 2 == 0 && w % 2 == 1 && o % 2 == 1))
                pw.print('Y');
            else pw.print('N');
            // print true
            // else print false
        }
        pw.close();
    }
}