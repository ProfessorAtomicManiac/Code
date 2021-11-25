package HeapStructures;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> {

    private ArrayList<Entry<K, V>> heap;
    private Comparator<K> comparator = null;

    public HeapPriorityQueue()
    {
        heap = new ArrayList<>();
    }

    public HeapPriorityQueue(ArrayList<Entry<K, V>> arr)
    {
        heap = new ArrayList<>();
        // Heapify the array
    }

    public HeapPriorityQueue(Comparator<K> comparator)
    {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public int size()
    {
        return heap.size();
    }

    public boolean isEmpty()
    {
        return heap.size() == 0;
    }

    public void setComparator(Comparator<K> comparator)
    {
        this.comparator = comparator;
    }

    public void insert(K key, V val)
    {
        // Upheap
        heap.add(new Entry<>(key, val));
        int n = heap.size()-1;
        while (comparator.compare(key, heap.get((n-1)/2).key) < 0 && n != 0)
        {
            swap((n-1)/2, n);
            n = (n-1)/2;
        }
    }

    public V min()
    {
        if (isEmpty())
            return null;
        return heap.get(0).val;
    }

    public V removeMin() {
        if (isEmpty())
            return null;
        swap(0, heap.size()-1);
        V val = heap.remove(heap.size()-1).val;
        // Downheap
        int n = 0;
        while (heap.size() != 0)
        {
            int temp = n;
            // If there exists two children
            if (2*n+2 < heap.size()) {
                // If left child is smaller than right child
                if (comparator.compare(heap.get(2 * n + 1).key, heap.get(2 * n + 2).key) < 0) {
                    if (comparator.compare(heap.get(n).key, heap.get(2 * n + 1).key) > 0) {
                        swap(n, (2 * n + 1));
                        n = 2 * n + 1;
                    }
                // If left child is equal to or greater than right child
                } else {
                    if (2*n+2 < heap.size()) {
                        if (comparator.compare(heap.get(n).key, heap.get(2*n+2).key) > 0) {
                            swap(n, (2 * n + 2));
                            n = 2 * n + 2;
                        }
                    }
                }
            // If there only exists a left child
            } else if (2*n+1 < heap.size()) {
                // If left child is smaller than parent
                if (comparator.compare(heap.get(n).key, heap.get(2 * n + 1).key) > 0) {
                    swap(n, (2 * n + 1));
                    n = 2 * n + 1;
                }
            }
            // If all its children are bigger
            if (temp == n)
                break;
        }
        return val;
    }

    // Sorted by keys
    public ArrayList<V> heapSort()
    {
        ArrayList<V> sorted = new ArrayList<>();
        while (heap.size() > 0)
        {
            sorted.add(removeMin());
        }
        return sorted;
    }

    private void swap(int a, int b)
    {
        Entry<K, V> temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    static class Entry<K, V> {
        K key;
        V val;

        public Entry()
        {
            key = null; val = null;
        }

        public Entry(K key, V val)
        {
            this.key = key; this.val = val;
        }
    }
}
