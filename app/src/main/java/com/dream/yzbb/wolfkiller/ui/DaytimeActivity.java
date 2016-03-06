package com.dream.yzbb.wolfkiller.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;

public class DaytimeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mNextAction;
    private TextView mEventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daytime);
        mNextAction = (Button) findViewById(R.id.next_event);
        mEventInfo = (TextView) findViewById(R.id.daytime_info);
        mNextAction.setOnClickListener(this);
        Factory.get().getGameManager().getDaytimeRoundManager().startDaytimeRound();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_event:
                break;
        }
    }
}
