package LiveUSACOComps.USOpen2022Silver;

import java.io.*;
import java.util.*;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1230">...</a>
 * This solution is just Prim-Jarnik's algorithm
 */

public class VisitsUpsolved {
    static ArrayList<LinkedList<Edge>> adj = new ArrayList<>();
    static long[] diff;
    static long sum = 0;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("visits.in"));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++)
        {
            adj.add(new LinkedList<>());
        }
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ind = Integer.parseInt(st.nextToken())-1;
            long val = Long.parseLong(st.nextToken());
            adj.get(ind).add(new Edge(i, val));
            adj.get(i).add(new Edge(ind, val));
        }
        // Find all connected components
        // dfs to find components
        diff = new long[n];
        visited = new boolean[n];
        for (int i = 0; i < diff.length; i++) {
            if (!visited[i])
                prim(i);
        }

        // Print out the maximum spanning tree sum
        pw.println(sum);
        pw.close();
    }

    static void prim(int index)
    {
        pq.add(new Edge(index, 0));
        while (!pq.isEmpty())
        {
            Edge e = pq.poll();
            visited[e.to] = true;
            sum += diff[e.to];
            //System.err.println(e.to + "," + e.val);
            for (Edge edge : adj.get(e.to)) {
                if (diff[edge.to] < edge.val && !visited[edge.to]) {
                    diff[edge.to] = edge.val;
                    pq.remove(new Edge(edge.to, 0));
                    pq.add(new Edge(edge.to, diff[edge.to]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        long val;
        Edge(int t, long v)
        {
            to = t; val = v;
        }

        @Override
        public int compareTo(Edge o) {
            return -Long.compare(val, o.val);
        }

        @Override
        public boolean equals(Object obj) {
            return (((Edge) obj).to) == (this.to);
        }
    }
}