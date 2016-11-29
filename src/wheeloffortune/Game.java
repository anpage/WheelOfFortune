package wheeloffortune;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class which contains all of the game's logic and flow control.
 * It's basically the glue that holds the rest of the objects together.
 */
public class Game {
    // Enum values representing possible game types
    public static final int STANDARD_GAME = 1;
    public static final int HALLOWEEN_GAME = 2;
    
    // Enum values representing possible in-game choices
    public static final int SPIN_WHEEL = 1;
    public static final int SOLVE_PUZZLE = 2;
    
    // The exit option is usually the third one
    public final int EXIT = 3;
    
    private List<Player> players;
    private Wheel wheel;
    private PuzzleReader puzzles;
    private int numRounds;
    private int gameType;
    
    /**
     * This starts the game itself.
     */
    public void run() {
        // Start an infinite loop. This makes sure the game starts over.
        // When the game needs to exit, the function will be returned.
        while (true) {
            // Store the current players in an ArrayList
            players = new ArrayList<>();
            
            // Ask for the number of players and store the result
            IntMenu playersMenu = new IntMenu("\nHow many players will be playing tonight?", 2, 100);
            int numPlayers = playersMenu.display();
            
            // Ask for each of the player's names and initialize a new Player object for each
            System.out.println("\nGreat! What are their names?");
            for (int i = 0; i < numPlayers; i++) {
                StringMenu newPlayer = new StringMenu("Player " + (i+1) + "'s name:");
                // Add each player to the players ArrayList
                players.add(new Player(newPlayer.display()));
            }
            
            // Ask for the number of rounds and store the result in an instance variable
            IntMenu roundsMenu = new IntMenu("\nHow many rounds would you like to play?", 1, 100);
            numRounds = roundsMenu.display();
            
            // Ask for the game type
            ChoiceMenu typeMenu = new ChoiceMenu("\nWhat type of game would you like to play?",
                                   new String[] {"Standard",
                                                 "Halloween",
                                                 "Exit"});
            gameType = typeMenu.display();
            // This switch uses the enum values defined at the top
            // We create a new wheel of the appropriate type and load in the puzzle file
            switch (gameType) {
                case STANDARD_GAME:
                    wheel = new StandardWheel();
                    puzzles = new PuzzleReader("src/WOFStandard.txt");
                    break;
                case HALLOWEEN_GAME:
                    wheel = new HWeenWheel();
                    puzzles = new PuzzleReader("src/WOFHalloween.txt");
                    break;
                case EXIT:
                    return;
            }
            
            // This is where the game itself happens
            System.out.println("\nAlright, let's get started! Here we go!");
            
            // First, we loop through each round
            for (int currRound = 1; currRound <= numRounds; currRound++) {
                // Each round has its own puzzle
                Puzzle currPuzzle = puzzles.getNewPuzzle();
                // roundOver is used to keep track of whether or not to move on
                boolean roundOver = false;
                // Loop through each player, but don't increment yet
                for (int currPlayer = 0; currPlayer < players.size();) {
                    // Store information about the player in convenient variables
                    Player player = players.get(currPlayer);
                    String name = player.getName();
                    
                    // Print the current player, the category, and the puzzle
                    System.out.println("\nOkay " + name + ", it's your turn.");
                    System.out.println("The category is \"" + currPuzzle.getCategory() + "\"");
                    System.out.println("\n" + currPuzzle.getState() + "\n");
                    
                    // Present the player with the game's primary menu
                    ChoiceMenu gameMenu = new ChoiceMenu("What would you like to do?",
                    new String[] {"Spin the Wheel",
                                  "Solve the Puzzle",
                                  "Exit"});
                    int playerChoice = gameMenu.display();
                    switch (playerChoice) {
                        case SPIN_WHEEL:
                            // We get a random spin value from the Wheel object
                            int spin = wheel.spin();
                            switch (spin) {
                                case -1: // Bankrupt
                                    System.out.println("Really bad luck! You went bankrupt!");
                                    player.bankrupt();
                                    currPlayer++; // Move on to the next player
                                    break;
                                case 0: // Lose a turn
                                    System.out.println("Bad luck! You lost a turn.");
                                    currPlayer++; // Move on to the next player
                                    break;
                                default: // Cash!
                                    System.out.println("You spun $" + spin + "!");
                                    // Give the player an opportunity to guess a letter
                                    GuessMenu letterMenu = new GuessMenu("Enter a letter:");
                                    int guess = currPuzzle.makeGuess(letterMenu.display());
                                    switch (guess) {
                                        case -1: // Guessed before
                                            System.out.println("This has already been guessed! Lose a turn.");
                                            currPlayer++;
                                            break;
                                        case 0: // No matches
                                            System.out.println("Sorry, this letter is not in the puzzle.");
                                            currPlayer++;
                                            break;
                                        case 1: // Only one match
                                            System.out.println("There is 1 of those in the puzzle!");
                                            player.giveCash(spin);
                                            // Print the round scores
                                            System.out.println("\nRound scores: ");
                                            for (Player p : players) {
                                                System.out.println(p.getName() + "'s cash: $" + p.getRoundCash());
                                            }
                                            break;
                                        default: // Multiple matches
                                            System.out.println("There are " + guess + " of those in the puzzle!");
                                            player.giveCash(spin * guess);
                                            // Print the round scores
                                            System.out.println("\nRound scores: ");
                                            for (Player p : players) {
                                                System.out.println(p.getName() + "'s cash: $" + p.getRoundCash());
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case SOLVE_PUZZLE:
                            // Ask the player for their proposed solution and check it against the puzzle
                            StringMenu solveMenu = new StringMenu("Please enter the solution:");
                            if (currPuzzle.solvePuzzle(solveMenu.display())) {
                                System.out.println("Correct! You win this round, " + name + "!");
                                // Move the player's round cash to their game cash and end the round
                                player.newRound();
                                roundOver = true;
                                currPlayer = numPlayers;
                            }
                            break;
                        case EXIT:
                            return;
                    }
                    
                    // If the round isn't over and we've run out of players, go back to the first
                    if (currPlayer >= numPlayers && !roundOver) {
                        currPlayer = 0;
                    }
                }
                
                System.out.println("\nThe round is over!");
                
                // Move the first player to the end
                // This is inefficient, but not so bad in this case
                players.add(players.remove(0));
                
                // Print the final game scores and determine the winner
                System.out.println("\nGame scores: ");
                Player winner = null;
                int lastCash = 0;
                for (Player p : players) {
                    int cash = p.getGameCash();
                    p.bankrupt();
                    System.out.println(p.getName() + "'s cash: $" + cash);
                    if (cash > lastCash) {
                        winner = p;
                    }
                    lastCash = cash;
                }
                
                if (currRound < numRounds) {
                    // If we're not out of rounds, keep going
                    System.out.println("\nStarting a new round...");
                } else {
                    // The game is over
                    if (winner != null) {
                        System.out.println("\nGame over! " + winner.getName() + " wins!");
                    } else {
                        System.out.println("\nGame over! Wow, nobody won. Huh.");
                    }
                    
                    // Ask if the player would like to play again
                    ChoiceMenu replayMenu = new ChoiceMenu("\nWould you like to play again?",
                                             new String[] {"Yes", "No"});
                    if (replayMenu.display() == 2) {
                        // If not, write out the results and return from the game's run function
                        ResultsWriter.writeResults(gameType, numRounds, players, winner);
                        return;
                    }
                }
            }
        }
    }
    
}
