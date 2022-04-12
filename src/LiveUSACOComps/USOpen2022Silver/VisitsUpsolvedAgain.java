package LiveUSACOComps.USOpen2022Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1230
 * This solution is just dfs, recording visited components, and cycle detection
 */

public class VisitsUpsolvedAgain {
    static ArrayList<Barn> adj = new ArrayList<>();
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            adj.add(new Barn(Integer.parseInt(st.nextToken()) - 1, Long.parseLong(st.nextToken())));
        }

        // dfs from every node, for each dfs find a cycle and subtract the minimum
        // set the visited to i
        // if the .to is == i, that means there is a cycle and you need to subtract the min
        // else if .to is != i && != -1, that means that node was visited previously and no need to continue
        // else if .to == -1 continue dfs

        visited = new int[n];
        Arrays.fill(visited, -1);
        long sum = 0;
        for (int i = 0; i < n; i++)
        {
            if (visited[i] == -1)
                sum += dfs(i, i);
        }
        pw.println(sum);
        pw.close();
    }

    static long dfs(int node, int comp)
    {
        //System.err.println(node);
        visited[node] = comp;
        long sum = adj.get(node).val;
        int to = adj.get(node).to;
        if (visited[to] == comp)
        {
            int y = node;
            long min = adj.get(node).val;
            // this is a cycle and must subtact the min
            do {
                y = adj.get(y).to;
                min = Math.min(min, adj.get(y).val);
            } while (node != y);
            return sum - min;
        } else if (visited[to] == -1)
        {
            return sum + dfs(to, comp);
        } else {
            return sum;
        }
    }

    static class Barn {
        int to;
        long val;
        Barn(int t, long v)
        {
            to = t; val = v;
        }
    }
}

