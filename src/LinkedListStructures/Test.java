package LinkedListStructures;

public class Test {

    public static void main(String[] args) {
        /* These are all the tests for the LinkedList class
        LinkedList<Integer> list = new LinkedList<Integer>();
        //list.insertAtHead(1);
        list.append(3);
        list.append(23);
        list.append(8349);
        list.append(324);
        //list.insert(69, 1);
        //list.removeIndex(3);

        //list.removeAtTail();
        //list.removeAtHead();

        System.out.println("The list size is: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getElement(i));
        }
        //System.out.println("First element: " + list.peekFirst());
        //System.out.println("Last element: " + list.peekLast());

        //System.out.println(list.contains(3));
        list.removeValue(3);

        System.out.println("The list size is: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getElement(i));
        }
        */

        // ------------------------------------------------------------------------------------------------------------------------------------------
        // Testing for Stack Classes

        /*  What values are returned during the following series of stack operations, if executed upon an initially empty stack?
         *  push(5), push(3), pop(), push(2), push(8), pop(), pop(), push(9), push(1), pop(), push(7), push(6), pop(), pop(), push(4), pop(), pop().
         */

        /*
        System.out.println("Values expected to return: ");
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(3);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(8);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(9);
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(7);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
         */

        /* Implement a method with signature transfer(S, T) that transfers all elements from stack S onto stack T,
           so that the element that starts at the top of S is the first to be inserted onto T, and the element at
           the bottom of S ends up at the top of T.
         */

        /*
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 9; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        stack = stack.transfer();
        System.out.println(stack);

        Stack<Integer> stack2 = new Stack<>();
        stack2.push(99);
        stack2.push(98);
        stack.transfer(stack2);
        System.out.println(stack2);
         */

        //System.out.println(stack.peek());
        //System.out.println(stack.contains(8));

        //-------------------------------------------------------------------------------------------------------------------
        // Testing for Queue Class

        /* Suppose an initially empty queue Q has performed a total of 32 enqueue operations, 10 first operations,
           and 15 dequeue operations, 5 of which returned null to indicate an empty queue. What is the current size of Q?
           Answer: There could be 5 first or 5 dequeue operations (or 4 first and 1 dequeue operations, etc) that returned null
           Q can have the most elements if 5 dequeue operations were to return null. This would result in 32-10=22 elements.
           Q can have the least elements if 5 first operations were to return null. This woud result in 32-15=17 elements.
           The current size of Q can range from 17 to 22 elements.
         */

        /* What values are returned during the following sequence of queue operations, if executed on an initially empty queue?
           enqueue(5), enqueue(3), dequeue(), enqueue(2), enqueue(8), dequeue(), dequeue(), enqueue(9), enqueue(1), dequeue(),
           enqueue(7), enqueue(6), dequeue(), dequeue(), enqueue(4), dequeue(), dequeue().
         */
        /*
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(5);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(2);
        queue.enqueue(8);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(9);
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(7);
        queue.enqueue(6);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(4);
        queue.dequeue();
        queue.dequeue();
        //System.out.println(queue.peek());
        //System.out.println(queue.contains(234));
        System.out.println(queue);
        // Prints out {4}
         */

        /*
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
         */

        //-----------------------------------------------------------------------------------------------------------------

        // Implement a Stack (class) using a given Queue (class).
        /*
        StackImpQueue<Integer> stack = new StackImpQueue<>();
        //System.out.println(stack.size());
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        //System.out.println(stack.isEmpty());
        //System.out.println(stack.size());
        //System.out.println(stack.contains(9));
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
         */

        /*
        // Implement a Quene (class) using a given Stack (class).
        QueueImpStack<Integer> queue = new QueueImpStack<>();
        //System.out.println(queue.size());
        //System.out.println(queue.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        //System.out.println(queue.isEmpty());
        //System.out.println(queue.size());
        //System.out.println(queue.contains(3));

        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue);
         */
    }


}
