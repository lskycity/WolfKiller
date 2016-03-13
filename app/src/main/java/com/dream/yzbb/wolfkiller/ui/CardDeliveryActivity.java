package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Civilian;
import com.dream.yzbb.wolfkiller.entity.Guard;
import com.dream.yzbb.wolfkiller.entity.Jupiter;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.WereWolf;
import com.dream.yzbb.wolfkiller.entity.Witch;

import java.util.List;

public class CardDeliveryActivity extends AppCompatActivity implements View.OnClickListener {

    private int position = 0;

    private boolean cardcovered = true ;

    private ImageView mCardView;

    private Button mSkipButton;

    private ImageButton mNextButton;

    private ImageButton mStartButton;

    private List<Player> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delievery);
        mCardView = (ImageView)findViewById(R.id.card_cover);
        mNextButton = (ImageButton)findViewById(R.id.next_card_btn);
        mStartButton = (ImageButton) findViewById(R.id.start_game_btn);
        mSkipButton = (Button)findViewById(R.id.skip_btn);
        mStartButton.setVisibility(View.GONE);
        //Get players
        list = Factory.get().getGameManager().getAllPlayers();
        mStartButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mCardView.setOnClickListener(this);
        mSkipButton.setOnClickListener(this);

        //start game after iteration of players' info
        // Factory.get().getGameManager().startGame();
        //Then go to main activity


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_card_btn:
                mCardView.setImageResource(R.drawable.card);
                position++;
                cardcovered = true;
                break;

            case R.id.card_cover:

                if(cardcovered = true)
                {
                    unCoverCard();
                    if(position == list.size()-1)
                    {
                        mNextButton.setVisibility(View.GONE);
                        mStartButton.setVisibility(View.VISIBLE);
                    }
                }


                break;

            case R.id.start_game_btn:
                Factory.get().getGameManager().startGame();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.skip_btn:
                Factory.get().getGameManager().startGame();
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;

        }
    }

    private void unCoverCard() {
        Player player = list.get(position);

        if(player.getRole() instanceof Guard)
        {
            mCardView.setImageResource(R.drawable.protector);
        } else if(player.getRole() instanceof Jupiter)
        {
            mCardView.setImageResource(R.drawable.jupiter);
        } else if(player.getRole() instanceof WereWolf)
        {
            mCardView.setImageResource(R.drawable.wolf);
        }else if (player.getRole() instanceof Witch)
        {
            mCardView.setImageResource(R.drawable.witch);
        }else {
            mCardView.setImageResource(R.drawable.people);
        }
        cardcovered = false;
    }
}
