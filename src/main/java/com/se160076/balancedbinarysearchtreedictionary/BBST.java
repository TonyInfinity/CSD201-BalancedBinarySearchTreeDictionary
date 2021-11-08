/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

import java.util.ArrayList;

/**
 *
 * @author Tony
 */
public class BBST {

    static Node<Word> root = null;

    public static boolean isEmpty() {
        return root == null;
    }

    public static Node<Word> insert(Node<Word> root, Word key) {
        if (root == null) {
            return new Node<>(key);
        }

        if (key.compareTo(root.getData()) < 0) {
            root.left = insert(root.left, key);
        } else if (key.compareTo(root.getData()) > 0) {
            root.right = insert(root.right, key);
        } else {
            System.out.println("Do You Wish To Update This Word's Translation?");
            boolean choice = Validator.checkInputYN();
            if (choice) {
                root.setData(new Word(key.getWord(), key.getTranslation()));
            }
        }
        return root;
    }

    public static Node<Word> deleteNode(Node<Word> root, Word key) {
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.getData()) < 0) {
            root.left = deleteNode(root.left, key);
        } else if (key.compareTo(root.getData()) > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null) {
                // update root to null
                return null;
            } // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null) {
                // find its inorder predecessor node
                Node<Word> predecessor = findMaximumKey(root.left);

                // copy value of the predecessor to the current node
                root.data = predecessor.data;

                // recursively delete the predecessor. Note that the
                // predecessor will have at most one child (left child)
                root.left = deleteNode(root.left, predecessor.data);
            } // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                Node<Word> child = (root.left != null) ? root.left : root.right;
                root = child;
            }
        }
        return root;
    }

    public static Node<Word> findMaximumKey(Node<Word> ptr) {
        while (ptr.right != null) {
            ptr = ptr.right;
        }
        return ptr;
    }

    public static Node balance(Word array[]) {
        return balance(array, 0, array.length - 1);
    }

    public static Node<Word> balance(Word array[], int start, int end) {
        if (array == null || array.length == 0 || start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node<Word> p = new Node(array[mid]);

        p.setLeft(balance(array, start, mid - 1));
        p.setRight(balance(array, mid + 1, end));

        return p;
    }

    public static void inorderTraversal(Node<Word> root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.getLeft());
        System.out.print(root.getData() + " ");
        inorderTraversal(root.getRight());
    }

    public static ArrayList<Word> inorderTraversalStore(Node<Word> root, ArrayList<Word> arr) {
        if (root == null) {
            return arr;
        }
        inorderTraversalStore(root.getLeft(), arr);
        arr.add(root.getData());
        inorderTraversalStore(root.getRight(), arr);
        return arr;
    }

    public static Node<Word> search(String english, Node<Word> root) {

        Word searchWord = new Word(english);
        return search(searchWord, root);
    }

    public static Node<Word> search(Word data, Node<Word> root) {

        Node<Word> traverse = root;

        while (traverse != null) {
            if (traverse.getData().compareTo(data) == 0) {
                return traverse;
            } else if (traverse.getData().compareTo(data) < 0) {
                traverse = traverse.right;
            } else {
                traverse = traverse.left;
            }
        }
        return null;
    }

    //Print Path Between 2 Nodes.  
    public static boolean getPath(Node<Word> node, ArrayList<Word> arr, Word findWord) {
        //check empty -> there is no path
        if (node == null) {
            return false;
        }
        //push the root value in the arr
        arr.add(node.data);

        //check word equals to findWord
        if (node.data.compareTo(findWord) == 0) {
            return true;
        }

        //check left and right
        if (getPath(node.left, arr, findWord) || getPath(node.right, arr, findWord)) {
            return true;
        }

        //if can't find the node in left or right
        //the array has to remove the current node
        arr.remove(arr.size() - 1);
        return false;
    }

    public static void printPathBetweenNodes(String english1, String english2, Node<Word> root) {
//        if (isEmpty()) {
//            System.out.println("The dictionary is empty!");
//            return;
//        }
        printPathBetweenNodes(root, new Word(english1), new Word(english2));
    }

    //Print out the path between two nodes in bbst
    public static void printPathBetweenNodes(Node<Word> root, Word w1, Word w2) {
        //arraylist to store path of w1
        ArrayList<Word> path1 = new ArrayList<>();
        //arraylist to store path of w2
        ArrayList<Word> path2 = new ArrayList<>();

        //getpath for two array
        getPath(root, path1, w1);
        getPath(root, path2, w2);

        //check the node is exist!
        if (path1.isEmpty() && path2.isEmpty()) {
            System.out.println("Both " + w1.getWord() + " And " + w2.getWord()
                    + " Does Not Exist.");
            return;
        }
        if (path1.isEmpty()) {
            System.out.println("The Word " + w1.getWord() + " Does Not Exist.");
            return;
        } else if (path2.isEmpty()) {
            System.out.println("The Word " + w2.getWord() + " Does Not Exist.");
            return;
        }

        //
//        System.out.println("Path1:");
//        for (Word word : path1) {
//            System.out.println(word);
//        }
//        System.out.println("Path2:");
//        for (Word word : path2) {
//            System.out.println(word);
//        }
        //
        int intersection = -1;
        //Get intersection point
        int i = 0, j = 0;
        //keep moving ultil there is no intersection point
        while (i != path1.size() || j != path2.size()) {
            //check value for two array equals or not
            if (i == j && path1.get(i).getWord().compareToIgnoreCase(path2.get(j).getWord()) == 0) {
                i++;
                j++;
            } else {
                intersection = j - 1;
                break;
            }
        }

        System.out.println("The Path Between The Two Nodes Is: ");
        //print the required path
        //traverse from reverse path1 -> unreverse path2
        for (i = path1.size() - 1; i > intersection; i--) {
            System.out.print(path1.get(i).getWord() + " ");
        }

        for (i = intersection; i < path2.size(); i++) {
            System.out.print(path2.get(i).getWord() + " ");
        }
    }
}
