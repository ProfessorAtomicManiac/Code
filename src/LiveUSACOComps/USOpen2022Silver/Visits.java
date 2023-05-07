package LiveUSACOComps.USOpen2022Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1230">...</a>
 * This solution only works if a_i != a_j for all i != j
 * So only got 3 test cases and solved one subtask
 */

public class Visits {

    static Cow[] cows; // 0-indexed
    static ArrayList<Vertex> adj = new ArrayList<>();
    static boolean[] visited;
    static Stack<Long> cowsInLoop = new Stack<>();
    static ArrayList<Long> ans = new ArrayList<>(); // stores the values of components

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        cows = new Cow[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            long val = Long.parseLong(st.nextToken());
            cows[i] = new Cow(index, val);
        }
        // Create a graph where index -> cows[index]
        for (int i = 0; i < cows.length; i++)
        {
            adj.add(new Vertex(-1, cows[i].barn));
        }
        // Find the loops using dfs, then find the sum - min of each loop
        for (int i = 0; i < n; i++)
        {
            // if already discovered in another component, no need to dfs
            visited = new boolean[n];
            dfs(i, i);
        }
        pw.println(ans);
        pw.close();
    }

    static void dfs(int index, int comp)
    {
        // if detect we visited another discovered component

        // if loop is detected calculate sum - min
        if (visited[index] && !cowsInLoop.isEmpty())
        {
            long sum = 0;
            long min = Long.MAX_VALUE;
            while (!cowsInLoop.isEmpty())
            {
                long ret = cowsInLoop.pop();
                sum += ret;
                if (min > ret)
                    min = ret;
                //System.err.println(sum + "," + min);
            }
            //ans += (sum - min);
            //System.err.println(ans);
            return;
        }
        // otherwise keep doing dfs and add to stack
        visited[index] = true;
        cowsInLoop.add(cows[index].val);
        //dfs(cows[index].barn);
    }

    static class Cow {
        int barn;
        long val;
        Cow(int b, long v)
        {
            barn = b; val = v;
        }
    }

    static class Vertex {
        int comp;
        int nei;
        Vertex(int c, int n)
        {
            comp = c; nei = n;
        }
    }
}