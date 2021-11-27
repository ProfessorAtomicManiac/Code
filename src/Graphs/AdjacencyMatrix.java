package Graphs;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * V = Vertex Type
 * E = Edge Type
 */

public class AdjacencyMatrix <V, E> {

    // For this matrix, the row index is where the edge starts and the column index is where the edge ends
    private ArrayList<ArrayList<Edge<V, E>>> matrix = new ArrayList<>();
    // I will use a map to store information about the vertex. Basically an index on the matrix
    // is a key, and it will point to some value which will be information about the vertex
    private HashMap<V, Integer> vertexToIndex = new HashMap<>();
    private HashMap<Integer, V> indexToVertex = new HashMap<>();

    public int numVertices()
    {
        return matrix.size();
    }

    public ArrayList<V> vertices()
    {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    public int numEdges()
    {
        int numEdges = 0;
        for (ArrayList<Edge<V, E>> row : matrix)
        {
            for (Edge<V, E> edge : row)
            {
                if (edge != null)
                    ++numEdges;
            }
        }
        return numEdges;
    }

    public ArrayList<Edge<V, E>> edges()
    {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (ArrayList<Edge<V, E>> e : matrix)
        {
            for (Edge<V, E> edge : e) {
                if (edge != null)
                    edges.add(edge);
            }
        }
        return edges;
    }

    public Edge<V, E> getEdge(V begin, V end)
    {
        return matrix.get(vertexToIndex.get(begin)).get(vertexToIndex.get(end));
    }

    public int outDegree(V vertex)
    {
        int count = 0;
        for (int i = 0; i < matrix.size(); i++)
        {
            if (indexToVertex.get(i) == vertex)
            {
                for (Edge<V, E> edge : matrix.get(i))
                {
                    if (edge != null)
                        ++count;
                }
            }
        }
        return count;
    }

    public int inDegree(V vertex)
    {
        int num = 0;
        for (ArrayList<Edge<V, E>> edges : matrix) {
            for (Edge<V, E> edge : edges) {
                if (edge == null)
                    continue;
                if (edge.end == vertex)
                    ++num;
            }
        }
        return num;
    }

    public ArrayList<Edge<V, E>> outgoingEdges(V vertex)
    {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++)
        {
            if (indexToVertex.get(i) == vertex) {
                for (Edge<V, E> edge : matrix.get(i))
                {
                    if (edge != null)
                        edges.add(edge);
                }

            }
        }
        return edges;
    }

    public ArrayList<Edge<V, E>> incomingEdges(V vertex)
    {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (ArrayList<Edge<V, E>> edgeArray : matrix) {
            for (Edge<V, E> edge : edgeArray) {
                if (edge == null)
                    continue;
                if (edge.end == vertex)
                    edges.add(edge);
            }
        }
        return edges;
    }

    // Duplicate vertexes can be made
    public void insertVertex(V vertex)
    {
        for (ArrayList<Edge<V, E>> edges : matrix)
        {
            edges.add(null);
        }
        ArrayList<Edge<V, E>> edges = new ArrayList<>();
        for (int i = 0; i < matrix.size() + 1; i++)
            edges.add(null);
        matrix.add(edges);
        vertexToIndex.put(vertex, matrix.size()-1);
        indexToVertex.put(matrix.size()-1, vertex);
    }

    // Duplicate edges can be made
    public void insertEdge(V begin, V end, E edgeData)
    {
        Edge<V, E> edge = new Edge<>(begin, end, edgeData);
        matrix.get(vertexToIndex.get(begin)).set(vertexToIndex.get(end), edge);
    }

    public void removeVertex(V vertex)
    {
        matrix.remove((int) vertexToIndex.get(vertex));
        for (int i = 0; i < matrix.get(0).size(); i++)
        {
            matrix.get(i).remove((int) vertexToIndex.get(vertex));
        }
        indexToVertex.remove(vertexToIndex.get(vertex));
        vertexToIndex.remove(vertex);
    }

    public void removeEdge(V begin, V end)
    {
        matrix.get(vertexToIndex.get(begin)).set(vertexToIndex.get(end), null);
    }

    static class Edge<V, E>
    {
        V begin;
        V end;
        E ele;
        public Edge(V begin, V end, E data)
        {
            this.begin = begin;
            this.end = end;
            this.ele = data;
        }
    }
}
