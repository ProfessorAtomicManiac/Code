package Graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphTest {

    static EdgeList<Integer, Character> edgeGraph = new EdgeList<>();
    static AdjacencyList<Integer, Character> adjacentGraph = new AdjacencyList<>();
    static AdjacencyMatrix<Integer, Character> matrixGraph = new AdjacencyMatrix<>();

    @BeforeEach
    public void initialize()
    {
        edgeGraph = new EdgeList<>();
        adjacentGraph = new AdjacencyList<>();
        matrixGraph = new AdjacencyMatrix<>();
        edgeGraph.insertVertex(1);
        edgeGraph.insertVertex(2);
        edgeGraph.insertVertex(3);
        edgeGraph.insertVertex(4);
        edgeGraph.insertEdge(1, 2, 'A');
        edgeGraph.insertEdge(2, 3, 'B');
        edgeGraph.insertEdge(3, 1, 'C');
        edgeGraph.insertEdge(3, 4, 'D');
        edgeGraph.insertEdge(1, 3, 'E');

        adjacentGraph.insertVertex(1);
        adjacentGraph.insertVertex(2);
        adjacentGraph.insertVertex(3);
        adjacentGraph.insertVertex(4);
        adjacentGraph.insertEdge(1, 2, 'A');
        adjacentGraph.insertEdge(2, 3, 'B');
        adjacentGraph.insertEdge(3, 1, 'C');
        adjacentGraph.insertEdge(3, 4, 'D');
        adjacentGraph.insertEdge(1, 3, 'E');

        matrixGraph.insertVertex(1);
        matrixGraph.insertVertex(2);
        matrixGraph.insertVertex(3);
        matrixGraph.insertVertex(4);
        matrixGraph.insertEdge(1, 2, 'A');
        matrixGraph.insertEdge(2, 3, 'B');
        matrixGraph.insertEdge(3, 1, 'C');
        matrixGraph.insertEdge(3, 4, 'D');
        matrixGraph.insertEdge(1, 3, 'E');
    }

    @Test
    public void test1()
    {
        test1EdgeMethod(edgeGraph);
        test1OtherMethod(adjacentGraph);
        test1OtherMethod(matrixGraph);
    }

    // This is specifically for edge graph
    public void test1EdgeMethod(Graph<Integer, Character> graph)
    {
        List<Integer> vertices = graph.vertices();
        Assertions.assertEquals(vertices.get(0), 1);
        Assertions.assertEquals(vertices.get(1), 2);
        Assertions.assertEquals(vertices.get(2), 3);
        Assertions.assertEquals(vertices.get(3), 4);
        Assertions.assertEquals(graph.numEdges(), 5);

        /* This test is for Adjacency Matrix/List
        List<EdgeList.Edge<Integer, Character>> edges = graph.edges();
        Assertions.assertEquals(edges.get(0).ele, 'A');
        Assertions.assertEquals(edges.get(1).ele, 'E');
        Assertions.assertEquals(edges.get(2).ele, 'B');
        Assertions.assertEquals(edges.get(3).ele, 'C');
         */

        List<Edge<Integer, Character>> edges = graph.edges();
        Assertions.assertEquals(edges.get(0).getEle(), 'A');
        Assertions.assertEquals(edges.get(1).getEle(), 'B');
        Assertions.assertEquals(edges.get(2).getEle(), 'C');
        Assertions.assertEquals(edges.get(3).getEle(), 'D');
    }

    // This is for the matrix/adjacent implementations
    public void test1OtherMethod(Graph<Integer, Character> graph) {
        List<Integer> vertices = graph.vertices();
        Assertions.assertEquals(vertices.get(0), 1);
        Assertions.assertEquals(vertices.get(1), 2);
        Assertions.assertEquals(vertices.get(2), 3);
        Assertions.assertEquals(vertices.get(3), 4);
        Assertions.assertEquals(graph.numEdges(), 5);

        List<Edge<Integer, Character>> edges = graph.edges();
        Assertions.assertEquals(edges.get(0).getEle(), 'A');
        Assertions.assertEquals(edges.get(1).getEle(), 'E');
        Assertions.assertEquals(edges.get(2).getEle(), 'B');
        Assertions.assertEquals(edges.get(3).getEle(), 'C');
    }

    @Test
    public void test2()
    {
        test2Method(edgeGraph);
        test2Method(adjacentGraph);
        test2Method(matrixGraph);
    }

    public void test2Method(Graph<Integer, Character> graph)
    {
        Edge<Integer, Character> edge = graph.getEdge(3, 1);
        Assertions.assertEquals(edge.getEle(), 'C');
        edge = graph.getEdge(1, 4);
        Assertions.assertNull(edge);
    }

    @Test
    public void test3()
    {
        test3EdgeMethod(edgeGraph);
        test3OtherMethod(adjacentGraph);
        test3OtherMethod(matrixGraph);
    }

    // This is only for edge implementation
    public void test3EdgeMethod(EdgeList<Integer, Character> graph)
    {
        Assertions.assertEquals(graph.outDegree(1), 2);
        Assertions.assertEquals(graph.outDegree(3), 2);
        Assertions.assertEquals(graph.inDegree(1), 1);
        Assertions.assertEquals(graph.inDegree(3), 2);
        List<Edge<Integer, Character>> edges = graph.outgoingEdges(3);
        Assertions.assertEquals(edges.get(0).getEle(), 'C');
        Assertions.assertEquals(edges.get(1).getEle(), 'D');
        edges = graph.incomingEdges(3);
        /* This is for Adjacency Matrix/List
        Assertions.assertEquals(edges.get(0).ele, 'E');
        Assertions.assertEquals(edges.get(1).ele, 'B');
         */
        Assertions.assertEquals(edges.get(0).getEle(), 'B');
        Assertions.assertEquals(edges.get(1).getEle(), 'E');
    }

    // This is for the matrix/adjacent implementations
    public void test3OtherMethod(Graph<Integer, Character> graph) {
        Assertions.assertEquals(graph.outDegree(1), 2);
        Assertions.assertEquals(graph.outDegree(3), 2);
        Assertions.assertEquals(graph.inDegree(1), 1);
        Assertions.assertEquals(graph.inDegree(3), 2);
        List<Edge<Integer, Character>> edges = graph.outgoingEdges(3);
        Assertions.assertEquals(edges.get(0).getEle(), 'C');
        Assertions.assertEquals(edges.get(1).getEle(), 'D');
        edges = graph.incomingEdges(3);
        Assertions.assertEquals(edges.get(0).getEle(), 'E');
        Assertions.assertEquals(edges.get(1).getEle(), 'B');

    }

    @Test
    public void test4()
    {
        test4Method(edgeGraph);
        test4Method(adjacentGraph);
        test4Method(matrixGraph);
    }

    public void test4Method(Graph<Integer, Character> graph)
    {
        graph.removeVertex(4);
        graph.removeVertex(3);
        Assertions.assertEquals(1, graph.numEdges());
        Assertions.assertEquals(graph.edges().get(0).getEle(), 'A');
        Assertions.assertEquals(graph.vertices().get(0), 1);
        graph.removeEdge(1, 2);
        Assertions.assertEquals(graph.edges().size(), 0);
        graph.insertEdge(1, 2, 'A');
    }
}
