/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

import java.util.Scanner;

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
            if (result.isEmpty() || result.isBlank()) {
                System.out.println("\nInput Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                return result;
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
}
