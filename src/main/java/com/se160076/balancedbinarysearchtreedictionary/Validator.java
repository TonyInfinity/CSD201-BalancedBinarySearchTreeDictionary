/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tony
 */
public class Validator {

    private final static Scanner sc = new Scanner(System.in);

    public static int validateMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < min || choice > max) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("\nChoice Must Be An Integer.");
                System.out.println("Please Enter Again\n");
                System.out.printf("Enter [%s, %s]: \n", min, max);
            }
        }
    }

    public static String validateString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty() || result.length() == 0) {
                System.out.println("\nInput Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String validateWord() {
        while (true) {
            String result = validateString();
            Pattern pattern = Pattern.compile("[a-zA-Z ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nId Must Be Alphabetical.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static boolean validateWord(String result) {
        while (true) {
            Pattern pattern = Pattern.compile("[a-zA-Z ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean checkInputYN() {
        while (true) {
            String result = validateString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("\nInput Must Be Either Y/y Or N/n.");
                System.out.println("Enter: \n");
            }
        }
    }

    public static String validateFileName(String fileName) {
        while (true) {
            String result = fileName.trim();
            Pattern pattern = Pattern.compile("^.*\\.(txt|TXT)$");
            Matcher matcher = pattern.matcher(result);
            if (result.isEmpty()) {
                System.out.println("\nFile Name Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                if (!matcher.matches()) {
                    System.out.println("\nFile Name Must End With .txt.");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            }
        }
    }

    public static boolean wordExists(ArrayList<Word> wl, String id) {
        for (Word i : wl) {
            if (i.getWord().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
