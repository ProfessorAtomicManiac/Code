
package TreeStructure;

import LinkedListStructures.Stack;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public BinaryTree(E element) {
        root = new BinaryTreeNode<>(element);
    }

    // Constructing a binary tree from its preorder and inorder
    // Yeah I know it has too many parameters
    public BinaryTreeNode<E> createBinaryTree(E[] preOrder, E[] inOrder, int begin, int end, int preOrderIndex, boolean[] visited)
    {
        // Iterate thru preOrder
        System.err.println(preOrderIndex + ": " + preOrder[preOrderIndex]);
        // base case if starting index = end (end is the index after the last possible index)
        if (begin+1 >= end) {
            //System.err.println(preOrder[preOrderIndex]);
            //System.err.println(preOrderIndex + " (" + begin + "," + end + ")");
            visited[preOrderIndex] = true;
            return new BinaryTreeNode<>(preOrder[preOrderIndex]);
        }

        // Each time find the element's location and set that as the root
        int rootIndex = search(inOrder, preOrder[preOrderIndex]);
        BinaryTreeNode<E> root = new BinaryTreeNode<>(inOrder[rootIndex]);

        // Divide the left of the root as the left subtree
        //System.err.println(preOrderIndex + " (" + begin + "," + end + ")");

        root.leftChild = createBinaryTree(preOrder, inOrder, begin, rootIndex, preOrderIndex+1, visited);
        System.err.println(root.element + " -> " + root.leftChild.element);
        preOrderIndex = rootIndex;
        System.err.println(preOrderIndex);
        // Divide the right of the root as the right subtree
        root.rightChild = createBinaryTree(preOrder, inOrder, rootIndex+1, end, preOrderIndex+1, visited);
        System.err.println(root.element + " -> " + root.rightChild.element);
        return root;
    }

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

    public int getDepth(BinaryTreeNode<E> node)
    {
        if (node == root) return 0;
        else return getDepth(node.parent) + 1;
    }

    public E getRootElement()
    {
        return root.element;
    }

    public BinaryTreeNode<E> getRoot()
    {
        return root;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    // I assume that every node has 0 or 2 children
    public boolean isInternal(BinaryTreeNode<E> node)
    {
        return node.leftChild != null;
    }

    public boolean isRoot(BinaryTreeNode<E> node)
    {
        return node == root;
    }

    public void inOrder(BinaryTreeNode<E> root)
    {
        if (root.leftChild != null)
            inOrder(root.leftChild);
        System.out.print(root.element + " ");
        if (root.rightChild != null)
            inOrder(root.rightChild);
    }

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

    public void preOrder(BinaryTreeNode<E> root)
    {
        System.out.print(root.element + " ");
        if (root.leftChild != null)
            preOrder(root.leftChild);
        if (root.rightChild != null)
            preOrder(root.rightChild);
    }


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

    public void postOrder(BinaryTreeNode<E> root)
    {
        if (root.leftChild != null)
            postOrder(root.leftChild);
        if (root.rightChild != null)
            postOrder(root.rightChild);
        System.out.print(root.element);
    }

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





}

class BinaryTreeNode<E>
{
    E element;
    BinaryTreeNode<E> parent;
    BinaryTreeNode<E> leftChild;
    BinaryTreeNode<E> rightChild;

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