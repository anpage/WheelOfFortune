package wheeloffortune;

import java.util.Scanner;

/**
 * Presents a menu to the player that allows inputting a string
 */
public class StringMenu {
    private final String message;

    /**
     *
     * @param msg The message to display before checking for input
     */
    public StringMenu(String msg) {
        message = msg;
    }
    
    /**
     * Displays the menu's message
     * @return The player's given string
     */
    public String display() {
        // Print the menu's message
        System.out.println(message);
        
        // Get user input
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
