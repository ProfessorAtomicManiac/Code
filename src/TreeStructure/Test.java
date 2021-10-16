package TreeStructure;

import java.util.Iterator;

public class Test {

    public static void main(String[] args)
    {
        /*
        BinaryTree<Character> bt = new BinaryTree<>('A');

        bt.setChildren(bt.getRoot(), 'B', 'C');
        bt.setChildren(bt.getRoot().leftChild, 'D', 'E');
        bt.setChildren(bt.getRoot().leftChild.rightChild, 'F', 'G');
        bt.setChildren(bt.getRoot().rightChild, 'H', 'I');
        bt.setChildren(bt.getRoot().rightChild.rightChild, 'J', 'K');
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());

        System.out.println();
        bt = new BinaryTree<>('A');
        bt.setChildren(bt.getRoot(), 'B', 'C');
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderIterative(bt.getRoot());


         */
        Tree<Character> tree = new Tree<>('A');
        tree.root().add('B');
        tree.root().add('C');
        tree.root().add('D');
        tree.root().add('E');
        tree.root().children().getElement(2).add('F');
        tree.root().children().getElement(2).add('G');
        tree.root().children().getElement(2).add('H');
        //tree.preOrderIterative(tree.root());
        //System.out.println();
        tree.preOrder(tree.root());
        System.out.println();
        //tree.postOrder(tree.root());
        for (Character character : tree) System.out.print(character);
    }
}
