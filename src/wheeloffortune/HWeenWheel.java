/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheeloffortune;

import java.util.Random;

/**
 *
 * @author Alex
 */
public class HWeenWheel implements Wheel {
    private final int numSlots;
    private final int[] slots;

    private final Random rand = new Random();

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

    @Override
    public int getRandomSlot() {
        return slots[rand.nextInt(numSlots)];
    }
}
