package wheeloffortune;

import java.util.Scanner;

/**
 * Presents a menu to the player that allows inputting an integer
 */
public class IntMenu {
    private final String message;
    private final int minValue;
    private final int maxValue;
    
    /**
     *
     * @param msg The message to display before checking for input
     * @param min The minimum allowed integer from the user
     * @param max The maximum allowed integer from the user
     */
    public IntMenu(String msg, int min, int max) {
        message = msg;
        minValue = min;
        maxValue = max;
    }

    /**
     * Displays the menu's message
     * @return The player's given integer
     */
    public int display() {
        // Print the menu's message and min/max values
        System.out.println(message + " [" + minValue + "-" + maxValue + "]");
        
        // Detect valid input
        int output = 0;
        Scanner in = new Scanner(System.in);
        // Loop until we get a valid input
        while (true) {
            if (in.hasNextInt()) { // We want only integers
                int input = in.nextInt();
                if (input >= minValue && input <= maxValue){ // It has to be within range
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
