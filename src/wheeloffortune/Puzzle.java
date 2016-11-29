package wheeloffortune;

/**
 * Represents a puzzle and the guesses made against it
 */
public class Puzzle {
    private final int number;
    private final String category;
    private final String solution;
    private String guessed;

    /**
     * Creates a new puzzle
     * @param num The puzzle's index within the category
     * @param cat The puzzle's category
     * @param sol The puzzle's solution
     */
    public Puzzle(int num, String cat, String sol) {
        number = num;
        category = cat;
        solution = sol;
        // Store the guessed letters in a string
        // Initialized with a space so that spaces are always guessed.
        guessed = " ";
    }

    /**
     * Make a guess against the string
     * -1 means that the guess has been made before
     * 0 means that the letter doesn't exist in the solution
     * @param ch The character to guess
     * @return The number of times ch appears in the solution
     */
    public int makeGuess(char ch) {
        // Convert to lowercase string to make case insensitve
        String guess = Character.toString(Character.toLowerCase(ch));

        // Append to guessed string if new guess,
        // otherwise return -1 to indicate existing guess
        if (!guessed.contains(guess)){
            guessed += guess;
        } else {
            return -1;
        }

        // Return the number of occurences in the solution
        return solution.length() - solution.toLowerCase().replaceAll(guess, "").length();
    }

    /**
     * Gets the solution, but obscured with asterisks where unguessed letters are
     * @return The asterisk-obscured solution
     */
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

    /**
     * Checks a case-insensitive attempt to solve the puzzle
     * @param input The proposed solution
     * @return Whether or not the solution matches
     */
    public boolean solvePuzzle(String input) {
        return input.equalsIgnoreCase(solution);
    }

    /**
     *
     * @return The puzzle's category
     */
    public String getCategory() {
        return category;
    }
}
