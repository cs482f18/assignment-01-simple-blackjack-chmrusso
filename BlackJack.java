package com.example.christina.simpleblackjack;

import android.util.Log;

import java.util.ArrayList;

/** A Model Class to manage the mechanics and logic within the BlackJack game.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class BlackJack {
    /**This is where the User's information about the game will be stored. */
    private User userPlayer;
    /**This is where the Dealer's information about the game will be stored. */
    private Dealer dealerPlayer;
    /**This object holds the shuffled Stack of Cards*/
    private Deck shuffledDeck;

    /**This is where the Cards within the User's hand is stored*/
    private ArrayList <Card> userHand;
    /**This is where the Cards within the Dealer's hand is stored */
    private ArrayList<Card> dealerHand;

    /**A bust is set value that both the User and Dealer cannot exceed in their total amount of points. Its set Value is 21.*/
    public final int BUST = 21;
    /**This keeps track of which player is currently labeled as the winner of the BlackJack game */
    private Player winner;
    /**This keeps track of which play is currently allowed to draw from the Deck */
    private Player whosTurn;
    /**This is the helper value which allows the first four cards be distributed at the beginning of the game */
    private int initialDeal;

    /**The Default constructor uses 1 Deck and let's the User move first*/
    public BlackJack(){

        shuffledDeck = new Deck(1);
        userPlayer = new User();
        dealerPlayer = new Dealer();

        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        whosTurn = userPlayer;
        initialDeal = 4;

    }

    /**This method is called in the beginning of the game to deal out the first four cards to each respective player.
     *
     * @return the cardName of the Card object drawn. (This is used to help the main Controller display information about the Model)
     */
    public String dealHand( ){
        Card tempcard = shuffledDeck.drawOne();
        String tempcardName = tempcard.getCardName();
        int tempcardPoints = tempcard.getPointValue();

        //Deal User's hand first
        if(initialDeal >2){
            userPlayer.setMovesLeft(userPlayer.getMovesLeft()-1);
            userPlayer.addTotalPoints(tempcardPoints);
            initialDeal--;
            userHand.add(tempcard);
        }
        //Deal Dealer's hand next
        else{
            dealerPlayer.setMovesLeft(dealerPlayer.getMovesLeft()-1);
            dealerPlayer.addTotalPoints(tempcardPoints);
            initialDeal--;
            dealerHand.add(tempcard);
        }
        return tempcardName;
    }


    /**This method is used each time the User or Dealer chooses to hit and draws a card from the Deck.
     *
     * @return the cardName of the card drawn. (This is used to help the main Controller display information about the Model)
     */
    public String hitPlayer(){
        Card tempcard = shuffledDeck.drawOne();
        String tempcardName = tempcard.getCardName();
        int tempcardPoints = tempcard.getPointValue();

        if(whosTurn == userPlayer){
            userPlayer.hitMe(tempcardPoints);
            userHand.add(tempcard);

            if(checkBust()){
                whosTurn = dealerPlayer;
                userPlayer.setMovesLeft(0);
                winner = dealerPlayer;
                endGame();
                return tempcardName;
            }
            else if(userPlayer.getMovesLeft() ==0){
                Log.w("Deck","Total Points=" + userPlayer.getTotalPoints());
                whosTurn = dealerPlayer;
                userPlayer.setHitChoice(false);
            }
        }else{
            dealerHand.add(tempcard);

            if(dealerPlayer.getTotalPoints() >= 17){
                dealerPlayer.setHitChoice(false);
                dealerPlayer.setMovesLeft(0);
                return "";
            }
            dealerPlayer.hitMe(tempcardPoints);
        }

        return tempcardName;
    }

    /**This method stops whichever player decides they are finished drawing Cards and disables their ability to hit.*/
    public void stopHitPlayer(){
        userPlayer.stopMe();
        dealerPlayer.setHitChoice(true);
        whosTurn = dealerPlayer;
    }


    /**This method is used to get the current state of the User's score.
     *
     * @return A String value representing the User's score. (This is used to help the main Controller display information about the Model)
     */
    public String userScore(){
        return "" + userPlayer.getTotalPoints();
    }

    /**This method is used to get the current state of the Dealer's score.
     *
     * @return A String value representing the Dealer's score. (This is used to help the main Controller display information about the Model)
     */
    public String dealerScore(){
        return "" + dealerPlayer.getTotalPoints();
    }

    /**This method is used to calculate how many moves are left for the User player.
     *
     * @return An int value representing the amount of hits left the User can make.
     */
    public int userMovesLeft(){
        return userPlayer.getMovesLeft();
    }

    /**This method is used to calculate how many moves are left for the Dealer player.
     *
     * @return An int value representing the amount of hits left the Dealer can make.
     */
    public int dealerMovesLeft(){
        return dealerPlayer.getMovesLeft();
    }

    /** This method actively is called to check if either the User or Deal have busted during the game.
     *
     * @return If a player has busted then it will return true, otherwise it will return false if neither have busted yet.
     */
    public boolean checkBust(){

        //Check User first
        if(userPlayer.getTotalPoints() >BUST){
            //Switch the Aces
            int aceIn = aceIndex(userPlayer);
            if(aceIn != -1){
                Card tempcard = userHand.get(aceIn);
                tempcard.setPointValue(1);
                userHand.set(aceIn, tempcard);
                userPlayer.setTotalPoints(userPlayer.getTotalPoints()-10);
                return false;
            }
            return true;
        }
        //Check Dealer next
        else if(dealerPlayer.getTotalPoints() >BUST){
            //Switch the Aces
            int aceIn = aceIndex(dealerPlayer);
            if(aceIn != -1){
                Card tempcard = dealerHand.get(aceIn);
                tempcard.setPointValue(1);
                dealerHand.set(aceIn, tempcard);
                dealerPlayer.setTotalPoints(dealerPlayer.getTotalPoints()-10);
                return false;
            }
            return true;
        }
        return false;
    }

    /** This method implements the case where an Ace would rather be used as 1 point instead of 11 points
     *
     * @param player Which Player are we checking for switch aces
     * @return  An int value representing where within a Player's hand there is an Ace whose value has been changed from 11 to 1. Returns -1 if no Aces are found.
     */
    private int aceIndex(Player player){

        if (player == userPlayer){
            for(int i = 0; i < userHand.size(); i++){
                if(userHand.get(i).getCardName().endsWith("A") && userHand.get(i).getPointValue() != 1){
                    return i;
                }
            }
        }
        else if(player == dealerPlayer){
            for(int i = 0; i < dealerHand.size(); i++){
                if(dealerHand.get(i).getCardName().endsWith("A") && dealerHand.get(i).getPointValue() != 1){
                    return i;
                }
            }
        }

        return -1;
    }

    /**This method checks to see if at anytime within the BlackJack game a winner is found.
     *
     * @return A String value representing which player won. Returns an empty String is there isn't a winner yet. (This is used to help the main Controller display information about the Model)
     */
    public String checkWinner(){
        //Compare User's hand to the Dealer's and check BlackJack
        if((userPlayer.getTotalPoints() > dealerPlayer.getTotalPoints() && userPlayer.getTotalPoints() <BUST) || checkBlackJack().equals("BlackJack!")){
            winner = userPlayer;
            endGame();
            return "User Won!";
        }
        //Compare Dealer's hand to the User's and check BlackJack
        else if((dealerPlayer.getTotalPoints() > userPlayer.getTotalPoints() && dealerPlayer.getTotalPoints() <BUST) || checkBlackJack().equals("BlackJack!")){
            winner = dealerPlayer;
            endGame();
            return "Dealer Won!";
        }
        //Check if there is a tie
        else if((dealerPlayer.getMovesLeft()==0 && userPlayer.getMovesLeft()==0) && (userPlayer.getTotalPoints() == dealerPlayer.getTotalPoints())){
            winner = dealerPlayer;
            endGame();
            return "Tie!";
        }
        //No one is the winner yet
        else{
            return "";
        }
    }

    /**This method is used to actively check if either player has achieved Black Jack.
     *
     * @return A String value that represents which play got Black Jack. Returns an empty String if neither Player has yet. (This is used to help the main Controller display information about the Model)
     */
    public String checkBlackJack(){
        if(userPlayer.getTotalPoints() == BUST ){
            winner = userPlayer;
            endGame();
            return "BlackJack!";
        }else if(dealerPlayer.getTotalPoints() ==BUST){
            winner = dealerPlayer;
            endGame();
            return "Dealer Won";
        }
        return"";
    }

    /**This method is used at the very end to stop the game from continuing once a winner is found.
     *
     */
    private void endGame(){
        if(winner == userPlayer){
            userPlayer.setMovesLeft(0);
            userPlayer.setHitChoice(false);
        }
        else{
            dealerPlayer.setMovesLeft(0);
            dealerPlayer.setHitChoice(false);
        }
    }

    /** This method is used to reset all values within the BlackJack game, in preparation for a new game to be played.
     *
     */
    public void reset(){
        shuffledDeck = new Deck(1);
        userPlayer = new User();
        dealerPlayer = new Dealer();
        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        whosTurn = userPlayer;
        initialDeal = 4;
    }

}
