package com.dream.yzbb.wolfkiller.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

public class SetupNewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_new_game);
        Factory.get().getRoleManager().getRoles();
        RoleDistributionInfo info = Factory.get().getRoleManager().getRoleDistributionInfo(7);
        //Randomly distribute players' roles
        Factory.get().getGameManager().initGame(info);
        //Then go to CardDeliveryActivity
    }
}
