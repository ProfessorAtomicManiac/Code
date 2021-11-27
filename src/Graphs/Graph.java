package Graphs;

import java.util.List;

public interface Graph<V, E> {

    int numVertices();

    List<V> vertices();

    int numEdges();

    List<Edge<V, E>> edges();

    Edge<V, E> getEdge(V begin, V end);

    int outDegree(V vertex);

    int inDegree(V vertex);

    List<Edge<V, E>> outgoingEdges(V vertex);

    List<Edge<V, E>> incomingEdges(V vertex);

    // Duplicate vertexes can be made
    void insertVertex(V vertex);

    // Duplicate edges can be made
    void insertEdge(V begin, V end, E edgeData);

    void removeVertex(V vertex);

    void removeEdge(V begin, V end);
}
