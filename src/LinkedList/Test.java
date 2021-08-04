package LinkedList;

public class Test {

    public static void main(String[] args) {
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
    }


}
