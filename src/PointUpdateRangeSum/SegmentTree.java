package PointUpdateRangeSum;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SegmentTree <T> {

    private List<T> array;
    private ArrayList<T> tree;
    private Comparator<T> comparator;

    public SegmentTree(Comparator<T> comparator)
    {
        new SegmentTree<>(new ArrayList<>(), comparator);
    }

    public SegmentTree(List<T> array, Comparator<T> comparator)
    {
        this.comparator = comparator;
        this.array = array;
        tree = new ArrayList<>();
        int ct = 0;
        for (int i = 0; i < 2 * (int) Math.pow(2, Math.ceil(Math.log(array.size())/Math.log(2))) - 1; i++)
        {
            ++ct;
            tree.add(null);
        }
        //System.err.println(ct);
        construct();
    }

    public T recalculate(int root)
    {
        if (2 * root + 2 >= tree.size())
            return tree.get(2 * root + 1);
        return comparator.compare(tree.get(2 * root + 1), tree.get(2 * root + 2)) > 0 ? tree.get(2 * root + 1) : tree.get(2 * root + 2);
    }

    public void construct()
    {
        construct(0, 0, array.size() - 1);
    }

    public void construct(int root, int l, int r)
    {
        //System.err.println(root + " " + l + " " + r);
        if (l == r)
        {
            tree.set(root, array.get(l));
            return;
        }
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        int mid = l + (r - l)/2;

        if (left < tree.size())
            construct(left, l, mid);
        if (right < tree.size())
            construct(right, mid + 1, r);
        tree.set(root, recalculate(root));
    }

    /**
     * Replace array[ind] with element
     */
    public void update(int ind, T element)
    {
        update(0, 0, array.size() - 1, ind, element);
    }

    public void update(int root, int l, int r, int ind, T element)
    {
        if (l == r) {
            tree.set(root, element);
            return;
        }
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        //System.err.println(root + " " + l + "," + r);

        int mid = l + (r - l)/2;

        if (ind <= mid)
        {
            update(left, l, mid, ind, element);
        } else {
            update(right, mid + 1, r, ind, element);
        }
        tree.set(root, recalculate(root));
    }

    /**
     * @return the best value according to the comparator in the range [l, r]
     */
    public T query(int left, int right)
    {
        return query(0, 0, array.size() - 1, left, right);
    }

    public T query(int root, int l, int r, int left, int right) {
        if (l >= left && r <= right)
        {
            return tree.get(root);
        }
        int newLeft = 2 * root + 1;
        int newRight = 2 * root + 2;

        int mid = l + (r - l)/2;

        T ret1 = null;
        T ret2 = null;
        if (left <= mid)
        {
            //System.err.println(l + " " + mid + " , " + left + "," + right);
            ret1 = query(newLeft, l, mid, left, right);
        }
        if (right >= mid + 1) {
            ret2 = query(newRight, mid + 1, r, left, right);
        }

        return (comparator.compare(ret1, ret2) > 0 ? ret1 : ret2);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("tree.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        int n, q;
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        ArrayList<Long> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            arr.add(Long.parseLong(st.nextToken()));
        }

        SegmentTree<Long> tree = new SegmentTree<>(arr, new Com());
        ArrayList<Long> ans = new ArrayList<>();
        //tree.print();

        for (int i = 0 ; i < q; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a, b;
            long c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Long.parseLong(st.nextToken());
            if (a == 1)
            {
                tree.update(b, c);
            } else {
                --c;
                ans.add(tree.query(b, (int)c));
            }
        }
        for (long i : ans)
            pw.println(i);
        //tree.print();
        pw.close();
    }

    private static class Com implements Comparator<Long> {

        @Override
        public int compare(Long o1, Long o2) {
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            return -Long.compare(o1, o2);
        }
    }

    public void print()
    {
        for (T ele : tree)
            System.err.print(ele + " ");
        System.err.println();
    }
}
