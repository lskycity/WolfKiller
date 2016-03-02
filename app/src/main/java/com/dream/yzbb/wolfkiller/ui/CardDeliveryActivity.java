package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.MainActivity;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;

import java.util.List;

public class CardDeliveryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delievery);
        TextView tv = (TextView) findViewById(R.id.card_delivery_text);
        //Get players
        List<Player> list = Factory.get().getGameManager().getAllPlayers();
        //Get specific player's information
        for (Player player : list) {
            tv.append(player.getPlayID()+", "+player.getStatus() + ", " + player.getRole().getName());
            tv.append("\n");
        }

        findViewById(R.id.start_game_btn).setOnClickListener(this);

        //start game after iteration of players' info
//        Factory.get().getGameManager().startGame();
        //Then go to main activity
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_game_btn) {
            Factory.get().getGameManager().startGame();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
