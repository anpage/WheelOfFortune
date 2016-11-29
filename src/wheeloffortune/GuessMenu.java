package wheeloffortune;

import java.util.Scanner;

/**
 * Presents a menu to the player that allows guessing for letters
 */
public class GuessMenu {
    private final String message;
    
    /**
     *
     * @param msg The message to display before checking for input
     */
    public GuessMenu(String msg) {
        message = msg;
    }
    
    /**
     * Displays the menu's message
     * @return The player's guessed letter
     */
    public char display() {
        // Print the menu's message
        System.out.println(message);
        
        // Get user input
        char output;
        Scanner in = new Scanner(System.in);
        // Loop until we get a valid input
        while (true) {
            if (!in.hasNextInt()) { // We don't want numbers
                String input = in.nextLine();
                if (input.length() == 1) { // We only want one character
                    output = input.toCharArray()[0];
                    break;
                }
            }
            System.out.println("That's not a valid letter. Please try again.");
            in.nextLine();
        }
        return output;
    }
}
