package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.app.commonui.AboutUsActivity;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.service.GameManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private GridView mGridView;
    private TextView mDisplay;

    private GameManager mGameManager;
    private PlayerAdapter mPlayerAdapter;

    private Button positive;
    private Button negative;
    private Button nightNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.all_player);
        mDisplay = (TextView) findViewById(R.id.event_panel);
        nightNow = (Button) findViewById(R.id.event_button);
        positive = (Button) findViewById(R.id.positive);
        negative = (Button) findViewById(R.id.negative);
        nightNow.setOnClickListener(this);
        positive.setOnClickListener(this);
        negative.setOnClickListener(this);

        mGameManager = Factory.get().getGameManager();
        mGridView.setAdapter(mPlayerAdapter = new PlayerAdapter(this, mGameManager.getAllPlayers()));
        mGridView.setOnItemClickListener(this);

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
        }

        return false;
    }

    private void openAboutActivity() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    private void openSettingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    NightRole currentRole;

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.event_button) {
            mGameManager.getNightRoundManager().startNightRound();
            nightNow.setVisibility(View.GONE);
            positive.setVisibility(View.VISIBLE);
            negative.setVisibility(View.VISIBLE);
            currentRole = mGameManager.getNightRoundManager().nextRole();
            mDisplay.append(currentRole.getName() + "\n");
            mPlayerAdapter.setSelectedCount(currentRole.getActionTargetNumber());

        } else if(v.getId() == R.id.positive){
            if(mPlayerAdapter.getSelectPlayers().length == currentRole.getActionTargetNumber()) {
                mGameManager.getNightRoundManager().doAction(currentRole, mPlayerAdapter.getSelectPlayers());
                gotoNextRole();
            } else {
                Toast.makeText(this, "need select player", Toast.LENGTH_LONG).show();
            }

        } else if(v.getId() == R.id.negative) {
            gotoNextRole();
        }
    }

    private void gotoNextRole() {
        if(mGameManager.getNightRoundManager().hasNext()) {
            currentRole = mGameManager.getNightRoundManager().nextRole();
            mDisplay.append(currentRole.getName() + "\n");
            mPlayerAdapter.setSelectedCount(currentRole.getActionTargetNumber());
        } else {
            NightRoundRecord roundRecord = mGameManager.getNightRoundManager().endNightRound();
            mPlayerAdapter.setSelectedCount(0);
            mDisplay.append("-------------------\n");
            mDisplay.append(roundRecord.toString()+"\n");
            mDisplay.append("-------------------\n");
            nightNow.setVisibility(View.VISIBLE);
            positive.setVisibility(View.GONE);
            negative.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPlayerAdapter.setSelectedPosition(position);
    }
}
