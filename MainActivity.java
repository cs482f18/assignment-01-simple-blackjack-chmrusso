package com.example.christina.simpleblackjack;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/** A Controller class to manage both the app display and the BlackJack game.
 *
 * @author Christina Russo
 * @version 1.0 10/6/18
 * @since version 1.0
 *
 */
public class MainActivity extends AppCompatActivity {

    /**Instanciate which game is being played*/
    public BlackJack BJGame;

    /**This method is called once the android app is opened and ultimately starts the game.
     *
     * @param savedInstanceState This is the Instance State last saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playGame();
    }

    /**This method is called by the hit button and lets the User continue to draw cards until he or she busts or runs out of moves, thus disabling their buttons and letting the Dealer have it's turn.
     *
     * @param button the hit button that was pressed on the android app.
     */
    public void hit(View button){

        TextView userPoints = findViewById(R.id.userPoints);
        TextView display =  findViewById(R.id.display);
        View stopButton =  findViewById( R.id.stopButton_id);

        if(BJGame.userMovesLeft() >= 1) {
            switch (BJGame.userMovesLeft()) {
                case 3:
                    TextView cardThree =  findViewById(R.id.cardThree);
                    cardThree.setText(BJGame.hitPlayer());
                    cardThree.setBackgroundColor(Color.WHITE);
                    userPoints.setText(BJGame.userScore());
                    break;
                case 2:
                    TextView cardFour =  findViewById(R.id.cardFour);
                    cardFour.setText(BJGame.hitPlayer());
                    cardFour.setBackgroundColor(Color.WHITE);
                    userPoints.setText(BJGame.userScore());
                    break;
                case 1:
                    TextView cardFive =  findViewById(R.id.cardFive);
                    cardFive.setText(BJGame.hitPlayer());
                    cardFive.setBackgroundColor(Color.WHITE);
                    userPoints.setText(BJGame.userScore());
                    break;
            }
        }

        if(BJGame.checkBust()){

            String bust = "Bust!";
            display.setText(bust);

            display.setTextColor(Color.WHITE);
            stopButton.setEnabled(false);
            button.setEnabled(false);
            return;
        }
        else if(BJGame.userMovesLeft() == 0) {

            display.setText(BJGame.checkBlackJack());

            display.setTextColor(Color.WHITE);
            stopButton.setEnabled(false);
            button.setEnabled(false);
            stop(stopButton);
            return;
        }
        else if(BJGame.checkBlackJack().equals("BlackJack!")) {

            display.setText(BJGame.checkBlackJack());
            display.setTextColor(Color.WHITE);
            stopButton.setEnabled(false);
            button.setEnabled(false);
        }

    }

    /**This method is called by the stop button and lets the Dealer take its turn. It will continue to draw cards until it reaches a numbber higher than 17. Dealers in particular do not hit on 17 or higher to improve their chances of winning.
     *
     * @param button the stop button that was pressed on the android app.
     */
    public void stop(View button){
        //Disable the hit and stop buttons
        View hitButton =  findViewById( R.id.hitButton_id);
        View stopButton =  findViewById( R.id.stopButton_id);
        hitButton.setEnabled(false);
        stopButton.setEnabled(false);

        BJGame.stopHitPlayer();
        TextView dealerPoints =  findViewById(R.id.dealerPoints);

        String cardName = "";
        while(BJGame.dealerMovesLeft() >= 1) {

            switch (BJGame.dealerMovesLeft()) {
                case 3:
                    TextView dealerThree = findViewById(R.id.dealerThree);
                    cardName = BJGame.hitPlayer();
                    if(cardName.equals("")){
                        break;
                    }
                    dealerThree.setText(cardName);
                    dealerThree.setBackgroundColor(Color.WHITE);
                    dealerPoints.setText(BJGame.dealerScore());
                    break;

                case 2:
                    TextView dealerFour =  findViewById(R.id.dealerFour);
                    cardName = BJGame.hitPlayer();
                    if(cardName.equals("")){
                        break;
                    }
                    dealerFour.setText(cardName);
                    dealerFour.setBackgroundColor(Color.WHITE);
                    dealerPoints.setText(BJGame.dealerScore());
                    break;

                case 1:
                    TextView dealerFive = findViewById(R.id.dealerFive);
                    cardName = BJGame.hitPlayer();
                    if(cardName.equals("")){
                        break;
                    }
                    dealerFive.setText(cardName);
                    dealerFive.setBackgroundColor(Color.WHITE);
                    dealerPoints.setText(BJGame.dealerScore());
                    break;
            }

            TextView display = findViewById(R.id.display);

            if(BJGame.checkBust()){
                String youWin = "You Win!";
                display.setText(youWin);
                display.setTextColor(Color.WHITE);
                return;
            }
            else if(BJGame.dealerMovesLeft() == 0) {
                display.setText(BJGame.checkWinner());
                display.setTextColor(Color.WHITE);
            }
            else if(BJGame.checkBlackJack().equals("Dealer Won")) {
                display.setText(BJGame.checkBlackJack());
                display.setTextColor(Color.WHITE);
            }
        }
    }

    /**This method is called when the NG button is pressed to reset the app's features and start a new game. This button can be pressed at anytime to start any game over anew.
     *
     * @param button The NG button to start a new game.
     */
    public void restartApp(View button) {
        BJGame.reset();

        TextView display =  findViewById(R.id.display);
        display.setText("");
        View hitButton = findViewById( R.id.hitButton_id);
        View stopButton = findViewById( R.id.stopButton_id);
        hitButton.setEnabled(true);
        stopButton.setEnabled(true);
        playGame();
    }

    /**This is the heart of the model, the method that is responsible for starting the game. It is also is recalled with every new game.
     *
     */
    public void playGame(){

        Log.w("Deck", "________________ NEWGAME");
        BJGame = new BlackJack();

        //user's cards
        TextView cardOne = findViewById( R.id.cardOne);
        TextView cardTwo = findViewById( R.id.cardTwo);
        TextView cardThree = findViewById( R.id.cardThree);
        TextView cardFour =  findViewById( R.id.cardFour);
        TextView cardFive =  findViewById( R.id.cardFive);


        //Dealer's cards
        TextView DealerOne = findViewById(R.id.dealerOne);
        TextView DealerTwo = findViewById(R.id.dealerTwo);
        TextView DealerThree =  findViewById(R.id.dealerThree);
        TextView DealerFour = findViewById(R.id.dealerFour);
        TextView DealerFive = findViewById(R.id.dealerFive);


        //deal cards
        cardOne.setText(BJGame.dealHand());
        cardTwo.setText(BJGame.dealHand());


        DealerOne.setText(BJGame.dealHand());
        DealerTwo.setText(BJGame.dealHand());

        TextView userPoints = findViewById(R.id.userPoints);
        TextView dealerPoints =  findViewById(R.id.dealerPoints);

        userPoints.setText(BJGame.userScore());
        dealerPoints.setText(BJGame.dealerScore());

        //hide cards
        cardThree.setBackgroundColor(Color.RED);
        cardFour.setBackgroundColor(Color.RED);
        cardFive.setBackgroundColor(Color.RED);

        DealerThree.setBackgroundColor(Color.RED);
        DealerFour.setBackgroundColor(Color.RED);
        DealerFive.setBackgroundColor(Color.RED);

        cardThree.setText("");
        cardFour.setText("");
        cardFive.setText("");

        DealerThree.setText("");
        DealerFour.setText("");
        DealerFive.setText("");


        if( BJGame.checkBlackJack().equals("Dealer Won")){
            TextView display =  findViewById(R.id.display);
            display.setText(BJGame.checkBlackJack());
            display.setTextColor(Color.WHITE);
            View hitButton =  findViewById( R.id.hitButton_id);
            View stopButton =  findViewById( R.id.stopButton_id);
            hitButton.setEnabled(false);
            stopButton.setEnabled(false);
        }
        if( BJGame.checkBlackJack().equals("BlackJack!")){
            TextView display =  findViewById(R.id.display);
            display.setText(BJGame.checkBlackJack());
            display.setTextColor(Color.WHITE);
            View hitButton =  findViewById( R.id.hitButton_id);
            View stopButton =  findViewById( R.id.stopButton_id);
            hitButton.setEnabled(false);
            stopButton.setEnabled(false);
        }
    }
}
