package com.martinnazi.blackjack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

/**
 * Main Activity for the game
 */
public class GameActivity extends Activity implements View.OnClickListener {

    public enum PlayerType {Dealer, Player;}

    private LinearLayout dealerCards;
    private TextView bankTextView;
    private TextView betTextView;
    private long bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        if (savedInstanceState == null) {
            dealerCards = (LinearLayout) findViewById(R.id.dealerCards);
            bankTextView = (TextView) findViewById(R.id.bankTextView);
            betTextView = (TextView) findViewById(R.id.betTextView);


            //Generates Random number between 250 and 1000 for initial bet
            long initial_bank = Math.round((Math.random() * (1000 - 250) + 250) / 10) * 10;
            bank = initial_bank;
            bankTextView.setText("$" + initial_bank);

            addCard(null, R.drawable.spades_j);
            addCard(null, R.drawable.clubs_2);
            addCard(null, R.drawable.clubs_3);
            addCard(null, R.drawable.hearts_k);
            addCard(null, R.drawable.clubs_4);
            addCard(null, R.drawable.spades_9);
            addCard(null, R.drawable.clubs_5);
            addCard(null, R.drawable.hearts_a);
            addCard(null, R.drawable.clubs_6);
            addCard(null, R.drawable.clubs_7);
        } else {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_25:
                break;
            case R.id.button_50:
                break;
            case R.id.button_100:
                break;
            case R.id.button_200:
                break;
            case R.id.hit_button:
                break;
            case R.id.stand_button:
                break;
            default:
                break;
        }
    }

    private void addCard(PlayerType player, int drawableId) {
        ImageView card = new ImageView(getApplicationContext());
        card.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), drawableId));
        card.setAdjustViewBounds(true);
        dealerCards.addView(card);
    }
}
