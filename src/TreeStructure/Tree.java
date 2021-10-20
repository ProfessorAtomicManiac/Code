package TreeStructure;

import LinkedListStructures.LinkedList;
import LinkedListStructures.Stack;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree <E> implements Iterable<E>{

    private TreeNode<E> root;
    /* I've decided against having a size method which stores the number of nodes
     * because it is very difficult to keep track of which nodes are actually part
     * of the tree when doing certain operations
     */

    /**
     * Hopefully these methods are self-explanatory
     * I'm not going to javadoc all this so that I can rest
     */
    public Tree(E element)
    {
        root = new TreeNode<>(element);
        root.parent = null;
    }

    public TreeNode<E> root()
    {
        return root;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public boolean isInternal(TreeNode<E> node)
    {
        return node.children.size() > 0;
    }

    public boolean isRoot(TreeNode<E> node)
    {
        return node == root;
    }

    /**
     * Returns the depth of a node
     * @param node      node of a tree
     * @return          the depth of the node
     */
    public int depth(TreeNode<E> node)
    {
        if (node == root) return 0;
        else return depth(node.parent) + 1;
    }

    /**
     * Returns the height of a node
     * @param node      node of a tree
     * @return          the height of the node
     */
    public int height(TreeNode<E> node)
    {
        int maxHeight = 1;
        for (int i = 0; i < node.children.size(); i++)
        {
            maxHeight = Math.max(maxHeight, 1+height(node.children.getElement(i)));
        }
        return maxHeight;
    }

    /**
     * Prints the BinaryTree elements in preorder recursively
     * @param root  the root of the subtree
     */
    public void preOrder(TreeNode<E> root)
    {
        System.out.print(root.element);
        for (int i = 0; i < root.children.size(); i++) {
            preOrder(root.children().getElement(i));
        }
    }

    /**
     * Prints the BinaryTree elements in inorder iteratively
     * @param root  the root of the subtree
     */
    public void preOrderIterative(TreeNode<E> root)
    {
        Stack<TreeNode<E>> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode<E> n = s.pop();
            System.out.print(n.element);
            for (int i = n.children().size() - 1; i >= 0; i--) {
                s.push(n.children().getElement(i));
            }
        }
    }

    // Honestly not sure if postOrder is applicable to regular trees or if this is exactly right
    // I did not implement inorder because it seemed redundant
    // Also I couldn't figure out how to implement the iterative version of postOrder for a general tree
    // Pretty sure postOrder/inOrder traversal is useless for general trees anyways
    /**
     * Prints the BinaryTree elements in postorder recursively
     * @param root  the root of the subtree
     */
    public void postOrder(TreeNode<E> root)
    {
        for (int i = 0; i < root.children.size(); i++) {
            postOrder(root.children().getElement(i));
        }
        System.err.println(root.element);
    }

    /**
     * Returns an iterator which iterates preorder elements
     * @return  returns the iterator
     */
    public Iterator<E> iterator() {
        //return new TreeIterator<E>();
        return new TreeIterator<>(this);
    }

    // Note: The way the tree will iterate through the tree is preorder traversal
    // Never really implemented an Iterator before so this is pretty much an experiment
    private static class TreeIterator<E> implements Iterator<E>
    {
        int current;
        ArrayList<E> order = new ArrayList<>();

        /**
         * Creates an Iterator object
         * @param tree  the tree to be iterated on
         */
        public TreeIterator(Tree<E> tree)
        {
            current = 0;
            // Iterative version of preOrder but stores results in array
            Stack<TreeNode<E>> s = new Stack<>();
            s.push(tree.root);
            while (!s.isEmpty()) {
                TreeNode<E> n = s.pop();
                //System.err.println(n.element);
                order.add(n.element);
                for (int i = n.children().size() - 1; i >= 0; i--) {
                    s.push(n.children().getElement(i));
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
            return order.get(current-1);
        }
    }

    static class TreeNode<E>
    {
        E element;
        TreeNode<E> parent;
        LinkedList<TreeNode<E>> children;

        /**
         * All methods in this class are self-explanatory
         * I already lost my sanity in writing these
         */
        public TreeNode(E element) {
            parent = null;
            children = new LinkedList<>();
            this.element = element;
        }

        public TreeNode(E element, TreeNode<E> parent, LinkedList<TreeNode<E>> children) {
            this.parent = parent;
            this.children = children;
            this.element = element;
        }

        public TreeNode<E> parent()
        {
            return parent;
        }

        public LinkedList<TreeNode<E>> children()
        {
            return children;
        }

        // Note: I assume the programmer will build the tree from the root to the leaves
        // otherwise, the root would be inconsistent and incorrect

        public void setParent(E parent)
        {
            this.parent = new TreeNode<>(parent);
        }

        public void setChildren(LinkedList<TreeNode<E>> children)
        {
            this.children = children;
        }

        public void add(E element)
        {
            this.children.append(new TreeNode<>(element));
        }

        public void pop()
        {
            this.children.removeAtTail();
        }

        public boolean remove(int index)
        {
            // I'm pretty sure my linked list class handles out of bound indexes,
            // otherwise I'm a bad programmer
            return children.removeIndex(index);
        }

        public boolean remove(E element)
        {
            TreeNode<E> target = new TreeNode<>(element);
            return children.removeValue(target);
        }
    }
}
