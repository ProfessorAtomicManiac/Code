
package TreeStructure;

import LinkedListStructures.Stack;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;

    // This is relevant as to how the iterators iterate through the tree
    private Traversal traversal;

    /**
     * Creates an empty BinaryTree
     */
    public BinaryTree() {
        root = null;
        traversal = Traversal.PREORDER;
    }

    /**
     * Creates a BinaryTree with root as the root of the BinaryTree
     * @param root  The root of the BinaryTree
     */
    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
        traversal = Traversal.PREORDER;
    }

    /**
     * Creates a BinaryTree with the element set as the value of the root of the BinaryTree
     * @param element   The element of the root of the BinaryTree
     */
    public BinaryTree(E element) {
        root = new BinaryTreeNode<>(element);
        traversal = Traversal.PREORDER;
    }

    /**
     * Gets the traversal mode for the iterator
     * @return      The type of traversal the iterator is using
     */
    public Traversal getTraversal()
    {
        return traversal;
    }

    /**
     * Set the traversal mode for the iterator
     * @param traversal     The type of traversal you set the iterator to
     */
    public void setTraversal(Traversal traversal)
    {
        this.traversal = traversal;
    }

    /**
     * Constructs a BinaryTree from an array of preOrder and inOrder elements
     * @param preOrder      An array of preOrdered elements of a BinaryTree
     * @param inOrder       An array of inOrdered elements of a BinaryTree
     * @return              A BinaryTreeNode which can be used to contruct a new BinaryTree
     */
    public BinaryTreeNode<E> createBinaryTree(E[] preOrder, E[] inOrder)
    {
        int begin = 0;
        int end = preOrder.length;
        int preOrderIndex = 0;
        boolean[] visited = new boolean[preOrder.length];
        return createBinaryTree(preOrder, inOrder, begin, end, preOrderIndex, visited);
    }

    /**
     * Specific recursive version of createBinaryTree that has more parameters to do certain functions
     * The user should not call this function but should use the overloaded function with fewer parameters
     * @param preOrder      An array of preOrdered elements of a BinaryTree
     * @param inOrder       An array of inOrdered elements of a BinaryTree
     * @param begin         Where to start iterating through the preOrder elements. Points to the starting element
     *                      This is used when the method divides the tree into the left child and right child
     * @param end           Where to stop iterating through the preOrder elements. Points to the element after the element
     *                      where you want to stop. This is used when the method divides the tree into the left
     *                      and right child.
     * @param preOrderIndex This is used to iterate through the preOrder elements
     * @param visited       This is used to record which nodes have already been constructed to avoid duplicate ones
     * @return              A BinaryTreeNode which can be used to construct a new BinaryTree
     */
    // Yeah I know it has too many parameters
    public BinaryTreeNode<E> createBinaryTree(E[] preOrder, E[] inOrder, int begin, int end, int preOrderIndex, boolean[] visited)
    {
        // Iterates through preOrder and checks which nodes have already been constructed
        visited[preOrderIndex] = true;

        System.err.println(preOrderIndex + ": " + preOrder[preOrderIndex]);
        // base case if starting index = end (end is the index after the last possible index)
        if (begin+1 >= end) {
            return new BinaryTreeNode<>(preOrder[preOrderIndex]);
        }

        // Each time find the element's location and set that as the root
        int rootIndex = search(inOrder, preOrder[preOrderIndex]);
        BinaryTreeNode<E> root = new BinaryTreeNode<>(inOrder[rootIndex]);

        // Divide the left of the root as the left subtree
        root.leftChild = createBinaryTree(preOrder, inOrder, begin, rootIndex, preOrderIndex+1, visited);

        System.err.println(root.element + " -> " + root.leftChild.element);

        // Find where the right subtree starts based on what nodes have been constructed
        for (; preOrderIndex < visited.length; preOrderIndex++)
        {
            if (visited[preOrderIndex]) {
                continue;
            }
            break;
        }
        visited[preOrderIndex] = true;

        // Divide the right of the root as the right subtree
        root.rightChild = createBinaryTree(preOrder, inOrder, rootIndex+1, end, preOrderIndex, visited);
        System.err.println(root.element + " -> " + root.rightChild.element);
        return root;
    }

    /**
     * Simple linear search (Binary search doesn't work with unsorted arrays)
     * @param arr       The array to be searched
     * @param element   The element you are looking for
     * @return          Index of the element, if it doesn't exist it returns -1
     */
    // An array storing inorder elements of a binary tree is not sorted
    public int search(E[] arr, E element)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == element)
                return i;
        }
        return -1;
    }

    /**
     * Returns the depth of the node
     * @param node  The node
     * @return      The depth of the node
     */
    public int getDepth(BinaryTreeNode<E> node)
    {
        if (node == root) return 0;
        else return getDepth(node.parent) + 1;
    }

    /**
     * Returns the root element
     * @return  the root element
     */
    public E getRootElement()
    {
        return root.element;
    }

    /**
     * Returns the root node
     * @return  the root
     */
    public BinaryTreeNode<E> getRoot()
    {
        return root;
    }

    /**
     * Returns a boolean based on whether the BinaryTree is empty or not
     * @return a boolean based on whether its empty or not
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Returns true if the node has children, false if it does not
     * @param node  The node
     * @return      boolean
     */
    // I assume that every node has 0 or 2 children
    public boolean isInternal(BinaryTreeNode<E> node)
    {
        return node.leftChild != null;
    }

    /**
     * Returns true if the node is a root
     * @param node  The node
     * @return      boolean
     */
    public boolean isRoot(BinaryTreeNode<E> node)
    {
        return node == root;
    }

    /**
     * Prints the BinaryTree elements in inorder recursively
     * @param root  the root of the subtree
     */
    public void inOrder(BinaryTreeNode<E> root)
    {
        if (root.leftChild != null)
            inOrder(root.leftChild);
        System.out.print(root.element + " ");
        if (root.rightChild != null)
            inOrder(root.rightChild);
    }

    /**
     * Prints the BinaryTree elements in inorder iteratively
     * @param root  the root of the subtree
     */
    public void inOrderIterative(BinaryTreeNode<E> root)
    {
        Stack<BinaryTreeNode<E>> visitedNodes = new Stack<>();
        visitedNodes.push(root);

        BinaryTreeNode<E> current = null;

        while (!visitedNodes.isEmpty())
        {
            if (visitedNodes.peek().leftChild != null && current != visitedNodes.peek()) {
                current = visitedNodes.peek();
                visitedNodes.push(visitedNodes.peek().leftChild);
                continue;
            }
            if (visitedNodes.peek().rightChild != null) {
                BinaryTreeNode<E> tempNode = visitedNodes.peek();
                System.out.print(visitedNodes.pop().element + " ");
                current = visitedNodes.peek();
                visitedNodes.push(tempNode.rightChild);
                continue;
            }
            System.out.print(visitedNodes.pop().element + " ");
        }
    }

    /**
     * Prints the BinaryTree elements in preorder recursively
     * @param root  the root of the subtree
     */
    public void preOrder(BinaryTreeNode<E> root)
    {
        System.out.print(root.element + " ");
        if (root.leftChild != null)
            preOrder(root.leftChild);
        if (root.rightChild != null)
            preOrder(root.rightChild);
    }

    /**
     * Prints the BinaryTree elements in preorder iteratively
     * @param root  the root of the subtree
     */
    public void preOrderIterative(BinaryTreeNode<E> root)
    {
        Stack<BinaryTreeNode<E>> visitedNodes = new Stack<>();
        visitedNodes.push(root);
        while (!visitedNodes.isEmpty())
        {
            BinaryTreeNode<E> temp = visitedNodes.pop();
            System.out.println(temp.element + " ");
            if (temp.rightChild != null)
                visitedNodes.push(temp.rightChild);
            if (temp.leftChild != null)
                visitedNodes.push(temp.leftChild);
        }
    }

    /**
     * Prints the BinaryTree elements in postorder recursively
     * @param root  the root of the subtree
     */
    public void postOrder(BinaryTreeNode<E> root)
    {
        if (root.leftChild != null)
            postOrder(root.leftChild);
        if (root.rightChild != null)
            postOrder(root.rightChild);
        System.out.print(root.element);
    }

    /**
     * Prints the BinaryTree elements in postorder iteratively
     * @param root  the root of the subtree
     */
    public void postOrderIterative(BinaryTreeNode<E> root)
    {
        Stack<BinaryTreeNode<E>> s = new Stack<>();
        Stack<BinaryTreeNode<E>> visitedNodes = new Stack<>();
        s.push(root);

        while (s.peek() != null) {
            // Push the root to stack (Done in previous iteration)

            // Push the left children
            if (s.peek().leftChild != null && visitedNodes.contains(s.peek().leftChild) == -1)
            {
                s.push(s.peek().leftChild);
                continue;
            }
            // Push the right children
            if (s.peek().rightChild != null && visitedNodes.contains(s.peek().rightChild) == -1)
            {
                s.push(s.peek().rightChild);
                continue;
            }

            // pop when no children left
            visitedNodes.push(s.pop());
            System.out.print(visitedNodes.peek().element);
        }
    }

    /**
     * Returns an iterator based on what traversal is set
     * @return  returns the iterator based on what traversal is set
     */
    public Iterator<E> iterator() {
        return new BinaryTreeIterator<>(this);
    }


    // Note: The way the tree will iterate through the tree is based on the traversal variable
    // Never really implemented an Iterator before so this is pretty much an experiment
    private static class BinaryTreeIterator<E> implements Iterator<E>
    {
        int current;

        // Stores the order in an array
        ArrayList<E> order = new ArrayList<>();

        /**
         * Creates an Iterator object
         * @param tree  the tree to be iterated on
         */
        public BinaryTreeIterator(BinaryTree<E> tree)
        {
            // Stores all the elements into an Arraylist so that
            // the .next() methods are just iterating through an array
            // Also if user decides to change traversal midway through the program
            // it will not break the iterator
            current = 0;

            Stack<BinaryTreeNode<E>> visitedNodes = new Stack<>();

            // I have no idea what an "enhanced switch loop" is but my IDE recommended it so
            // it automatically formatted my code the way it is
            switch (tree.getTraversal()) {

                // These methods are the exact same as the traversal iterative method
                // except that it appends its results to an arraylist called order
                case PREORDER -> {
                    visitedNodes.push(tree.getRoot());
                    while (!visitedNodes.isEmpty()) {
                        BinaryTreeNode<E> temp = visitedNodes.pop();
                        order.add(temp.element);
                        if (temp.rightChild != null)
                            visitedNodes.push(temp.rightChild);
                        if (temp.leftChild != null)
                            visitedNodes.push(temp.leftChild);
                    }
                }
                case INORDER -> {
                    visitedNodes.push(tree.getRoot());
                    BinaryTreeNode<E> current = null;
                    while (!visitedNodes.isEmpty()) {
                        if (visitedNodes.peek().leftChild != null && current != visitedNodes.peek()) {
                            current = visitedNodes.peek();
                            visitedNodes.push(visitedNodes.peek().leftChild);
                            continue;
                        }
                        if (visitedNodes.peek().rightChild != null) {
                            BinaryTreeNode<E> tempNode = visitedNodes.peek();
                            order.add(visitedNodes.pop().element);
                            current = visitedNodes.peek();
                            visitedNodes.push(tempNode.rightChild);
                            continue;
                        }
                        order.add(visitedNodes.pop().element);
                    }
                }
                case POSTORDER -> {
                    Stack<BinaryTreeNode<E>> s = new Stack<>();
                    s.push(tree.getRoot());

                    while (s.peek() != null) {
                        // Push the root to stack (Done in previous iteration)

                        // Push the left children
                        if (s.peek().leftChild != null && visitedNodes.contains(s.peek().leftChild) == -1)
                        {
                            s.push(s.peek().leftChild);
                            continue;
                        }
                        // Push the right children
                        if (s.peek().rightChild != null && visitedNodes.contains(s.peek().rightChild) == -1)
                        {
                            s.push(s.peek().rightChild);
                            continue;
                        }

                        // pop when no children left
                        visitedNodes.push(s.pop());
                        order.add(visitedNodes.peek().element);
                    }
                }
            }
        }

        /**
         * Returns whether a next element exists
         * @return  boolean
         */
        @Override
        public boolean hasNext() {
            return current < order.size();
        }

        /**
         * Returns the next element
         * @return     The next element
         */
        @Override
        public E next() {
            current++;
            return order.get(current - 1);
        }
    }
}

enum Traversal {
    PREORDER,
    POSTORDER,
    INORDER
}

class BinaryTreeNode<E>
{
    E element;
    BinaryTreeNode<E> parent;
    BinaryTreeNode<E> leftChild;
    BinaryTreeNode<E> rightChild;

    /**
     * All methods in this class are self-explanatory
     * I already lost my sanity in writing these
     */
    public BinaryTreeNode(E element) {
        parent = null;
        leftChild = null;
        rightChild = null;
        this.element = element;
    }

    public BinaryTreeNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> leftChild, BinaryTreeNode<E> rightChild) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.element = element;
    }

    /*
     * NOTE: The following operations assumes that the first parameter is
     * already part of the tree. This is important for calculating the height
     * of the tree. There are probably better ways to add a subtree to a tree.
     * Also it does not update the root accordingly if necessary.
     */

    public void setParent(E parent)
    {
        this.parent = new BinaryTreeNode<>(parent);
    }

    public void setLeftChild(E leftChild)
    {
        BinaryTreeNode<E> temp = new BinaryTreeNode<>(leftChild);
        this.leftChild = temp;
        temp.parent = this;

    }

    public void setRightChild(E rightChild)
    {
        BinaryTreeNode<E> temp = new BinaryTreeNode<>(rightChild);
        this.rightChild = temp;
        temp.parent = this;
    }
    public void setChildren(E leftChild, E rightChild)
    {
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public void setTree(E parent, E leftChild, E rightChild)
    {
        this.setChildren(leftChild, rightChild);
        this.setParent(parent);
    }
}