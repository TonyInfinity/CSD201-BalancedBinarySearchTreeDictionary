/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;


import java.lang.reflect.Array;
import java.util.ArrayList;


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

//<<<<<<< Updated upstream
//=======
    public static ArrayList<Word> inorderTraversalStore(Node<Word> root, ArrayList<Word> arr) {
        if (root == null) {
            return arr;
        }
        inorderTraversalStore(root.getLeft(), arr);
        arr.add(root.getData());
        inorderTraversalStore(root.getRight(), arr);
        return arr;
    }
    
    public static Node<Word> search(String english){
        if(isEmpty()){
            System.out.println("The list is empty!");
            return null;
        }
        Word searchWord = new Word(english);
        return search(searchWord);
    }
    
    public static Node<Word> search(Word data){
          
        Node<Word> traverse = root;
        
        while(traverse != null){
            if(traverse.getData().compareTo(data) == 0){
                return traverse;
            }else if (traverse.getData().compareTo(data) > 0){
                traverse = traverse.right;
            }else{
                traverse = traverse.left;
            }           
        }
        return null;
    }
    
    //Print Path Between 2 Nodes.  
    public static boolean getPath(Node node, ArrayList<Word> arr, Word findWord){
        //check empty -> there is no path
        if(node == null){
            return false;
        }
        //push the root value in the arr
        arr.add((Word)node.data);
        
        //check word equals to findWord
        if(((Word)node.data).compareTo(findWord) == 0){
            return true;
        }
        
        //has left and smaller value -> left
        if(node.left != null && ((Word)node.data).compareTo(findWord) < 0){
           if(getPath(node.left, arr, findWord)){
               return true;
           }
        }
        //has right right and bigger value -> right
        if(node.right != null && ((Word)node.data).compareTo(findWord) > 0){
           if(getPath(node.right, arr, findWord)){
               return true;
           }
        }
        
        //if can't find the node in left or right
        //the array has to remove the current node
        arr.remove(arr.size() - 1);
        return false;
    }
    
    public static void printPathBetweenNodes(String english1, String english2){
        if(isEmpty()){
            System.out.println("The dictionary is empty!");
            return;
        }
        
        printPathBetweenNodes(root, new Word(english1), new Word(english2));

    }
    
    
    //Print out the path between two nodes in bbst
    public static void printPathBetweenNodes(Node root, Word w1, Word w2){
        //arraylist to store path of w1
        ArrayList<Word> path1 = new ArrayList<>();
        //arraylist to store path of w2
        ArrayList<Word> path2 = new ArrayList<>();
        
        //getpath for two array
        getPath(root, path1, w1);
        getPath(root, path2, w2);
        
        //check the node is exist!
        if(path1.isEmpty() && path2.isEmpty()){
            System.out.println("Both word " + w1.getWord() + " and " + w2.getWord()
                    + "is not existed in the dictionary !");
            return;
        }
        if(path1.isEmpty()){
            System.out.println("The word " + w1.getWord() + " is not existed in the dictionary!");
            return;
        }else if(path2.isEmpty()){
            System.out.println("The word " + w2.getWord() + " is not existed in the dictionary!");
            return;
        }
        
        int intersection = -1;
        //Get intersection point
        int i = 0, j = 0;
        //keep moving ultil there is no intersection point
        while(i != path1.size() || j != path2.size()){
            //check value for two array equals or not
            if(i == j || path1.get(i).compareTo(path2.get(j)) == 0){
                i++;
                j++;
            }
            else{
                intersection = j - 1;
                break;
            }
        }
        
        //print the required path
        //traverse from reverse path1 -> unreverse path2
        for (i = path1.size() - 1; i > intersection; i--) {
            System.out.print(path1.get(i).getWord() + " ");
        }
        
        for (j = 0; j < intersection; j++) {
            System.out.print(path2.get(j).getWord() + " ");
        }
    }
//>>>>>>> Stashed changes
}
