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
public class StringMenu {
    private final String message;
    
    public StringMenu(String msg) {
        message = msg;
    }
    
    public String display() {
        // Print the menu's message
        System.out.println(message);
        
        // Get user input
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
