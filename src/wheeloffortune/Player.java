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
public class Player {
    String name;
    int gameCash;
    int roundCash;

    public Player(String newName) {
        name = newName;
    }

    public void newRound() {
        gameCash += roundCash;
        roundCash = 0;
    }

    public void bankrupt() {
        roundCash = 0;
    }

    public void giveCash(int cash) {
        roundCash += cash;
    }

    public int getGameCash() {
        return gameCash;
    }

    public int getRoundCash() {
        return roundCash;
    }
}
