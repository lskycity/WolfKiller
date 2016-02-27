package com.dream.yzbb.wolfkiller.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;

public class CardDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delievery);
        //Get players
        Factory.get().getGameManager().getPlayerCount();
        //Get specific player's information
        Player nextPlayer = Factory.get().getGameManager().getNextPlayer();
        nextPlayer.getRole();
        nextPlayer.setName("Xianpeng");

        //start game after iteration of players' info
        Factory.get().getGameManager().startGame();
        //Then go to main activity
    }
}