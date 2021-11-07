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
public class Main {

    public static void main(String args[]) {
        ArrayList<Word> wl = new ArrayList<>();

        while (true) {
            Manager.printMenu();
            int choice = Validator.validateMenuChoice(1, 6);

            switch (choice) {
                case 1:
                    Manager.loadAndBuildBBST(wl);
                    break;

                case 2:
                    Manager.addWord(wl);
                    break;

                case 3:
                    Manager.deleteWord(wl);
                    break;

                case 4:
                    Manager.search();
                    break;

                case 5:
                    Manager.printPathbetweenTwoNodes();
                    break;

                case 6:
                    System.exit(0);
                    break;
            }
        }
    }
}
