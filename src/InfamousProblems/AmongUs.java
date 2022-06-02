package InfamousProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * This problem is sus cuz amongus
 * https://www.codechef.com/INOIPRAC/problems/AMONGUS2
 */

public class AmongUs {
    static ArrayList<LinkedList<Edge>> adj;
    static int[] visited;
    static int numVisited;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int tests = Integer.parseInt(br.readLine());
        for (int t = 0; t < tests; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            // initialize the adj
            for (int i = 0; i < n; i++)
            {
                adj.add(new LinkedList<>());
            }

            for (int i = 0; i < m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int accuse = Integer.parseInt(st.nextToken());
                if (accuse == 1) accuse = -1;
                else accuse = 1;
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                adj.get(a).add(new Edge(b, accuse));
                adj.get(b).add(new Edge(a, accuse));
            }

            // dfs
            int ans = 0;

            visited = new int[n];
            for (int i = 0; i < n; i++)
            {
                if (visited[i] == 0)
                {
                    numVisited = 0;
                    visited[i] = 1;
                    int temp = dfs(i);
                    if (temp == -1) {
                        ans = -1;
                        break;
                    }
                    ans += Math.max(temp, numVisited - temp);
                }
            }
            pw.println(ans);
        }
        pw.close();
    }

    static int dfs(int node)
    {
        boolean isValid = true;
        int ans = 0;
        ++numVisited;
        if (visited[node] == -1)
            ++ans;
        for (Edge e : adj.get(node))
        {
            if (visited[e.to] == 0)
            {
                visited[e.to] = visited[node]*e.type;
                int temp = dfs(e.to);
                if (temp == -1)
                    return -1;
                else ans += temp;
            } else {
                if (visited[e.to] != visited[node]*e.type)
                    isValid = false;
            }
        }
        if (!isValid)
            return -1;
        return ans;
    }

    static class Edge {
        int to;
        int type;
        Edge(int to, int type)
        {
            this.to = to; this.type = type;
        }
    }
}
