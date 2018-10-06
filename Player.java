package com.example.christina.simpleblackjack;
/** An Abstract class used to dictact what each instance of a Player requires to function.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public abstract class Player {

    /**Each Player will have a total number of points they have collected each turn.*/
    protected int totalPoints;
    /**Each Player will have their own decision on whether or not they have finish drawing Cards and are ready to stand.*/
    protected boolean hitChoice;
    /**Each Player will have a set number of moves they are able to take. It is set within the game that they are not allowed to exceed a total number of 5 cards on the playing field.*/
    protected int movesLeft;

    /**This method will define how each Player will add to their hand during the BlackJack game.
     *
     * @param points An int representing the amount of points from the Card just drawn.
     */
    public abstract void hitMe(int points);

}
