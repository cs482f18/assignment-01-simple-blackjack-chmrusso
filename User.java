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

    /**This method subtracts from the User's total moves left while also counting the total points he or she has collected within the game.
     *
     * @param points An int representing the amount of points from the Card just drawn.
     */
    public void hitMe(int points){
        super.movesLeft = super.movesLeft -1;
        addTotalPoints(points);
    }

    /**This method sets the User's total moves left to 0 while also setting its choice for hitting to a stop, thus telling the BlackJack game that her or she has decided to stand.
     *
     */
    public void stopMe(){
        super.movesLeft =0;
        super.hitChoice = false;
    }

    /**totalPoints setter method
     *
     * @param points the amount of points that is setting the User's totalPoints to.
     */
    public void setTotalPoints(int points){
        super.totalPoints = points;
    }

    /** This method continuously adds and manages the User's total points collected during the game.
     *
     * @param points the amount of point they have collected during that turn
     */
    public void addTotalPoints(int points){
        super.totalPoints = super.totalPoints + points;
    }

    /**totalPoints getter method
     *
     * @return the total points collected by the User during the game so far.
     */
    public int getTotalPoints(){
        return super.totalPoints;
    }

    /**hitChoice setter method.
     *
     * @param choice the hit (true) or stand (false) choice which determines the User's current state.
     */
    public void setHitChoice(boolean choice){
        super.hitChoice = choice;
    }

    /**movesLeft getter method
     *
     * @return the amount of legal moves left for the User so far.
     */
    public int getMovesLeft(){
        return super.movesLeft;
    }

    /**movesLeft setter method
     *
     * @param left the new amount of moves that the User has left.
     */
    public void setMovesLeft(int left){
        super.movesLeft = left;
    }
}
