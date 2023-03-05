package LiveUSACOComps.February2023Silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1303
 * Coded it in an hour and 15-30 mins ish. Reason why it took longer than it should have
 * was cuz I had wrong idea initially and I was slow at debugging
 */

public class Cowlibi {

    static Ele[] alibi;
    static Ele[] gardens;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int g, n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        g = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        gardens = new Ele[g];
        for (int i = 0; i < g; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            gardens[i] = new Ele(x, y, t);
        }
        Arrays.sort(gardens);
        alibi = new Ele[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            alibi[i] = new Ele(x, y, t);
        }
        Arrays.sort(alibi);
        //printArr(gardens);
        //printArr(alibi);


        int j = 0;
        int ans = 0;
        int left = -1;
        int right = 0;
        while (j < n) {
            while (right != g && alibi[j].t > gardens[right].t)
            {
                ++right; ++left;
            }
            //System.err.println(left + " " + right + " " + j);

            // check right
            if (right != g && !withinDist(right, j)) {
                ++ans;
            }
            // check left
            else if (left != -1 && !withinDist(left, j)) {
                ++ans;
            }
            ++j;
        }
        pw.println(ans);
        pw.close();
    }

    static boolean withinDist(int gardenInd, int alibiInd) { // a ^ 2 + b ^ 2 <= c ^ 2
        return (alibi[alibiInd].x - gardens[gardenInd].x) * (alibi[alibiInd].x - gardens[gardenInd].x)
                + (alibi[alibiInd].y - gardens[gardenInd].y) * (alibi[alibiInd].y - gardens[gardenInd].y)
                <= (alibi[alibiInd].t - gardens[gardenInd].t) * (alibi[alibiInd].t - gardens[gardenInd].t);
    }

    static void printArr(Ele[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.err.println(arr[i]);
        }
    }


}
class Ele implements Comparable<Ele> {
    long x;
    long y;
    long t;
    Ele(long x, long y, long t) {
        this.x = x; this.y = y; this.t = t;
    }

    @Override
    public int compareTo(Ele o) {
        return Long.compare(t, o.t);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + t;
    }
}
