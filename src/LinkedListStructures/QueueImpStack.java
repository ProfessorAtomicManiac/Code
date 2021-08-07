package LinkedListStructures;

// Implement a Queue (class) using a given Stack (class).

public class QueueImpStack <T> {

    /* How this would work is that there are two Stack objects. In order to dequeue an element, we would need to
       pop all elements except for the last node and all those elements go to the other stack object. We then pop all
       the elements in the second stack object to reorder them correctly. (This could be avoided but that would involve
       a lot of excessive code, but it is inefficient)
     */
    private Stack<T> ordered;
    private Stack<T> residue;

    public QueueImpStack() {
        ordered = new Stack<>();
        residue = new Stack<>();
    }

    public int size() {
        return ordered.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void enqueue(T data) {
        ordered.push(data);
    }


    public T dequeue() {
        int size = ordered.size()-1;
        for (int i = 0; i < size; i++) {
            residue.push(ordered.pop());
        }
        T returnValue = ordered.pop();
        for (int i = 0; i < size; i++) {
            ordered.push(residue.pop());
        }
        return returnValue;
    }

    public T get(int index) {
        return ordered.get(index);
    }

    // Returns data at the front of the queue
    public T peekFront() {
        return ordered.get(size()-1);
    }

    public T peekBack() { return ordered.peek(); }

    // Returns the index (NOTE: Back is 0, front is size-1)
    public int contains(T data) {
        return ordered.contains(data);
    }

    // Prints from back to front
    @Override
    public String toString() {
        return ordered.toString();
    }
}
