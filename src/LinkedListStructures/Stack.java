package LinkedListStructures;

public class Stack <T> {

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

    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void push(T data) {
        if (size == 0) top = new Node<>(data);

        Node<T> tempNode = top;
        top = new Node<>(data);
        top.next = tempNode;

        size++;
    }

    @Override
    public String toString() {
        /* My IDE recommended StringBuilder instead of using String so I just used it. I don't actually know
           how it works fully, I just replaced it.
         */
        StringBuilder str = new StringBuilder("{");
        Node<T> tempNode = top;
        for (int i = 0; i < size; i++) {
            str.append(tempNode.data);
            if (i != size-1) str.append(", ");
            tempNode = tempNode.next;
        }
        str.append("}");
        return str.toString();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> tempNode = top;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }

    public T pop() {
        if (size == 0) return null;

        Node<T> returnNode = top;
        Node<T> tempNode = top.next;
        top.next = null;
        top = tempNode;
        size--;
        return returnNode.data;

    }

    public T peek() {
        if (size == 0) return null;
        return top.data;
    }

    // Returns the index
    public int contains(T data) {
        if (size == 0) return -1;

        Node<T> tempNode = top;
        for (int i = 0; i < size; i++) {
            if (tempNode.data == data) {
                return i;
            }
            tempNode = tempNode.next;
        }
        return -1;
    }

    /* Implement a method with signature transfer(S, T) that transfers all elements from stack S onto stack T,
       so that the element that starts at the top of S is the first to be inserted onto T, and the element at
       the bottom of S ends up at the top of T.
     */
    // This is done as a member method and stack T (denoted as stack in this method) can have elements in it already
    public Stack<T> transfer(Stack<T> stack) {
        int size = this.size;
        for (int i = 0; i < size; i++) {
            stack.push(this.pop());
        }
        return stack;
    }

    // This is if Stack T has no elements
    public Stack<T> transfer() {
        Stack<T> stack = new Stack<>();
        int size = this.size;
        for (int i = 0; i < size; i++) {
            stack.push(this.pop());
        }
        return stack;
    }
}

