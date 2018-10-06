package com.example.christina.simpleblackjack;
import java.util.Stack;

/** An Object class to represent a Deck within the BlackJack game. Here, it organizes and manages the Card Objects.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class Deck {

    /** A counter for the current number of Decks being used. The Default value of Decks being used is 1.*/
    private int numOfDecks;
    /** A set value of how many Cards should be in each Deck.*/
    private final int CARDPERDECK = 52;
    /** An ordered Array, like with most new packs of Cards, that has each Card in acceding order, as in the first 4 Cards would be "♣2","♦2","♠2","♥2" .*/
    private Card[] newPack;
    /** This Ctack represents a shuffled set of Cards.*/
    private Stack<Card> shuffledDeck;

    /** This constructor both creates a new Deck of Cards in order then shuffles the newly made Cards into a Stack of Cards.
     *
     * @param numberOfDecks A Deck takes in a number of Decks that will be played within the game. The Default value of Deck being used is 1.
     */
    public Deck(int numberOfDecks){

        numOfDecks = numberOfDecks;
        newPack= new Card[numOfDecks*CARDPERDECK];


        String suits[] = {"♣","♦","♠","♥"};
        String face[] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        //populate the deck
        int packIndex = 0;
        for(int x = numberOfDecks; x > 0; x--){
            for(int y = suits.length -1; y >= 0; y--){
                for (int z = face.length-1; z >=0; z--){

                    //set point value
                    int pointValue;
                    if(face[z].equals("J")||face[z].equals("Q")||face[z].equals("K")){
                        pointValue = 10;
                    }else if (face[z].equals("A")){
                        pointValue = 11;
                    }
                    else {
                        pointValue = Integer.parseInt(face[z]);
                    }

                    //set cardName
                    String cardName = suits[y] + face[z];

                    //make the card
                    Card tempcard = new Card( pointValue, cardName);
                    newPack[packIndex]=tempcard;
                    packIndex++;

                }
            }
        }

        //Shuffle the cards
        shuffledDeck = new Stack();
        for(int x = newPack.length-1; x >=0; x--){

            //randomly pick a card
            int randomCardIndex = (int)(Math.random() * ((numOfDecks*CARDPERDECK)));

            //dont add the same card twice
            while(newPack[randomCardIndex]== null){
                randomCardIndex = (int)(Math.random() * ((numOfDecks*CARDPERDECK)));
            }

            shuffledDeck.add(newPack[randomCardIndex]);
            newPack[randomCardIndex] = null;
        }
    }

    /** This method represent drawing a single Card from the Deck and placing it into the game.
     *
     * @return the drawn Card from the Stack of shuffled Cards.
     */
    public Card drawOne(){
        return shuffledDeck.pop();
    }
}
