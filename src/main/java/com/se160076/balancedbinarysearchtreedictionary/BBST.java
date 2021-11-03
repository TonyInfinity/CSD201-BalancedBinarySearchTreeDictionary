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

    public static boolean insert(Word el) {
        Node<Word> p = new Node(el);
        //check tree is empty or not
        if (isEmpty()) {
            root = p;
        } else {
        //traverse tree
            Node<Word> tmp = root;
            Node<Word> previous = null;
            while (tmp != null) {
                previous = tmp;
                //if greater -> right
                
                if (el.compareTo(tmp.data) == 0){
                    return false;
                }
                
                else if (el.compareTo(tmp.data) > 0) {
                    tmp = tmp.right;
                } else {
                //if smaller < left
                    tmp = tmp.left;
                }
            }
            if (el.compareTo(previous.data) > 0) {
                previous.right = p;
            } else {
                previous.left = p;
            }
        }
        return true;
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
