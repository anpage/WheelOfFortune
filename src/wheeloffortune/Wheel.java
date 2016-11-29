package wheeloffortune;

/**
 * Provides the (very simple) public interface for both types of wheels
 */
public interface Wheel {

    /**
     * Spins the wheel
     * A slot with -1 represents a bankrupt slot
     * A slot with 0 represents a "lose a turn" slot
     * Anything else is a cash value
     * @return A random slot on the wheel
     */
    int spin();
}
