/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author Tony
 */
public class Manager {

    private static Node<Word> root;
    private static final String fileName = "dictionary.txt";

    public final static void printMenu() {
        System.out.println("\n|----------------------------------- BALANCED BST DICTIONARY -----------------------------------|");
        System.out.println("| 1. Load And Build BBST.                                                                       |");
        System.out.println("| 2. Add A Word.                                                                                |");
        System.out.println("| 3. Delete A Word.                                                                             |");
        System.out.println("| 4. Search For A Word.                                                                         |");
        System.out.println("| 5. Print Path Between 2 Nodes.                                                                |");
        System.out.println("| 6. Quit.                                                                                      |");
        System.out.println("|-----------------------------------------------------------------------------------------------|\n");
    }

    public final static void loadAndBuildBBST(ArrayList<Word> wl) {
        readFile(wl);
        Collections.sort(wl, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.compareTo(w2);
            }
        });
        Word[] wa = new Word[wl.size()];
        wa = wl.toArray(wa);
        root = BBST.balance(wa);
        BBST.inorderTraversal(root);
    }

    public final static void addWord(ArrayList<Word> wl) {
        String word;
        String translation;
        System.out.println("Enter Word To Add: ");
        word = Validator.validateWord();
        System.out.println("Enter Its Translation: ");
        translation = Validator.validateString();
        root = BBST.insert(root, new Word(word, translation));
        BBST.inorderTraversal(root);
    }

    public static void deleteWord(ArrayList<Word> wl) {
        String word;
        System.out.println("Enter Word To Delete: ");
        word = Validator.validateWord();
        root = BBST.deleteNode(root, new Word(word));
        BBST.inorderTraversal(root);
    }

    public static void search() {
        String translation;
        //input the word need to search
        System.out.println("Enter Word To Search: ");
        String inputWord;
        inputWord = Validator.validateWord();
        //find the word
        Node<Word> tmp = BBST.search(inputWord, root);
        //check if the word exists
        if (tmp == null) {
            System.out.println("This Word Does Not Exist.");
            return;
        }
        //show for user
        translation = tmp.getData().getTranslation();
        System.out.println(inputWord + ": " + translation);
    }

    public static void printPathbetweenTwoNodes() {
        System.out.println("Enter First Word: ");
        String firstWord = Validator.validateWord();
        System.out.println("Enter Second Word: ");
        String secondWord = Validator.validateWord();

        BBST.printPathBetweenNodes(firstWord, secondWord, root);
    }

    public final static void readFile(ArrayList<Word> wl) {
        String file = Validator.validateFileName(fileName);
        Path path = Paths.get(file);
        try ( BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                String word = st.nextToken().trim();
                String translation = st.nextToken().trim();
                if (Validator.validateWord(word)) {
                    wl.add(new Word(word, translation));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(ArrayList<Word> wl) {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);
            for (Word w : wl) {
                pw.println(w.toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
    }
}
