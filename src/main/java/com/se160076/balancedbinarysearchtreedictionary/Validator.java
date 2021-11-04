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
            if (result.isEmpty() || result.length() == 0) {
                System.out.println("\nInput Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }
}
