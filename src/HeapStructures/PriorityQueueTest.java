package HeapStructures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    public static void main(String[] args)
    {
        UnsortedPriorityQueue<Integer, Character> q = new UnsortedPriorityQueue<>();
        q.setComparator(Integer::compareTo);
        q.insert(5, 'A');
        q.insert(4, 'B');
        q.insert(7, 'F');
        q.insert(1, 'D');
        System.out.println(q.removeMin());
        q.insert(3, 'J');
        q.insert(6, 'L');
        System.out.println(q.removeMin());
        System.out.println(q.removeMin());
        q.insert(8, 'G');
        System.out.println(q.removeMin());
        q.insert(2, 'H');
        System.out.println(q.removeMin());
        System.out.println(q.removeMin());
    }

    @Disabled
    @Test
    public void testMin()
    {
        UnsortedPriorityQueue<Integer, Character> q = new UnsortedPriorityQueue<>();
        q.insert(6, 'A');
        q.insert(2, 'B');
        q.insert(3, 'C');
        q.insert(4, 'D');
        q.setComparator(Integer::compareTo);
        assertEquals('B', (char) q.min(), "The character is not right! ");
    }

    @Disabled
    @Test
    public void testRemoveMin()
    {
        UnsortedPriorityQueue<Integer, Character> q = new UnsortedPriorityQueue<>();
        q.insert(6, 'A');
        q.insert(2, 'B');
        q.insert(4, 'C');
        q.insert(3, 'D');
        q.setComparator(Integer::compareTo);
        q.removeMin();
        q.removeMin();
        assertEquals('C', (char) q.min(), "The character is not right! ");
    }

    @Disabled
    @Test
    public void testSortedInsert()
    {
        SortedPriorityQueue<Integer, Character> q = new SortedPriorityQueue<>();
        q.setComparator(Integer::compareTo);
        q.insert(6, 'A');
        q.insert(2, 'B');
        q.insert(4, 'C');
        q.insert(3, 'D');

        assertEquals('B', q.removeMin(), "WRONG_ANSWER");
        assertEquals('D', q.removeMin(), "WRONG_ANSWER");
        assertEquals('C', q.removeMin(), "WRONG_ANSWER");
        assertEquals('A', q.removeMin(), "WRONG_ANSWER");
    }

    @Disabled
    @Test
    public void testSortedInsert2()
    {
        SortedPriorityQueue<Integer, Character> q = new SortedPriorityQueue<>();
        q.setComparator(Integer::compareTo);
        q.insert(5, 'A');
        q.insert(4, 'B');
        q.insert(7, 'F');
        q.insert(1, 'D');
        assertEquals('D', q.removeMin(), "WRONG_ANSWER");
        q.insert(3, 'J');
        q.insert(6, 'L');
        assertEquals('J', q.removeMin(), "WRONG_ANSWER");
        assertEquals('B', q.removeMin(), "WRONG_ANSWER");
        q.insert(8, 'G');
        assertEquals('A', q.removeMin(), "WRONG_ANSWER");
        q.insert(2, 'H');
        assertEquals('H', q.removeMin(), "WRONG_ANSWER");
        assertEquals('L', q.removeMin(), "WRONG_ANSWER");

    }

    @Test
    public void testHeapInsert1()
    {
        HeapPriorityQueue<Integer, Character> q = new HeapPriorityQueue<>();
        q.setComparator(Integer::compareTo);
        q.insert(6, 'A');
        q.insert(2, 'B');
        q.insert(4, 'C');
        q.insert(3, 'D');

        assertEquals('B', q.removeMin(), "WRONG_ANSWER");
        assertEquals('D', q.removeMin(), "WRONG_ANSWER");
        assertEquals('C', q.removeMin(), "WRONG_ANSWER");
        assertEquals('A', q.removeMin(), "WRONG_ANSWER");
    }

    @Test
    public void testHeapInsert2()
    {
        HeapPriorityQueue<Integer, Character> q = new HeapPriorityQueue<>();
        q.setComparator(Integer::compareTo);
        q.insert(5, 'A');
        q.insert(4, 'B');
        q.insert(7, 'F');
        q.insert(1, 'D');
        assertEquals('D', q.removeMin(), "WRONG_ANSWER");
        q.insert(3, 'J');
        q.insert(6, 'L');
        assertEquals('J', q.removeMin(), "WRONG_ANSWER");
        assertEquals('B', q.removeMin(), "WRONG_ANSWER");
        q.insert(8, 'G');
        assertEquals('A', q.removeMin(), "WRONG_ANSWER");
        q.insert(2, 'H');
        assertEquals('H', q.removeMin(), "WRONG_ANSWER");
        assertEquals('L', q.removeMin(), "WRONG_ANSWER");
    }

    @Test
    public void heapSort()
    {
        HeapPriorityQueue<Integer, Integer> q = new HeapPriorityQueue<>(Integer::compareTo);
        q.insert(5, 5);
        q.insert(4, 4);
        q.insert(6, 6);
        q.insert(7, 7);
        assertEquals(4, q.min());
        q.insert(1, 1);
        q.insert(2, 2);
        q.insert(3, 3);
        q.insert(8, 8);
        q.insert(8, 8);
        q.insert(9, 9);
        q.insert(10, 10);

        ArrayList<Integer> sorted = q.heapSort();

        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10)), sorted);
    }
}
