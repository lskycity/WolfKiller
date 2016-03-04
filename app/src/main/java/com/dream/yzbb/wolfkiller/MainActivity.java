package com.dream.yzbb.wolfkiller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.app.commonui.AboutUsActivity;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.service.GameManager;
import com.dream.yzbb.wolfkiller.ui.SettingsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView mGridView;
    private TextView mDispaly;

    private GameManager mGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.all_player);
        mDispaly = (TextView) findViewById(R.id.event_panel);
        findViewById(R.id.event_button).setOnClickListener(this);

        mGameManager = Factory.get().getGameManager();
        mGridView.setAdapter(new PlayerAdapter(this, mGameManager.getAllPlayers()));

        mGameManager.getNightRoundManager().startNightRound();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            openSettingsActivity();
            return true;
        } else if (id == R.id.menu_about) {
            openAboutActivity();
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }

        return false;
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openSettingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.event_button) {
            if(mGameManager.getNightRoundManager().hasNext()) {
                NightRole role = mGameManager.getNightRoundManager().nextRole();
                mDispaly.append(role.getName()+"\n");
            } else {
                mGameManager.getNightRoundManager().endNightRound();
                mDispaly.append("-------------------\n");
            }
        }
    }
}
