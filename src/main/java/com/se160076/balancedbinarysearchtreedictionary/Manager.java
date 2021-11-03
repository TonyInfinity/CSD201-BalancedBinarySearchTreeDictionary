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

    private static final String fileName = "dictionary.txt";

    public final static void printMenu() {
        System.out.println("\n|------------------------------------- VACCINE MANAGEMENT -------------------------------------|");
        System.out.println("| 1. Load And Build BBST.                                                                      |");
        System.out.println("| 2. Add A Word.                                                                               |");
        System.out.println("| 3. Delete A Word.                                                                            |");
        System.out.println("| 4. Search For A Word.                                                                        |");
        System.out.println("| 5. Print Path Between 2 Nodes.                                                               |");
        System.out.println("| 6. Quit.                                                                                     |");
        System.out.println("|----------------------------------------------------------------------------------------------|\n");
    }

    public final static void loadAndBuildBBST(ArrayList<Word> wl) {
        readFile(wl);
        Collections.sort(wl, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getWord().compareTo(w2.getWord());
            }
        });
        Word[] w = new Word[wl.size()];
        w = wl.toArray(w);
        Node r = BBST.createBBST(w);
        BBST.inorderTraversal(r);
    }

    public final static void addWord(ArrayList<Word> wl) {
        String word;
        String translation;
        System.out.println("Enter Word To Add: ");
        word = Validator.validateString();
        System.out.println("Enter Its Translation: ");
        translation = Validator.validateString();
        
    }

    public final static void readFile(ArrayList<Word> wl) {
        Path path = Paths.get(fileName);
        try ( BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                String word = st.nextToken();
                String translation = st.nextToken();
                wl.add(new Word(word, translation));
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
