package Graphs;

import java.util.HashMap;
import java.util.Stack;

public class GraphAlgorithms <V, E> {

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
}
