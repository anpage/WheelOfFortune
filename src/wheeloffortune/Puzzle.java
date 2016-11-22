/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheeloffortune;

/**
 *
 * @author Alex
 */
public class Puzzle {
    private int number;
    private String category;
    private String solution;
    private String guessed;

    public Puzzle() { // TODO: Implement real data
        number = 0;
        category = "Animals";
        solution = "Kitty Cats";
        guessed = " ";
    }

    public int makeGuess(char ch) {
        // Convert to lowercase string to make case insensitve
        String guess = Character.toString(Character.toLowerCase(ch));

        // Append to guessed string if new guess,
        // otherwise return -1 to indicate existing guess
        if (guessed.indexOf(guess) == -1){
            guessed += guess;
        } else {
            return -1;
        }

        // Return the number of occurences in the solution
        return solution.length() - solution.replaceAll(guess, "").length();
    }

    public String getState() {
        // Build a new string, replacing non-guessed chars with '*'
        String obscuredSolution = "";

        for (int i = 0; i < solution.length(); i++) {
            char ch = solution.charAt(i);
            if (guessed.indexOf(Character.toLowerCase(ch)) == -1) {
                obscuredSolution += '*';
            } else {
                obscuredSolution += ch;
            }
        }

        return obscuredSolution;
    }

    public boolean solvePuzzle(String input) {
        return input.equalsIgnoreCase(solution);
    }

    public String getCategory() {
        return category;
    }
}
