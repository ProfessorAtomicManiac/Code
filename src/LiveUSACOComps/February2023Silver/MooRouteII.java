package LiveUSACOComps.February2023Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1304">...</a>
 * Thought of solution and coded it in an hour-ish
 * Got test cases 1-10 and 13
 */

public class MooRouteII {

    static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
    static long[] layover;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        layover = new long[n];
        ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Integer.MAX_VALUE;
            adj.add(new ArrayList<>());
        }
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken()) - 1;
            long depart = Long.parseLong(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken()) - 1;
            long arrive = Long.parseLong(st.nextToken());
            adj.get(a1).add(new Edge(a2, depart, arrive));
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            layover[i] = Long.parseLong(st.nextToken());
        }
        for (ArrayList<Edge> edges : adj) {
            Collections.sort(edges);
        }
        dfs(0, 0, 0);
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == Integer.MAX_VALUE)
                pw.println(-1);
            else pw.println(ans[i]);
        }
        pw.close();
    }

    static void dfs(int airport, long time, long lay) {
        //System.err.println(airport + " " + time);
        ans[airport] = Math.min(time, ans[airport]);
        // binary search to find available edges that is >= time + layover
        int l = 0, r = adj.get(airport).size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (adj.get(airport).get(mid).depart >= time + lay) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        for (int i = l; i < adj.get(airport).size(); i++) {
            Edge e = adj.get(airport).get(i);
            if (!e.visited && e.depart >= time + lay) {
                e.visited = true;
                dfs(e.to, e.arrive, layover[e.to]);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        long depart;
        long arrive;
        boolean visited = false;
        Edge(int t, long d, long a) {
            to = t; depart = d; arrive = a;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(depart, o.depart);
        }
    }
}