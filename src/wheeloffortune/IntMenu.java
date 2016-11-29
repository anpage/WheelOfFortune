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
public class IntMenu {
    private final String message;
    private final int minValue;
    private final int maxValue;
    
    public IntMenu(String msg, int min, int max) {
        message = msg;
        minValue = min;
        maxValue = max;
    }
    
    public int display() {
        // Print the menu's message
        System.out.println(message);
        
        // Detect valid input
        int output = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                int input = in.nextInt();
                if (input >= minValue && input <= maxValue){
                    output = input;
                    break;
                }
            }
            System.out.println("That's not a valid choice. Please try again.");
            in.nextLine();
        }
        return output;
    }
}
