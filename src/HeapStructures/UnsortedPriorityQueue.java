package HeapStructures;

import java.util.ArrayList;
import java.util.Comparator;

public class UnsortedPriorityQueue<K, V> {
    private ArrayList<Entry<K, V>> queue;
    private Comparator<K> comparator = null;

    public UnsortedPriorityQueue() {
        queue = new ArrayList<>();
    }

    public UnsortedPriorityQueue(Comparator<K> comparator)
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
        queue.add(new Entry<>(key, val));
    }

    public V min()
    {
        if (queue.size() == 0)
            return null;
        Entry<K, V> min = new Entry<>();
        for (Entry<K, V> kvEntry : queue) {
            if (min.key == null) {
                min.key = kvEntry.key;
                min.val = kvEntry.val;
                continue;
            }
            if (comparator.compare(min.key, kvEntry.key) > 0) {
                min.key = kvEntry.key;
                min.val = kvEntry.val;
            }
        }
        return min.val;
    }

    public V removeMin()
    {
        if (queue.size() == 0)
            return null;
        K min = null;
        int minIndex = -1;
        for (int i = 0; i < queue.size(); i++) {
            if (min == null) {
                min = queue.get(i).key;
                minIndex = i;
                continue;
            }
            if (comparator.compare(min, queue.get(i).key) > 0) {
                min = queue.get(i).key;
                minIndex = i;
            }
        }
        return queue.remove(minIndex).val;
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

