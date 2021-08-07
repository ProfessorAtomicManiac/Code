package LinkedListStructures;

public class Queue <T> {

    // Node class
    /*  This exact code for this node class is present in Stack and Queue. It is probably a good idea to
     *  remove this class and put it as a separate class file for the 3 classes to use, however that would
     *  involve changing a lot of code that I don't want to do, but I'd like to say that I'm completely aware
     *  of this and that if one change should be made, it would be this.
     */
    private static class Node<T> {

        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node<T> back; // where elements are inserted
    private Node<T> front; // where elements are removed
    private int size;

    public Queue() {
        front = null;
        back = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void enqueue(T data) {
        Node<T> tempNode = new Node<>(data);
        if (size == 0) {
            front = tempNode;
            back = tempNode;
        }
        else {
            tempNode.next = back;
            back = tempNode;
        }
        size++;
    }

    public T dequeue() {
        if (size == 0) return null;
        else {
            Node<T> returnNode = front;
            Node<T> tempNode = back;
            for (int i = 0; i < size-2; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = null;
            front = tempNode;
            size--;
            return returnNode.data;
        }
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> tempNode = back;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }

    // Returns data at the front of the queue
    public T peekFront() {
        return front.data;
    }

    public T peekBack() { return back.data; }

    // Returns the index (NOTE: Back is 0, front is size-1)
    public int contains(T data) {
        if (size == 0) return -1;

        Node<T> tempNode = back;
        for (int i = 0; i < size; i++) {
            if (tempNode.data == data) {
                return i;
            }
            tempNode = tempNode.next;
        }
        return -1;
    }

    // Prints from back to front
    @Override
    public String toString() {
        /* My IDE recommended StringBuilder instead of using String, so I just used it. I don't actually know
           how it works fully, I just replaced it.
         */
        StringBuilder str = new StringBuilder("{");
        Node<T> tempNode = back;
        for (int i = 0; i < size; i++) {
            str.append(tempNode.data);
            if (i != size-1) str.append(", ");
            tempNode = tempNode.next;
        }
        str.append("}");
        return str.toString();
    }
}
