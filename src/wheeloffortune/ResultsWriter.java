package wheeloffortune;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes out the results of the game to a text file
 */
public class ResultsWriter {
    /**
     *
     * @param type The game's type (according to the enum values)
     * @param numRounds The number of rounds played
     * @param players The array of players who took part
     * @param winner The winner of the game
     */
    public static void writeResults(int type, int numRounds, List<Player> players, Player winner) {
        File outFile = new File("src/WOFResults.txt");
        FileWriter writer;
        
        try {
            writer = new FileWriter(outFile, false);
            
            writer.write("Game ");
            switch (type) {
                case Game.STANDARD_GAME:
                    writer.write("Standard");
                    break;
                case Game.HALLOWEEN_GAME:
                    writer.write("Halloween");
                    break;
            }
            writer.write(" with " + numRounds + " rounds\n\n");
            
            writer.write("Players");
            for (int i = 0; i < players.size(); i++) {
                writer.write("\nPlayer" + (i+1) + " = " + players.get(i).getName());
            }
            writer.write("\n");
            
            for (Player p : players) {
                writer.write("\nPlayer " + p.getName() + " winnings = $" + p.getGameCash());
            }
            
            writer.write("\n\nGame winner = " + winner.getName());
            
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
