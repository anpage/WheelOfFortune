package wheeloffortune;

/**
 * This is the main class of the program and it only serves to initialize and
 * run the game loop.
 */
public class WheelOfFortune {

    /**
     * This is the main method.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Wheel of Fortune!");
        
        // Create a new instance of the game and run it
        Game mainGame = new Game();
        mainGame.run();
        
        System.out.println("\nThanks for playing! Goodbye!");
    }
}
