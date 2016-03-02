package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.MainActivity;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

public class SetupNewGameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, View.OnClickListener {

    private NumberPicker mNumberPicker;
    private TextView mDistributionInfo;
    private Button mCardDeliveryBtn;
    private Button mStartGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_new_game);
        initView();
        /**
         Factory.get().getRoleManager().getRoles();
         RoleDistributionInfo info = Factory.get().getRoleManager().getRoleDistributionInfo(7);
         //Randomly distribute players' roles
         Factory.get().getGameManager().initGame(info);
         //Then go to CardDeliveryActivity
         **/
    }

    private void initView() {
        mNumberPicker = (NumberPicker) findViewById(R.id.player_count);
        mNumberPicker.setMaxValue(Constants.MAX_PLAYER_COUNT);
        mNumberPicker.setMinValue(Constants.MIN_PLAYER_COUNT);
        mNumberPicker.setOnValueChangedListener(this);

        mDistributionInfo = (TextView) findViewById(R.id.info);
        mCardDeliveryBtn = (Button) findViewById(R.id.card_delivery_btn);
        mCardDeliveryBtn.setOnClickListener(this);
        mStartGameBtn = (Button) findViewById(R.id.start_game_btn);
        mStartGameBtn.setOnClickListener(this);
    }

    private void updateRoleDistributionInfo(int playerCount) {
        mDistributionInfo.setText(Factory.get().getRoleManager().getRoleDistributionInfo(playerCount).toString());
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        updateRoleDistributionInfo(newVal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_delivery_btn:
                openCardDeliveryActivity();
                break;
            case R.id.start_game_btn:
                openMainActivity();
                break;
        }
    }

    private void openCardDeliveryActivity() {
        Intent intent = new Intent(this, CardDeliveryActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.PLAYER_COUNT, mNumberPicker.getValue());
        startActivity(intent);
    }
}
