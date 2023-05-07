package LiveUSACOComps.January2023Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1280">...</a>
 * I can't believe I implemented segment tree, a gold topic data structure, on a bronze problem
 * Wasted 2+ hours cuz I forgot how to do segtree
 * I'm still pretty mad, this was one of the easiest silver lately
 * Full AC
 */

public class MooRoute {

    static Ele[] tree;
    static int[] passes;
    static ArrayList<Character> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        passes = new int[n];
        tree = new Ele[4*n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            passes[i] = Integer.parseInt(st.nextToken());
        }

        build(0, 0, n - 1);
        cycleInterval(0, n - 1, 1, 0);
        for (char c : ans)
            pw.print(c);
        pw.close();
    }

    // 1 = right, -1 = left
    static void cycleInterval(int left, int right, int direct, int cycles)
    {
        Ele minObj = getMin(0, 0, passes.length - 1, left, right);
        int min = minObj.data;
        if (min - cycles <= 0) {

            return;
        }
        for (int i = 0; i < min - 1 - cycles; i++) {
            if (direct == 1) {
                for (int j = left; j <= right; j++) {
                    ans.add('R');
                }
            } else {
                for (int j = left; j <= right; j++) {
                    ans.add('L');
                }
            }
            direct *= -1;
        }
        // direct should be right if there is still a segment to the right
        if (minObj.ind + 1 < passes.length && minObj.ind + 1 <= right) {
            cycleInterval(minObj.ind + 1, right, direct, min - 1);
        }
        ans.add('L');
        if (minObj.ind - 1 >= 0 && left <= minObj.ind - 1)
            cycleInterval(left, minObj.ind - 1, direct, min - 1);

    }

    static void build(int node, int left, int right)
    {
        if (left == right)
            tree[node] = new Ele(passes[left], left);
        else {
            int mid = left + (right - left) / 2;
            build(2*node + 1, left, mid);
            build(2 * node + 2, mid + 1, right);
            if (tree[2 * node + 1].data < tree[2 * node + 2].data) {
                tree[node] = new Ele(tree[2 * node + 1].data, tree[2 * node + 1].ind);
            } else {
                tree[node] = new Ele(tree[2 * node + 2].data, tree[2 * node + 2].ind);
            }
        }
    }

    static Ele getMin(int node, int x, int y, int left, int right)
    {
        if (left <= x && y <= right)
            return tree[node];
        else {
            Ele ans = new Ele(Integer.MAX_VALUE, -1);
            int mid = x + (y - x) / 2;
            if (left <= mid) {
                ans = getMin(2 * node + 1, x, mid, left, right);
            }
            if (right > mid) {
                Ele r = getMin(2 * node + 2, mid + 1, y, left, right);
                if (ans.data >= r.data)
                    ans = new Ele(r.data, r.ind);
            }
            return ans;
        }
    }

    static class Ele {
        int data;
        int ind;
        Ele(int d, int i)
        {
            data = d;
            ind = i;
        }
    }
}