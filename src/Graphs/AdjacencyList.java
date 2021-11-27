package Graphs;

import java.util.LinkedList;

/*
 * Directed Graph
 * V = vertex type
 * E = edge type
 */

public class AdjacencyList <V, E> {

    // I think in this case, it would be better to use LinkedList since insertion of elements is quicker than that of
    // ArrayList, and also it doesn't matter that accessing elements for Arraylist is fast since we need to iterate through the list to
    // find a certain value, at which case both data structures perform the same
    LinkedList<VertexElement<V, E>> list = new LinkedList<>();

    public int numVertices()
    {
        return list.size();
    }

    public LinkedList<V> vertices()
    {
       LinkedList<V> vertices = new LinkedList<>();
       for (VertexElement<V, E> vertexEle : list)
           vertices.add(vertexEle.vertex);
       return vertices;
    }

    public int numEdges()
    {
        int numEdges = 0;
        for (VertexElement<V, E> vertexEle : list)
            numEdges += vertexEle.edges.size();
        return numEdges;
    }

    public LinkedList<Edge<V, E>> edges()
    {
        LinkedList<Edge<V, E>> edges = new LinkedList<>();
        for (VertexElement<V, E> vertexEle : list)
        {
            edges.addAll(vertexEle.edges);
        }
        return edges;
    }

    public Edge<V, E> getEdge(V begin, V end)
    {
        for (VertexElement<V, E> vertexEle : list)
        {
            if (vertexEle.vertex == begin)
            {
                for (Edge<V, E> edge : vertexEle.edges)
                {
                    if (edge.end == end)
                        return edge;
                }
            }
        }
        return null;
    }

    public Edge<V, E> getEdge(E ele)
    {
        for (VertexElement<V, E> vertexEle : list)
        {
            for (Edge<V, E> edge : vertexEle.edges)
            {
                if (edge.ele == ele)
                    return edge;
            }
        }
        return null;
    }

    public int outDegree(V vertex)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex)
                return ve.edges.size();
        }
        return -1;
    }

    public int inDegree(V vertex)
    {
        boolean vertexExists = false;
        int num = 0;
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex)
                vertexExists = true;
            for (Edge<V, E> edge : ve.edges)
            {
                if (edge.end == vertex)
                    ++num;
            }
        }
        if (!vertexExists)
            return -1;
        return num;
    }

    public LinkedList<Edge<V, E>> outgoingEdges(V vertex)
    {
        LinkedList<Edge<V, E>> edges = new LinkedList<>();
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex)
                edges.addAll(ve.edges);
        }
        return edges;
    }

    public LinkedList<Edge<V, E>> incomingEdges(V vertex)
    {
        LinkedList<Edge<V, E>> edges = new LinkedList<>();
        for (VertexElement<V, E> ve : list)
        {
            for (Edge<V, E> edge : ve.edges)
            {
                if (edge.end == vertex)
                    edges.add(edge);
            }
        }
        return edges;
    }

    // No duplicate vertexes
    public boolean insertVertex(V vertex)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex)
                return false;
        }
        list.add(new VertexElement<>(vertex));
        return true;
    }

    // No duplicate edges
    public boolean insertEdge(V begin, V end, E edgeData)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == begin) {
                for (Edge<V, E> edge : ve.edges)
                {
                    if (edge.end == end)
                        return false;
                }
                ve.addEdge(new Edge<>(edgeData, begin, end));
                return true;
            }
        }
        return false;
    }

    public void removeVertex(V vertex)
    {
        list.removeIf(ve -> ve.vertex == vertex);
        for (VertexElement<V, E> ve : list)
        {
            ve.edges.removeIf(edge -> edge.end == vertex);
        }
    }

    public void removeEdge(V begin, V end)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == begin)
            {
                ve.edges.removeIf(e -> e.end == end);
                break;
            }
        }
    }

    public void removeEdge(E edge)
    {
        for (VertexElement<V, E> ve : list)
        {
            ve.edges.removeIf(e -> e.ele == edge);
        }
    }

    static class VertexElement <V, E> {
        V vertex;
        LinkedList<Edge<V, E>> edges = new LinkedList<>();

        public VertexElement(V vertex)
        {
            this.vertex = vertex;
        }

        public VertexElement(V vertex, LinkedList<Edge<V, E>> edges)
        {
            this.vertex = vertex;
            this.edges = edges;
        }

        public void addEdge(Edge<V, E> edge)
        {
            edges.add(edge);
        }
    }

    static class Edge <V, E> {
        E ele;
        V begin;
        V end;

        public Edge(E ele, V begin, V end)
        {
            this.ele = ele;
            this.begin = begin;
            this.end = end;
        }

        public LinkedList<V> endVertices()
        {
            LinkedList<V> endVertices = new LinkedList<>();
            endVertices.add(begin); endVertices.add(end);
            return endVertices;
        }
    }
}
