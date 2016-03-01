package com.dream.yzbb.wolfkiller.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

public class SetupNewGameActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private NumberPicker mNumberPicker;
    private TextView mDistributionInfo;

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
    }

    private void updateRoleDistributionInfo(int playerCount) {
        mDistributionInfo.setText(Factory.get().getRoleManager().getRoleDistributionInfo(playerCount).toString());
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//        Toast.makeText(this, "Old value is " + oldVal + ", new value is " + newVal, Toast.LENGTH_LONG).show();
        updateRoleDistributionInfo(newVal);
    }
}
