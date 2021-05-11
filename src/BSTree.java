/*
 * Name: Lohit Geddam
 * PID:  A16374851
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Lohit Geddam
 * @since  05/10/21
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<T>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        BSTNode temp = new BSTNode(null,null, key);
        if (findKey(key)) {
            return false;
        }
        if (key == null) {
            throw new NullPointerException();
        }
        if(this.root == null) {
            this.root = temp;
            nelems++;
        }
        else {
            BSTNode currentNode = this.root;
            while (currentNode != null) {
                if (temp.getKey().compareTo(currentNode.getKey()) == -1) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setleft(temp);
                        currentNode = null;
                        nelems++;
                        return true;
                    }
                    else {
                        currentNode = currentNode.left;
                    }
                }
                else {
                    if (currentNode.getRight() == null) {
                        currentNode.setright(temp);
                        currentNode = null;
                        nelems++;
                        return true;
                    }
                    else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        BSTNode currentNode = this.root;
        if (key == null) {
            throw new NullPointerException();
        }
        while (currentNode != null) {
            if (key.compareTo(currentNode.getKey()) == 0) {
                return true;
            }
            else if (key.compareTo(currentNode.getKey()) == -1) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode currentNode = this.root;
        while (currentNode != null) {
            if (key.compareTo(currentNode.getKey()) == 0) {
                currentNode.addNewInfo(data);
                currentNode = null;
            }
            else if (key.compareTo(currentNode.getKey()) == -1) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode currentNode = this.root;
        while (currentNode != null) {
            if (key.compareTo(currentNode.getKey()) == 0) {
                return currentNode.getDataList();
            }
            else if (key.compareTo(currentNode.getKey()) == -1) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(this.root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if(root == null) {
            return -1;
        }
        int leftHeight = findHeightHelper(root.left);
        int rightHeight = findHeightHelper(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* * * * * BST Iterator * * * * */

    /**
     * BSTree_Iterator class acts as a stack for the BST.
     */
    public class BSTree_Iterator implements Iterator<T> {
        Stack<T> stack;
        /**
         * BSTree_Iterator constructor initializes and populates
         * the iterator.
         */
        public BSTree_Iterator() {
            Stack<T> stack = new Stack<T>();
            Stack<BSTNode> nodeStack = new Stack<BSTNode>();
            BSTNode currentNode = root;
            while (!stack.empty() || currentNode != null) {
                if (currentNode != null) {
                    nodeStack.push(currentNode);
                    currentNode = currentNode.right;
                }
                else {
                    currentNode = nodeStack.pop();
                    stack.push(currentNode.getKey());
                    currentNode = currentNode.left;
                }
            }
            this.stack = stack;
        }

        /**
         * Check if the iterator has a next
         * @return whether or not the iterator has a next
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stack.pop();
        }
    }

    public Iterator<T> iterator() {
        /* TODO */
        return null;
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
