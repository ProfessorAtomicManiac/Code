package Graphs;

/*
 * V = Vertex Type
 * E = Edge Type
 */

import java.util.ArrayList;

public class EdgeList <V, E> {

    ArrayList<V> vertexSeq = new ArrayList<>();
    ArrayList<Edge<V, E>> edgeSeq = new ArrayList<>();

    public int numVertices()
    {
        return vertexSeq.size();
    }

    public ArrayList<V> vertices()
    {
        return vertexSeq;
    }

    public int numEdges()
    {
        return edgeSeq.size();
    }

    public ArrayList<Edge<V, E>> edges()
    {
        return edgeSeq;
    }

    public Edge<V, E> getEdge(V begin, V end)
    {
        for (Edge<V, E> edge : edgeSeq)
        {
            if (edge.start == begin && edge.end == end)
                return edge;
        }
        return null;
    }

    public int outDegree(V vertex)
    {
        int count = 0;
        for (Edge<V, E> edge : edgeSeq)
        {
            if (edge.start == vertex)
                ++count;
        }
        return count;
    }

    public int inDegree(V vertex)
    {
        int count = 0;
        for (Edge<V, E> edge : edgeSeq)
        {
            if (edge.end == vertex)
                ++count;
        }
        return count;
    }

    public ArrayList<Edge<V, E>> outgoingEdges(V vertex)
    {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E> edge : edgeSeq)
        {
            if (edge.start == vertex)
                edges.add(edge);
        }
        return edges;
    }

    public ArrayList<Edge<V, E>> incomingEdges(V vertex)
    {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E> edge : edgeSeq)
        {
            if (edge.end == vertex)
                edges.add(edge);
        }
        return edges;
    }

    // Duplicate vertexes can be made
    public void insertVertex(V vertex)
    {
        vertexSeq.add(vertex);
    }

    // Duplicate edges can be made
    public void insertEdge(V begin, V end, E edgeData)
    {
        edgeSeq.add(new Edge<>(edgeData, begin, end));
    }

    public void removeVertex(V vertex)
    {
        vertexSeq.remove(vertex);
        edgeSeq.removeIf(edge -> edge.start == vertex || edge.end == vertex);
    }

    public void removeEdge(V begin, V end)
    {
        edgeSeq.removeIf(edge -> edge.start == begin && edge.end == end);
    }

    static class Edge <V, E>
    {
        E ele;
        V start;
        V end;
        public Edge(E ele, V start, V end)
        {
            this.ele = ele; this.start = start; this.end = end;
        }
    }
}
