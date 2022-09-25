package PointUpdateRangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FenwickTree {
    long[] tree;
    long[] arr;

    /**
     * Additive Fenwick Tree, does work for other operations, but it's only implemented for addition
     * Also works for different types but Java keeps yelling at me cuz you can't override operators in Java
     */
    public FenwickTree(long[] arr)
    {
        tree = new long[arr.length + 1];
        this.arr = arr;
        for (int i = 1; i <= arr.length; i++)
        {
            tree[i] += arr[i-1];
            int j = i + (i & -i);
            if (j <= arr.length)
                tree[j] += tree[i];
        }
    }

    public long sum(int pos)
    {
        long sum = 0;
        while (pos > 0)
        {
            sum += tree[pos];
            pos -= (pos & -pos);
        }
        return sum;
    }

    public long query(int left, int right)
    {
        return sum(right) - sum(left - 1);
    }

    public void update(int ind, long val)
    {
        long change = val - arr[ind-1];
        arr[ind-1] = val;
        while (ind < tree.length)
        {
            tree[ind] += change;
            ind += (ind & -ind);
        }
    }

    public static void main(String[] args) throws IOException {
        int n, q;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            arr[i] = Long.parseLong(st.nextToken());
        }
        FenwickTree tree = new FenwickTree(arr);
        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (query == 1)
            {
                tree.update(a, b);
            }
            else
            {
                pw.println(tree.query(a, (int) b));
            }
        }
        pw.close();
    }
}
