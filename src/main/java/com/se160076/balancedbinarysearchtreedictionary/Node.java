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
public class Node<Word> {

    public Word data;
    public Node left;
    public Node right;
    //add Node parent
    public Node parent;

    public Node() {
    }

    public Node(Word data) {
        super();
        this.data = data;
    }

    public Word getData() {
        return data;
    }

    public void setData(Word data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    
    
    public Node getNode(Word data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.left = newNode.right = newNode.parent = null;
        return newNode;
    }

}
