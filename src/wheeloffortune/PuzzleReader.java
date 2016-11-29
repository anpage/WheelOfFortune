package wheeloffortune;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads a given puzzle file and stores the information within
 */
public class PuzzleReader {
    private List<Puzzle> readPuzzles;
    private final Random rand;
    
    /**
     * Creates a new instance pointed at the given file and reads in the puzzles
     * @param fileName The path to the puzzle file to be read
     */
    public PuzzleReader(String fileName) {
        readPuzzles = new ArrayList<>();
        rand = new Random();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Initialize puzzle info in case the file isn't correct
            String category = "None";
            int puzzleNum = 1;
            String line;
            
            // Begin reading lines until EOF
            while ((line = br.readLine()) != null) {
                // We only care about lines with an equals sign
                if (line.contains("=")) {
                    // Split the line at the equals sign and trim whitespace
                    String before = line.split("=")[0].trim();
                    String after = line.split("=")[1].trim();
                    
                    // If the line defines a category, set the category and reset puzzle number
                    // Otherwise, add a new puzzle to the list and increment puzzle number
                    if (before.equalsIgnoreCase("category")) {
                        category = after;
                        puzzleNum = 1;
                    } else if (before.equalsIgnoreCase("puzzle")) {
                        readPuzzles.add(new Puzzle(puzzleNum, category, after));
                        puzzleNum++;
                    }
                }
            }
       } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    /**
     *
     * @return A random puzzle read from the file
     */
    public Puzzle getNewPuzzle() {
        // Generate a random index and return the puzzle there
        int index = rand.nextInt(readPuzzles.size());
        return readPuzzles.get(index);
    }
}
