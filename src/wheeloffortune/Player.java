package wheeloffortune;

/**
 * Represents one of the game's players
 */
public class Player {
    private final String name;
    private int gameCash;
    private int roundCash;

    /**
     * Creates a new player
     * @param newName The player's name
     */
    public Player(String newName) {
        name = newName;
    }

    /**
     *
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Dumps the player's round cash into their game cash and zeroes out the former
     */
    public void newRound() {
        gameCash += roundCash;
        roundCash = 0;
    }

    /**
     * Zeroes out the player's round cash
     */
    public void bankrupt() {
        roundCash = 0;
    }

    /**
     * Gives the player round cash
     * @param cash The amount of cash to give the player
     */
    public void giveCash(int cash) {
        roundCash += cash;
    }

    /**
     *
     * @return the player's game cash
     */
    public int getGameCash() {
        return gameCash;
    }

    /**
     *
     * @return the player's round cash
     */
    public int getRoundCash() {
        return roundCash;
    }
}
