package LiveUSACOComps.December2022Silver;

import java.io.*;
import java.util.*;

public class BarnTree {

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static long[] barns;
    static ArrayList<Transport> posTrans = new ArrayList<>();
    static ArrayList<Transport> negTrans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // create a tree
        int n = Integer.parseInt(br.readLine());
        barns = new long[n];
        long eachBarnNeeds = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            adj.add(new ArrayList<>());
            barns[i] = Long.parseLong(st.nextToken());
            eachBarnNeeds += barns[i];
        }
        eachBarnNeeds /= n;
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        for (int i = 0; i < n; i++)
        {
            barns[i] -= eachBarnNeeds;
        }
        dfs(0, -1);
        pw.println(posTrans.size() + negTrans.size());
        for (Transport t : posTrans)
        {
            pw.println((t.from + 1) + " " + (t.to + 1) + " " + t.amt);
        }
        pw.close();
    }

    static long dfs(int node, int parent)
    {
        for (int child : adj.get(node))
        {
            if (child != parent)
            {
                long transport = dfs(child, node);
                if (transport > 0) {
                    posTrans.add(new Transport(child, node, transport));
                    barns[node] += transport;
                } else if (transport < 0) {
                    negTrans.add(new Transport(node, child, -transport));
                    barns[node] -= transport;
                }
            }
        }
        //System.err.println(node + " " + barns[node]);
        return barns[node];
    }

    static class Transport
    {
        int from;
        int to;
        long amt;
        Transport(int f, int t, long a)
        {
            from = f;
            to = t;
            amt = a;
        }

    }
}