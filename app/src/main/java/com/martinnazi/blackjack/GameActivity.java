package com.martinnazi.blackjack;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main Activity for the game
 */
public class GameActivity extends Activity implements View.OnClickListener {

    public enum PlayerTurn {Dealer, Player;}

    public enum GameState {Bet, Play, Finish, Win, Loss;}

    private PlayerTurn player;
    private LinearLayout dealerCards;
    private LinearLayout playerCards;
    private TextView bankTextView;
    private TextView betView;
    private Button button25, button50, button100, button200;

    private int playerScore;
    private int dealerScore;

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
            betView = (TextView) findViewById(R.id.betTextView);
            button25 = (Button) findViewById(R.id.button_25);


            player = PlayerTurn.Player;

            bank = 500;
            bankTextView.setText("$" + bank);

            currentDeck = fullDeckList;

            playerScore = 0;
            dealerScore = 0;
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
                if (currentDeck.size() > 0) {
                    int index = new Random().nextInt((currentDeck.size() - 1) + 1) + 1;
                    addCard(player, currentDeck.get(index));
                    player = PlayerTurn.Dealer;
                    if (dealerScore < 17) {
                        index = new Random().nextInt((currentDeck.size() - 1) + 1) + 1;
                        addCard(player, currentDeck.get(index));
                    } else {
                        player = PlayerTurn.Player;
                    }
                }
                break;
            case R.id.stand_button:
                showResults();
                break;
            default:
                break;
        }
    }

    private void showResults() {
        AlertDialog ad = new AlertDialog.Builder(GameActivity.this).create();
        ad.setTitle("Round Results");
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Continue Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                betView.setText("$0");
                dialog.dismiss();
            }
        });
        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Finish Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GameActivity.this.finish();
            }
        });
        if (playerScore > 21) {
            if (dealerScore > 21) {
                ad.setMessage("Round ended in a draw.");
            } else {
                ad.setMessage("Round ended in a loss.");
            }
        } else if (playerScore == 21) {
            if (playerScore > dealerScore) {
                ad.setMessage("Round ended in a win.");
            } else if (playerScore == dealerScore) {
                ad.setMessage("Round ended in a draw.");
            }
        } else if (playerScore < 21) {
            if (playerScore > dealerScore) {
                ad.setMessage("Round ended in a win.");
            } else if (playerScore < dealerScore) {
                ad.setMessage("Round ended in a loss.");
            } else {
                ad.setMessage("Round ended in a draw.");
            }
        }
        ad.show();
    }

    private void addCard(PlayerTurn _player, Drawable drawable) {
        ImageView card = new ImageView(getApplicationContext());
        card.setImageDrawable(drawable);
        card.setAdjustViewBounds(true);

        if (_player == PlayerTurn.Dealer) {
            dealerCards.addView(card);
            currentDeck.remove(drawable);
            dealerHand.add(drawable);
        } else {
            playerCards.addView(card);
            currentDeck.remove(drawable);
            playerHand.add(drawable);
        }
    }
}
