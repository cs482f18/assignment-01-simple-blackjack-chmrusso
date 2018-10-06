package com.example.christina.simpleblackjack;
/** An Object class to represent each Card within the BlackJack game
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class Card {

    /** Each card has a pointValue ranging from 2-11*/
    private int pointValue;
    /** Each card has a cardName with a suit ranging from ♣,♦,♠,♥ and a name ranging from 2-10 or J,K,Q,A. An example of a cardName would be "♦4" */
    private String cardName;

    /** The parametrized Constructor creates the card given its pointValue and cardName
     * @param value the point value of a Card
     * @param name the suit and name of a Card
     */
    public Card(int value, String name){
        pointValue = value;
        cardName = name;
    }

    /** cardName getter method
     *
     * @return a String of both the suit and card name of the given card.
     */
    public String getCardName(){
        return cardName;
    }

    /** pointValue getter method
     *
     * @return an integer of the point value of the given card
     */
    public int getPointValue() {
        return pointValue;
    }

    /** pointValue setter method
     *
     * @param points sets the pointValue of a Card to this integer
     */
    public void setPointValue(int points) {
        pointValue = points;
    }
}
