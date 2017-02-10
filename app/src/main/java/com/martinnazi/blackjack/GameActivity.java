package com.martinnazi.blackjack;

import android.app.Activity;
import android.os.Bundle;

/**
 * Main Activity for the game
 */
public class GameActivity extends Activity {

    public enum PlayerType { Dealer, Player; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
