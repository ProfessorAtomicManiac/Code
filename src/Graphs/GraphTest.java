package Graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphTest {

    static AdjacencyMatrix<Integer, Character> graph;

    // These tests were all done identical for all graphs, I just switched around the class types
    @BeforeEach
    public void initialization()
    {
        graph = new AdjacencyMatrix<>();
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertVertex(3);
        graph.insertVertex(4);
        graph.insertEdge(1, 2, 'A');
        graph.insertEdge(2, 3, 'B');
        graph.insertEdge(3, 1, 'C');
        graph.insertEdge(3, 4, 'D');
        graph.insertEdge(1, 3, 'E');
    }

    @Test
    public void testAdjacencyList1()
    {
        ArrayList<Integer> vertices = graph.vertices();
        Assertions.assertEquals(vertices.get(0), 1);
        Assertions.assertEquals(vertices.get(1), 2);
        Assertions.assertEquals(vertices.get(2), 3);
        Assertions.assertEquals(vertices.get(3), 4);
        Assertions.assertEquals(graph.numEdges(), 5);

        ArrayList<AdjacencyMatrix.Edge<Integer, Character>> edges = graph.edges();
        Assertions.assertEquals(edges.get(0).ele, 'A');
        Assertions.assertEquals(edges.get(1).ele, 'E');
        Assertions.assertEquals(edges.get(2).ele, 'B');
        Assertions.assertEquals(edges.get(3).ele, 'C');
    }

    @Test
    public void testAdjacencyList2()
    {
        AdjacencyMatrix.Edge<Integer, Character> edge = graph.getEdge(3, 1);
        Assertions.assertEquals(edge.ele, 'C');
        edge = graph.getEdge(1, 4);
        Assertions.assertNull(edge);
    }

    @Test
    public void testAdjacencyList3()
    {
        Assertions.assertEquals(graph.outDegree(1), 2);
        Assertions.assertEquals(graph.outDegree(3), 2);
        Assertions.assertEquals(graph.inDegree(1), 1);
        Assertions.assertEquals(graph.inDegree(3), 2);
        ArrayList<AdjacencyMatrix.Edge<Integer, Character>> edges = graph.outgoingEdges(3);
        Assertions.assertEquals(edges.get(0).ele, 'C');
        Assertions.assertEquals(edges.get(1).ele, 'D');
        edges = graph.incomingEdges(3);
        Assertions.assertEquals(edges.get(0).ele, 'E');
        Assertions.assertEquals(edges.get(1).ele, 'B');
    }

    @Test
    public void testAdjacencyList4()
    {
        graph.removeVertex(4);
        graph.removeVertex(3);
        Assertions.assertEquals(1, graph.numEdges());
        Assertions.assertEquals(graph.edges().get(0).ele, 'A');
        Assertions.assertEquals(graph.vertices().get(0), 1);
        graph.removeEdge(1, 2);
        Assertions.assertEquals(graph.edges().size(), 0);
        graph.insertEdge(1, 2, 'A');
    }
}