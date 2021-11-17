package HeapStructures;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedPriorityQueue<K, V> {
    private ArrayList<Entry<K, V>> queue;
    private Comparator<K> comparator = null;

    public SortedPriorityQueue() {
        queue = new ArrayList<>();
    }

    public SortedPriorityQueue(Comparator<K> comparator)
    {
        this.comparator = comparator;
    }

    public int size()
    {
        return queue.size();
    }

    public boolean isEmpty()
    {
        return queue.size() == 0;
    }

    public void setComparator(Comparator<K> comparator)
    {
        this.comparator = comparator;
    }

    public void insert(K key, V val)
    {
        // Binary-Search (This still takes O(n) time because inserting an element into an arraylist will take O(n) time)
        queue.add(search(queue, key), new Entry<>(key, val));
    }

    public V min()
    {
        return queue.get(0).val;
    }

    public V removeMin()
    {
        return queue.remove(0).val;
    }

    // Binary Insertion (Probably quite erroneous)
    private int search(ArrayList<Entry<K, V>> arr, K key)
    {
        if (arr.size() == 0)
            return 0;
        int begin = 0;
        int end = arr.size()-1;
        while (begin <= end)
        {
            int mid = begin + (end-begin)/2;
            if (comparator.compare(arr.get(mid).key, key) == 0)
                return mid + 1;
            else if (comparator.compare(arr.get(mid).key, key) > 0)
                end = mid - 1;
            else
                begin = mid + 1;
        }
        if (begin >= arr.size())
            return (comparator.compare(arr.get(end).key, key) > 0) ? end : end + 1;
        return (comparator.compare(arr.get(begin).key, key) > 0) ? begin : begin + 1;
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
