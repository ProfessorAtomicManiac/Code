package TreeStructure;

import org.junit.Test;

public class TreeTest {

    public static void main(String[] args)
    {

    }

    // For the JUnit tests, it is complicated to check whether the stuff
    // put into the output stream is correct, so I just manually check
    // Earlier the code was in the main function, I just moved it to another @Test method
    // when I'm done to document my test cases

    @Test
    public void testPreOrder()
    {
        Tree<Character> tree = new Tree<>('A');
        tree.root().add('B');
        tree.root().add('C');
        tree.root().add('D');
        tree.root().add('E');
        tree.root().children().getElement(2).add('F');
        tree.root().children().getElement(2).add('G');
        tree.root().children().getElement(2).add('H');
        tree.preOrderIterative(tree.root());
        System.out.println();
        tree.preOrder(tree.root());
        System.out.println();
        tree.postOrder(tree.root());
        for (Character character : tree) System.out.print(character);
    }

    @Test
    public void testInOrder1()
    {
        BinaryTree<Character> bt = new BinaryTree<>('A');

        bt.getRoot().setChildren('B', 'C');
        bt.getRoot().leftChild.setChildren('D', 'E');
        bt.getRoot().leftChild.rightChild.setChildren('F', 'G');
        bt.getRoot().rightChild.setChildren('H', 'I');
        bt.getRoot().rightChild.rightChild.setChildren('J', 'K');
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());
    }

    @Test
    public void testInOrder2()
    {
        /*
        System.out.println();
        BinaryTree<Character> bt = new BinaryTree<>('A');
        bt.getRoot().setChildren('B', 'C');
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());

         */

        /*
        System.out.println();
        BinaryTree<Integer> bt = new BinaryTree<Integer>(1);
        bt.getRoot().setChildren(null, 2);
        bt.getRoot().rightChild.setChildren(3, null);
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());

         */

        /*
        BinaryTree<Integer> bt = new BinaryTree<>(null);
        //bt.getRoot().setChildren(null, 2);
        //bt.getRoot().rightChild.setChildren(3, null);
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());

         */

        BinaryTree<Integer> bt = new BinaryTree<>(1);
        bt.getRoot().setChildren(null, 2);
        //bt.getRoot().rightChild.setChildren(3, null);
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());
    }

    @Test
    public void testPostOrder() {
        BinaryTree<Character> bt = new BinaryTree<>('A');

        bt.getRoot().setChildren('B', 'C');

        bt.getRoot().leftChild.setChildren('D', 'E');
        bt.getRoot().leftChild.rightChild.setChildren('F', 'G');
        bt.getRoot().rightChild.setChildren('H', 'I');
        bt.getRoot().rightChild.rightChild.setChildren('J', 'K');


        bt.postOrder(bt.getRoot());
        System.out.println();
        bt.postOrderIterative(bt.getRoot());
    }

    @Test
    public void testInorderAndPreOrderConstruction() {
        BinaryTree<Character> bt = new BinaryTree<>('A');
        bt.getRoot().setChildren('B', 'C');
        bt.getRoot().leftChild.setChildren('D', 'E');
        bt.getRoot().leftChild.rightChild.setChildren('F', 'G');
        bt.getRoot().rightChild.setChildren('H', 'I');
        bt.getRoot().rightChild.rightChild.setChildren('J', 'K');

        Character[] preOrder = {'A', 'B', 'D', 'E', 'F', 'G', 'C', 'H', 'I', 'J', 'K'};
        Character[] inOrder = {'D', 'B', 'F', 'E', 'G', 'A', 'H', 'C', 'J', 'I', 'K'};
        BinaryTree<Character> tree = new BinaryTree<>(bt.createBinaryTree(preOrder, inOrder));
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.inOrder(tree.getRoot());
        System.out.println();

        bt = new BinaryTree<>('A');
        bt.getRoot().setChildren('B', 'C');
        Character[] preOrder2 = {'A', 'B', 'C'};
        Character[] inOrder2 = {'B', 'A', 'C'};
        tree = new BinaryTree<>(bt.createBinaryTree(preOrder2, inOrder2));
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.inOrder(tree.getRoot());
    }
}
