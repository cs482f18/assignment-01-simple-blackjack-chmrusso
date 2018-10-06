package com.example.christina.simpleblackjack;
/** An Object class to represent the User of the BlackJack game.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class Dealer extends Player {

    /**The Default constructor sets the max amount of moves possible and sets the Dealer's initial choice for hitting to be false.
     *
     */
    public Dealer(){
        super.movesLeft=5;
        super.hitChoice = false;
    }

}
