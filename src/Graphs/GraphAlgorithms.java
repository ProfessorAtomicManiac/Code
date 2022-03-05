package Graphs;

import java.util.*;

public class GraphAlgorithms <V, E> {

    private final int INF = 1000000000;

    /**
     * The following dfs and bfs algorithms will print both discovery edges and back edges
     * If you just want the discovery edges, then a lot of the code can be omitted
     */
    public GraphAlgorithms()
    {

    }

    /**
     * Prints the discovery edges of dfs recursively. I tried to use flags for the vertexes of my graph
     * however, the classes I've implemented makes this quite difficult, so instead I'll try to use a Map
     * According to sources online, I can access the keys (vertexes) in O(1) time, and if it is set to true
     * that means it has already been visited
     * @param graph     Self-explanatory
     */
    public void dfs(Graph<V, E> graph)
    {
        if (graph.numVertices() == 0)
            return;
        HashMap<V, Boolean> visited = new HashMap<>();
        for (V vertex : graph.vertices())
            visited.put(vertex, false);
        for (V vertex : graph.vertices())
        {
            if (!visited.get(vertex)) {
                visited.put(vertex, true);
                System.out.println("New Connected Component: " + vertex);
                dfs(graph, vertex, visited, null);
            }
        }
    }

    /**
     * Full recursive method of dfs, user should not call this method but should use the overloaded method
     * @param graph         Self-explanatory
     * @param vertex        The current vertex that is being "dfs"ed
     * @param visited       The vertexes that have already been visited
     * @param lastVertex    The vertex that the dfs pathing came from
     */
    public void dfs(Graph<V, E> graph, V vertex, HashMap<V, Boolean> visited, V lastVertex)
    {
        for (Edge<V, E> edge : graph.outgoingEdges(vertex))
        {
            // Is the node that dfs came from
            if (edge.getEnd() == lastVertex)
                continue;
            // Has already been visited
            if (visited.get(edge.getEnd()))
            {
                System.out.println("Back-Edge - " + edge.getEle() + ": " + edge.getStart() + " -> " + edge.getEnd());
                continue;
            }
            System.out.println("Discovery Edge - " + edge.getEle() + ": " + edge.getStart() + " -> " + edge.getEnd());
            visited.put(edge.getEnd(), true);
            dfs(graph, edge.getEnd(), visited, vertex);
        }
    }

    public void iterativeDFS(Graph<V, E> graph)
    {
        HashMap<V, Boolean> visited = new HashMap<>();
        for (V vertex : graph.vertices())
            visited.put(vertex, false);

        Stack<V> stack = new Stack<>();
        Stack<V> preNode = new Stack<>();
        for (V vertex : graph.vertices())
        {
            if (!visited.get(vertex)) {
                System.out.println("New Connected Component: " + vertex);
                stack.push(vertex);
                while (!stack.isEmpty())
                {
                    V curr = stack.pop();
                    if (visited.get(curr))
                        continue;
                    visited.put(curr, true);
                    V backNode = null;
                    if (!preNode.isEmpty()) {
                        System.out.println("Discovery Edge - " + graph.getEdge(preNode.peek(), curr).getEle() + ": " + preNode.peek() + " -> " + curr);
                        backNode = preNode.pop();
                    }
                    for (Edge<V, E> edge : graph.outgoingEdges(curr))
                    {
                        if (backNode != null)
                            if (edge.getEnd() == backNode)
                                continue;

                        if (visited.get(edge.getEnd())) {
                            System.out.println("Back-Edge - " + edge.getEle() + ": " + edge.getStart() + " -> " + edge.getEnd());
                            continue;
                        }
                        preNode.push(curr);
                        stack.push(edge.getEnd());
                    }
                }
            }
        }
    }

    public void bfs(Graph<V, E> graph)
    {
        HashMap<V, Boolean> visited = new HashMap<>();
        for (V vertex : graph.vertices())
            visited.put(vertex, false);
        HashMap<Edge<V, E>, Boolean> visitedEdges = new HashMap<>();
        for (Edge<V, E> edge : graph.edges())
            visitedEdges.put(edge, false);

        Queue<V> queue = new LinkedList<>();
        Queue<V> preNode = new LinkedList<>();
        for (V vertex : graph.vertices())
        {
            if (!visited.get(vertex)) {
                System.out.println("New Connected Component: " + vertex);
                queue.add(vertex);
                while (!queue.isEmpty())
                {
                    V curr = queue.poll();
                    if (visited.get(curr)) {
                        if (!visitedEdges.get(graph.getEdge(preNode.peek(), curr))) {
                            System.out.println("Back-Edge - " + graph.getEdge(preNode.peek(), curr).getEle() + ": " + preNode.peek() + " -> " + curr);
                            visitedEdges.put(graph.getEdge(preNode.peek(), curr), true);
                            preNode.poll();
                        }
                        continue;
                    }
                    visited.put(curr, true);
                    V backNode = null;
                    if (!preNode.isEmpty()) {
                        System.out.println("Discovery Edge - " + graph.getEdge(preNode.peek(), curr).getEle() + ": " + preNode.peek() + " -> " + curr);
                        visitedEdges.put(graph.getEdge(preNode.peek(), curr), true);
                        backNode = preNode.poll();
                    }
                    for (Edge<V, E> edge : graph.outgoingEdges(curr))
                    {
                        if (backNode != null)
                            if (edge.getEnd() == backNode) {
                                visitedEdges.put(edge, true);
                                continue;
                            }
                        if (visited.get(edge.getEnd())) {
                            System.out.println("Back-Edge - " + edge.getEle() + ": " + edge.getStart() + " -> " + edge.getEnd());
                            visitedEdges.put(edge, true);
                            continue;
                        }
                        preNode.add(curr);
                        queue.add(edge.getEnd());
                    }
                }
            }
        }
    }

    public Collection<Integer> dijkstra(Graph<V, E> graph, V vertex)
    {
        HashMap<V, Integer> dist = new HashMap<>();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
        for (V v : graph.vertices())
        {
            if (v == vertex) {
                pq.add(new Vertex<>(vertex, 0));
                dist.put(vertex, 0);
            } else {
                pq.add(new Vertex<>(v, INF));
                dist.put(v, INF);
            }
        }
        while (!pq.isEmpty())
        {
            Vertex<V> u = pq.poll();
            for (Edge<V, E> edge : graph.outgoingEdges(u.vertex))
            {
                if (u.dist + (int) edge.getEle() < dist.get(edge.getEnd()))
                {
                    // the equals method has been overridden
                    pq.remove(new Vertex<>(edge.getEnd(), dist.get(edge.getEnd())));
                    pq.add(new Vertex<>(edge.getEnd(), u.dist + (int) edge.getEle()));
                    dist.put(edge.getEnd(), u.dist + (int) edge.getEle());
                }
            }
        }
        return dist.values();
    }

    static class Vertex <V> implements Comparable<Vertex<V>> {
        V vertex;
        int dist;
        public Vertex (V v, int d) {
            vertex = v; dist = d;
        }

        @Override
        public int compareTo(Vertex<V> o) {
            return Integer.compare(dist, o.dist);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?> vertex1 = (Vertex<?>) o;
            return dist == vertex1.dist && Objects.equals(vertex, vertex1.vertex);
        }
    }
}
