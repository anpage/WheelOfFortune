/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheeloffortune;

import java.util.Scanner;

/**
 *
 * @author Valter
 */
public class ChoiceMenu {
    private final String message;
    private final String[] options;
    
    public ChoiceMenu(String msg, String[] opts) {
        message = msg;
        options = opts;
    }
    
    public int display() {
        // Print the menu's message and choices
        System.out.println(message);
        for (int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ": " + options[i]);
        }
        
        // Detect valid choice
        int choice = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                int input = in.nextInt();
                if (input > 0 && input <= options.length){
                    choice = input;
                    break;
                }
            }
            System.out.println("That's not a valid choice. Please try again.");
            in.nextLine();
        }
        return choice;
    }
}
