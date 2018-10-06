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
    public void hitMe(int points){
        this.movesLeft = this.movesLeft -1;
        addTotalPoints(points);
    }

    /**totalPoints setter method
     *
     * @param points the amount of points that is setting the Player's totalPoints to.
     */
    public void setTotalPoints(int points){
        this.totalPoints = points;
    }

    /** This method continuously adds and manages the Player's total points collected during the game.
     *
     * @param points the amount of point they have collected during that turn
     */
    public void addTotalPoints(int points){
        this.totalPoints = this.totalPoints + points;
    }

    /**totalPoints getter method
     *
     * @return the total points collected by the Player during the game so far.
     */
    public int getTotalPoints(){
        return this.totalPoints;
    }

    /**hitChoice setter method.
     *
     * @param choice the hit (true) or stand (false) choice which determines the Player's current state.
     */
    public void setHitChoice(boolean choice){
        this.hitChoice = choice;
    }

    /**movesLeft getter method
     *
     * @return the amount of legal moves left for the Player so far.
     */
    public int getMovesLeft(){
        return this.movesLeft;
    }

    /**movesLeft setter method
     *
     * @param left the new amount of moves that the Player has left.
     */
    public void setMovesLeft(int left){
        this.movesLeft = left;
    }

}
