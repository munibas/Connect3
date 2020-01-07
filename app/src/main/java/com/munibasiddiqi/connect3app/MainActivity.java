package com.munibasiddiqi.connect3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.util.Log.*;
import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {

    // 0 = red, 1 = yellow

    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);


            if (activePlayer == 1) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            }

            counter.animate().translationYBy(1000f).setDuration(30);

            for (int[] winningPosition : winningPositions)

            {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[0]] != 2)) {

                   // System.out.println(gameState[winningPosition[0]]);
                    //winner message

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 1){
                        winner = "Yellow";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.textViewWinnerMessage);
                    winnerMessage.setText(winner+" has won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutPlayAgain);
                    layout.setVisibility(View.VISIBLE);

                    gameIsActive = false;


                }else {

                    boolean gameDraw = true;
                    for (int i=0; i<gameState.length; i++){
                        if (gameState[i] == 2){
                            gameDraw = false;
                        }
                    }
                    if (gameDraw){
                        TextView winnerMessage = (TextView) findViewById(R.id.textViewWinnerMessage);
                        winnerMessage.setText("Its a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutPlayAgain);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }

        }

    }

    public void playAgain(View view){

        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutPlayAgain);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        gameIsActive = true;

        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.GridLayout);


        for(int i=0;i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
