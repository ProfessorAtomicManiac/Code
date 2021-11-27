package Graphs;

public class Edge <V, E> {
    private final E ele;
    private final V start;
    private final V end;
    public Edge(E ele, V start, V end)
    {
        this.ele = ele; this.start = start; this.end = end;
    }

    public E getEle()
    {
        return ele;
    }
    public V getStart()
    {
        return start;
    }
    public V getEnd()
    {
        return end;
    }
}
