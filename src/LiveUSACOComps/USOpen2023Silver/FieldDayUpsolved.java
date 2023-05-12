package LiveUSACOComps.USOpen2023Silver;

import java.io.*;
import java.util.*;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1327">...</a>
 * Full solution involving BFS. It is really cool to think of all possible
 * binary strings as a graph with edges connected to each other.
 *
 * Also I became Benq or smth cuz I was able to root out two very not obvious bugs
 * without looking at solution code but based on luck and intuition
 *
 * 1. BFS did not actually visit each node once, if the node is in the queue it may
 * still be added to the queue since the node is considered visited once the node is
 * popped from the queue. That is why it TLE even though my calculations determined
 * that it shouldn't TLE
 * 2. Interestingly after the 1st fix, the program failed test cases 2-9 but ak'ed all
 * of 10-20 except for 19 cuz TLE. I guessed that since test cases 2-9 are smaller, there
 * is a higher likelihood of some teams to be duplicates, and my code didn't account for that.
 * 3. I fixed the TLE issue by using boolean arrays instead of HashSet cuz HashSet has high
 * constant factor
 */
public class FieldDayUpsolved {

    static boolean[] visited;
    static HashMap<Integer, ArrayList<Integer>> reciprocal = new HashMap<>();
    static ArrayList<Integer> teams = new ArrayList<>();
    static int[] ans;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            // convert each string to a binary integer
            char[] str = br.readLine().toCharArray();

            int num = 0;
            for (int j = 0; j < c; j++) {
                int add = 1;
                if (str[j] == 'G') // G == 1
                    add <<= c - 1 - j;
                else add = 0;
                num += add;
            }
            //System.err.println(Integer.toBinaryString(num));
            teams.add(num);
        }
        // create graph of all possible binary strings
        // connected by edges of one move
        //System.err.println();

        // mark reciprocal existing strs
        for (int i = 0; i < n; i++) {
            int recip = (int)Math.pow(2, c) - 1 - teams.get(i);
            //System.err.println(Integer.toBinaryString(teams.get(i)) + " " + Integer.toBinaryString(recip));
            if (reciprocal.containsKey(recip))
                reciprocal.get(recip).add(i);
            else {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(i);
                reciprocal.put(recip, arr);
            }
        }
        ans = new int[n];
        visited = new boolean[(1 << c)];
        Arrays.fill(ans, -1);
        // bfs from each existing strs
        Queue<Pair> q = new LinkedList<>();
        for (int team : teams) {
            q.add(new Pair(team, 0));
        }
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.team] = true;
            //System.err.println(Integer.toBinaryString(curr.team) + " " + curr.moves);

            // when encounter reciprocal then u record value
            if (reciprocal.containsKey(curr.team)) {
                for (int ansInd : reciprocal.get(curr.team)) {
                    if (ans[ansInd] == -1)
                        ans[ansInd] = curr.moves;
                }

            }
            for (int i = 0; i < c; i++) {
                int neighbor = curr.team;
                neighbor ^= (int)Math.pow(2, i);
                if (!visited[neighbor]) {
                    q.add(new Pair(neighbor, curr.moves + 1));
                    visited[neighbor] = true;
                }
            }
        }
        for (int an : ans) {
            pw.println(c - an);
        }
        pw.close();
    }

    static class Pair {
        int team;
        int moves;
        Pair(int t, int m) {
            team = t; moves = m;
        }
    }
}