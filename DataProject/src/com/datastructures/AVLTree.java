package com.datastructures;

import java.util.ArrayList;

public class AVLTree<T> {

    public class Node {
        int height;
        Node left;
        Node right;
        ArrayList<T> elements;

        Node(T t) {
            this.elements = new ArrayList<>();
            this.elements.add(t);
        }
    }

    private Node root;

//    public Node find(double key) {
//        Node current = root;
//        while (current != null) {
//            if (current.key == key) {
//                break;
//            }
//            current = current.key < key ? current.right : current.left;
//        }
//        return current;
//    }

    public void insert(T t) {
        root = insert(root, t);
    }

    public void delete(T t) {
        root = delete(root, t);
    }

    public Node getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private Node insert(Node node, T key) {
        if (node == null) {
            return new Node(key);
        } else if (node.elements.get(0).hashCode() > key.hashCode()) {
            node.left = insert(node.left, key);
        } else if (node.elements.get(0).hashCode() < key.hashCode()) {
            node.right = insert(node.right, key);
        } else {
            // DUPLICATED T ELEMENTS
            node.elements.add(key);
        }
        return rebalance(node);
    }

    private Node delete(Node node, T key) {
        if (node.elements == null) {
            return node;
        } else if (node.elements.get(0).hashCode() > key.hashCode()) {
            node.left = delete(node.left, key);
        } else if (node.elements.get(0).hashCode() < key.hashCode()) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.elements.add(mostLeftChild.elements.get(0));
                node.right = delete(node.right, node.elements.get(0));
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }


    private Node mostLeftChild(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }


    /**
     * Serialize the tree to a string using an infix traversal.
     *
     * In other words, the tree items will be serialized in numeric order.
     *
     * @return String representation of the tree
     */
    public String serializeInfix(){
        StringBuilder str = new StringBuilder();
        serializeInfix (root, str, "\n");
        return str.toString();
    }

    /**
     * Internal method to infix-serialize a tree.
     *
     * @param t    Tree node to traverse
     * @param str  Accumulator; string to keep appending items to.
     */
    protected void serializeInfix(Node t, StringBuilder str, String sep){
        if (t != null){
            serializeInfix (t.left, str, sep);
            for(T t1 : t.elements){
                str.append(t1);
                str.append(sep);
            }
            serializeInfix (t.right, str, sep);
        }
    }

    @Override
    public String toString() {
        return serializeInfix();
    }
}