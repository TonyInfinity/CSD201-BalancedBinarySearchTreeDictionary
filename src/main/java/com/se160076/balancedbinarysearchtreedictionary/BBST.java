/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

/**
 *
 * @author Tony
 */
public class BBST {

    static Node root = null;

    public static boolean isEmpty() {
        return root == null;
    }

    public static void insert(Word el) {
        Node<Word> p = new Node(el);
        if (isEmpty()) {
            root = p;
        } else {
            Node<Word> tmp = root;
            Node<Word> pre = null;
            while (tmp != null) {
                pre = tmp;
                if (el.compareTo(tmp.data) == 1) {
                    tmp = tmp.right;
                } else {
                    tmp = tmp.left;
                }
            }
            if (el.compareTo(pre.data) == 1) {
                pre.right = p;
            } else {
                pre.left = p;
            }
        }
    }

    public static Node createBBST(Word array[]) {
        return createBBST(array, 0, array.length - 1);
    }

    public static Node createBBST(Word array[], int start, int end) {
        if (array == null || array.length == 0 || start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node root = new Node(array[mid]);

        root.setLeft(createBBST(array, start, mid - 1));
        root.setRight(createBBST(array, mid + 1, end));

        return root;
    }

    public static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.getLeft());
        System.out.print(root.getData() + " ");
        inorderTraversal(root.getRight());
    }

}
