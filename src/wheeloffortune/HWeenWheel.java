package wheeloffortune;

import java.util.Random;

/**
 * Provides the data and logic specific to the Halloween wheel
 */
public class HWeenWheel implements Wheel {
    private final int numSlots;
    private final int[] slots;

    private final Random rand = new Random();

    /**
     * Initialize the wheel's data according to the Halloween wheel specification
     */
    public HWeenWheel() {
        numSlots = 25;
        int numLoseTurn = 3;
        int numBankrupt = 3;
        int baseValue = 500;
        int[] possibleModifiers = {50, 100, 200, 300, 400, 600, 800};

        // Hold all possible slots to land on
        slots = new int[numSlots];

        // Assign bankrupt slots
        // Any before remain 0 to represent lose turn slots
        for (int i = numLoseTurn; i < numLoseTurn + numBankrupt; i++) {
            slots[i] = -1;
        }

        // The rest are random
        for (int i = numLoseTurn + numBankrupt; i < numSlots; i++) {
            slots[i] = baseValue + possibleModifiers[rand.nextInt(possibleModifiers.length)];
        }
    }

    /**
     * Spins the wheel
     * A slot with -1 represents a bankrupt slot
     * A slot with 0 represents a "lose a turn" slot
     * Anything else is a cash value
     * @return A random slot on the wheel
     */
    @Override
    public int spin() {
        return slots[rand.nextInt(numSlots)];
    }
}
