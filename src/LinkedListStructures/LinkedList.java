package LinkedListStructures;

// Singly Linked List
public class LinkedList<T> {

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

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
        head = null; tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public boolean insert(T data, int index) {
        if (index < 0 || index >= size) {
            System.err.println("Index out of scope");
            return false;
        }

        if (index == 0) insertAtHead(data);
        else if (index == size-1) insertAtTail(data);
        else {
            Node<T> backNode = head;
            for (int i = 0; i < index-1; i++) {
                backNode = backNode.next;
            }
            Node<T> tempNode = new Node<>(data);
            tempNode.next = backNode.next;
            backNode.next = tempNode;
        }
        size++;
        return true;
    }

    public void insertAtHead(T data) {
        Node<T> tempNode = new Node<>(data);

        if (isEmpty()) {
            head = tempNode;
            tail = head;
        } else {
            tempNode.next = head;
            head = tempNode;
        }
        size++;
    }

    public void append(T data) {
        insertAtTail(data);
    }

    public void insertAtTail(T data) {
        Node<T> tempNode = new Node<>(data);

        if (isEmpty()) {
            head = tempNode;
            tail = head;
        } else {
            tail.next = tempNode;
            tail = tempNode;
        }
        size++;
    }

    public boolean removeIndex(int index) {
        if (index < 0 || index >= size) {
            System.err.println("Index out of scope");
            return false;
        }

        if (index == 0) removeAtHead();
        else if (index == size-1) removeAtTail();
        else {
            Node<T> backNode = head;
            for (int i = 0; i < index-1; i++) {
                backNode = backNode.next;
            }

            Node<T> tempNode = backNode.next.next;
            backNode.next.next = null;
            backNode.next = tempNode;
        }
        size--;
        return true;
    }

    public boolean removeValue(T data) {
        Node<T> tempNode = head;
        Node<T> nextTempNode = head.next;
        if (tempNode.data.equals(data)) {
            removeAtHead();
            return true;
        }
        for (int i = 0; i < size-1; i++) {
            if (nextTempNode.data.equals(data) && i == size-2) {
                removeAtTail();
                return true;
            } else if (nextTempNode.data.equals(data)) {
                tempNode.next = nextTempNode.next;
                nextTempNode.next = null;
                size--;
                return true;
            }
            tempNode = nextTempNode;
            nextTempNode = nextTempNode.next;
        }
        return false;
    }

    public void removeAtHead() {
        Node<T> tempNode = head.next;
        head.next = null;
        head = tempNode;
        size--;
    }

    public void removeAtTail() {
        Node<T> tempNode = head;
        for (int i = 0; i < size-2; i++) {
            tempNode = tempNode.next;
        }
        tempNode.next = null;
        tail = tempNode;
        size--;
    }

    public T getElement(int index) {
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }

    public T peekFirst() {
        if (isEmpty()) return null;
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) return null;
        return tail.data;
    }

    // Also returns index of element
    public int contains(T data) {
        Node<T> tempNode = head;
        for (int i = 0; i < size-1; i++) {
            if (tempNode.data.equals(data)) {
                return i;
            }
            tempNode = tempNode.next;
        }
        return -1;
    }
}

