package com.example.christina.simpleblackjack;

/** An Object class to represent the User of the BlackJack game.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class User extends Player{

    /** The Default constructor sets the max amount of moves possible and sets his or her initial choice for hitting to be true.
     */
    public User(){
        super.movesLeft=5;
        super.hitChoice=true;
    }

    /**This method sets the User's total moves left to 0 while also setting its choice for hitting to a stop, thus telling the BlackJack game that her or she has decided to stand.
     *
     */
    public void stopMe(){
        super.movesLeft =0;
        super.hitChoice = false;
    }


}
