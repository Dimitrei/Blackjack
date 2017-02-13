package com.martinnazi.blackjack;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Main Activity for the game
 */
public class GameActivity extends Activity implements View.OnClickListener {

    public enum PlayerTurn {Dealer, Player;}

    public enum GameState {Bet, Play, Finish;}

    private PlayerTurn player;
    private LinearLayout dealerCards;
    private LinearLayout playerCards;
    private TextView bankTextView;
    private TextView betTextView;
    private Button button25, button50, button100, button200;

    private long bank;

    ArrayList<Drawable> fullDeckList;
    ArrayList<Drawable> playerHand;
    ArrayList<Drawable> dealerHand;
    ArrayList<Drawable> currentDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getFullDeck();


        if (savedInstanceState == null) {
            dealerCards = (LinearLayout) findViewById(R.id.dealerCards);
            playerCards = (LinearLayout) findViewById(R.id.playerCards);
            bankTextView = (TextView) findViewById(R.id.bankTextView);
            betTextView = (TextView) findViewById(R.id.betTextView);
            button25 = (Button) findViewById(R.id.button_25);


            player = PlayerTurn.Player;

            bank = 500;
            bankTextView.setText("$" + bank);

            currentDeck = fullDeckList;
        } else {

        }
    }

    private void getFullDeck() {
        Field[] drawables = R.drawable.class.getFields();
        fullDeckList = new ArrayList<>();
        for (Field field : drawables) {
            try {
                if (field.getName().contains("clubs") ||
                        field.getName().contains("spades") ||
                        field.getName().contains("hearts") ||
                        field.getName().contains("diamonds"))
                    fullDeckList.add(ContextCompat.getDrawable(getApplicationContext(),
                            field.getInt(null)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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

    private void addCard(PlayerTurn _player, int drawableId) {
        ImageView card = new ImageView(getApplicationContext());
        card.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), drawableId));
        card.setAdjustViewBounds(true);

        if (_player == PlayerTurn.Dealer) {
            dealerCards.addView(card);
            player = PlayerTurn.Player;
        } else {
            playerCards.addView(card);
            player = PlayerTurn.Dealer;
        }
    }

    private void addCard(PlayerTurn _player, Drawable drawable) {
        ImageView card = new ImageView(getApplicationContext());
        card.setImageDrawable(drawable);
        card.setAdjustViewBounds(true);

        if (_player == PlayerTurn.Dealer) {
            dealerCards.addView(card);
            currentDeck.remove(drawable);
            dealerHand.add(drawable);
            player = PlayerTurn.Player;
        } else {
            playerCards.addView(card);
            currentDeck.remove(drawable);
            playerHand.add(drawable);
            player = PlayerTurn.Dealer;
        }
    }
}
