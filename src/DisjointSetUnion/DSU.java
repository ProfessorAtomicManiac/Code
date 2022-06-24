package DisjointSetUnion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DSU <T> {

    // Can't avoid raw types
    Node[] nodes;
    public DSU(int n)
    {
        nodes = new Node[n];
        for (int i = 0; i < n; i++)
        {
            nodes[i] = new Node<>(null, i);
            nodes[i].parent = nodes[i];
        }
    }
    public void union(Node<T> node1, Node<T> node2)
    {
        Node<T> root1 = find(node1);
        // path compression should make the heights of both trees = 2
        root1.parent = find(node2);
    }

    public Node<T> find(Node<T> node)
    {
        if (node.parent != node) {
            node.parent = find(node.parent);
        }
        return node.parent;
    }

    static class Node <T> {
        Node<T> parent;
        T data;
        public Node(Node<T> parent, T data) {
            this.parent = parent; this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        DSU<Integer> dsu = new DSU<>(n);
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
        }
    }
}
