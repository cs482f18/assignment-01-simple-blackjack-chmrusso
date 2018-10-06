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

    /**This method subtracts from the Dealer's total moves left while also counting the total points the computer has collected within the game.
     *
     * @param points An int representing the amount of points from the Card just drawn.
     */
    public void hitMe(int points){
        super.movesLeft = super.movesLeft -1;
        addTotalPoints(points);
    }

    /**totalPoints setter method
     *
     * @param points the amount of points that is setting the Dealer's totalPoints to.
     */
    public void setTotalPoints(int points){
        super.totalPoints = points;
    }

    /** This method continuously adds and manages the Dealer's total points collected during the game.
     *
     * @param points the amount of point the computer has collected during that turn
     */
    public void addTotalPoints(int points){
        super.totalPoints = super.totalPoints + points;
    }

    /**totalPoints getter method
     *
     * @return the total points collected by the Dealer during the game so far.
     */
    public int getTotalPoints(){
        return super.totalPoints;
    }

    /**hitChoice setter method.
     *
     * @param choice the hit (true) or stand (false) choice which determines the Dealer's current state.
     */
    public void setHitChoice(boolean choice){
        super.hitChoice = choice;
    }

    /**movesLeft getter method
     *
     * @return the amount of legal moves left for the Dealer so far.
     */
    public int getMovesLeft(){
        return super.movesLeft;
    }

    /**movesLeft setter method
     *
     * @param left the new amount of moves that the Dealer has left.
     */
    public void setMovesLeft(int left){
        super.movesLeft = left;
    }
}
