package Graphs;

import java.util.LinkedList;
import java.util.List;

/*
 * Directed Graph
 * V = vertex type
 * E = edge type
 */

public class AdjacencyList <V, E> implements Graph<V, E>{

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

    public List<Edge<V, E>> edges()
    {
        List<Edge<V, E>> edges = new LinkedList<>();
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
                    if (edge.getEnd() == end)
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
                if (edge.getEle() == ele)
                    return edge;
            }
        }
        return null;
    }

    public int outDegree(V vertex)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex) {
                return ve.edges.size();
            }
        }
        return 0;
    }

    public int inDegree(V vertex)
    {
        int num = 0;
        for (VertexElement<V, E> ve : list)
        {
            for (Edge<V, E> edge : ve.edges)
            {
                if (edge.getEnd() == vertex)
                    ++num;
            }
        }
        return num;
    }

    public List<Edge<V, E>> outgoingEdges(V vertex)
    {
        List<Edge<V, E>> edges = new LinkedList<>();
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == vertex)
                edges.addAll(ve.edges);
        }
        return edges;
    }

    public List<Edge<V, E>> incomingEdges(V vertex)
    {
        List<Edge<V, E>> edges = new LinkedList<>();
        for (VertexElement<V, E> ve : list)
        {
            for (Edge<V, E> edge : ve.edges)
            {
                if (edge.getEnd() == vertex)
                    edges.add(edge);
            }
        }
        return edges;
    }

    // Duplicate vertexes can be made
    public void insertVertex(V vertex)
    {
        list.add(new VertexElement<>(vertex));
    }

    // Duplicate edges can be made
    public void insertEdge(V begin, V end, E edgeData)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == begin) {
                ve.addEdge(new Edge<>(edgeData, begin, end));
                return;
            }
        }
    }

    public void removeVertex(V vertex)
    {
        list.removeIf(ve -> ve.vertex == vertex);
        for (VertexElement<V, E> ve : list)
        {
            ve.edges.removeIf(edge -> edge.getEnd() == vertex);
        }
    }

    public void removeEdge(V begin, V end)
    {
        for (VertexElement<V, E> ve : list)
        {
            if (ve.vertex == begin)
            {
                ve.edges.removeIf(e -> e.getEnd() == end);
                break;
            }
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
}
