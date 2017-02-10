package com.martinnazi.blackjack;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkeonet64 on 2/2/2017.
 */

public class CardList extends ArrayAdapter<String> {

    Activity activity;
    ArrayList<String> cards;

    public CardList(Activity context, /**put enum type here for dealer or player to distinguish which cards are shown and which are face down,**/ ArrayList<String> objects) {
        super(context, R.layout.card_listelement, objects);
        cards = objects;
        activity = context;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = activity.getLayoutInflater();
        View listElement = inflater.inflate(R.layout.card_listelement, null, true);

        ImageView card = (ImageView) listElement.findViewById(R.id.cardImage);
        TextView cardLabel = (TextView) listElement.findViewById(R.id.cardNumber);

        return listElement;
    }
}
