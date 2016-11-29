package wheeloffortune;

import java.util.Scanner;

/**
 * Presents a menu to the player with a list of possible choices
 */
public class ChoiceMenu {
    private final String message;
    private final String[] options;
    
    /**
     *
     * @param msg The message to display before checking for input
     * @param opts The list of possible choices
     */
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
        // Loop until we get a valid input
        while (true) {
            if (in.hasNextInt()) { // We want only integers
                int input = in.nextInt();
                if (input > 0 && input <= options.length){ // It has to be one of the choices
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
