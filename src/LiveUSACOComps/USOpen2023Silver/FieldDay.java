package LiveUSACOComps.USOpen2023Silver;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1327">...</a>
 * Was able to solve subtasks 1 and 2, except for one test case
 * Felt like a genius when I realized you could use the fact that C <= 18
 * and found a solution idea
 * Still wasn't able to ak it cuz I'm still dumb
 * 9/20 test cases
 */
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class FieldDay {

    static int target;
    static HashSet<Integer> elements;
    static int maxC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int c, n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        maxC = c;
        n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        elements = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // to int binary
            String str = br.readLine();
            int ele = 0;
            for (int j = 0; j < c; j++) {
                if (str.charAt(j) == 'G')
                {
                    ele += Math.pow(2, c - j - 1);
                }
            }
            arr[i] = ele;
            //System.err.println(Integer.toBinaryString(ele));
            elements.add(ele);
        }
        //System.err.println();
        for (int i = 0; i < n; i++) {
            // iterate thru every possible ans
            //System.err.println();
            target = arr[i] ^ ((int)Math.pow(2, c) - 1);
            for (int j = 0; j <= c; j++) {
                if (generate(j, 0)) {
                    pw.println(c - j);
                    break;
                }
            }
        }

        pw.close();
    }

    static boolean generate(int c, int ind) {
        if (ind > maxC)
            return false;
        if (c == 0) {
            //System.err.println(c + " " + Integer.toBinaryString(target));
            return elements.contains(target);
        }
        target ^= (int)Math.pow(2, maxC - ind - 1);
        if (generate(c - 1, ind + 1))
            return true;
        target ^= (int)Math.pow(2, maxC - ind - 1);
        return generate(c, ind + 1);
    }
}
