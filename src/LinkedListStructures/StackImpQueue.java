package LinkedListStructures;

// Implement a Stack (class) using a given Queue (class).

public class StackImpQueue<T> {

    /* How this would work is that there are two Queue objects. In order to pop an element, we would need to
       dequeue all elements except for the back node and all those elements go to the other queue object.
     */
    private Queue<T> queueT;
    private Queue<T> queueF;
    private boolean queue;

    public StackImpQueue() {
        queueT = new Queue<>();
        queueF = new Queue<>();
        queue = true;
    }

    public int size() {
        // This checks which queue is being occupied by the elements
        return (queue ? queueT.size() : queueF.size());
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public void push(T data) {
        if (queue) queueT.enqueue(data);
        else queueF.enqueue(data);
    }

    @Override
    public String toString() {
        return (queue ? queueT.toString() : queueF.toString());
    }

    public T pop() {
        if (queue) {
            int size = queueT.size()-1;
            for (int i = 0; i < size; i++) {
                queueF.enqueue(queueT.dequeue());
            }
            queue = false;
            return queueT.dequeue();
        }
        else {
            int size = queueF.size()-1;
            for (int i = 0; i < size; i++) {
                queueT.enqueue(queueF.dequeue());
            }
            queue = true;
            return queueF.dequeue();
        }
    }

    public T peek() {
        return (queue ? queueT.peekBack() : queueF.peekBack());
    }

    // Returns the index
    public int contains(T data) {
        return (queue ? queueT.contains(data) : queueF.contains(data));
    }
}
