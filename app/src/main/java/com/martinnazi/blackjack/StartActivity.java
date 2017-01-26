package com.martinnazi.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button start = (Button) findViewById(R.id.gameButton);
        Button about = (Button) findViewById(R.id.developebutton);

        start.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gameButton:
                Intent gIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(gIntent);
                break;
            case R.id.developebutton:
                Intent dIntent = new Intent(getApplicationContext(), DeveloperActivity.class);
                startActivity(dIntent);
                break;
            default:
                break;
        }
    }
}
