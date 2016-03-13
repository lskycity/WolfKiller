package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.app.commonui.AboutUsActivity;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.service.GameManager;
import com.dream.yzbb.wolfkiller.utils.ViewUtils;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        StartDayOrNightFragment.OnStartClickListener, NightEventFragment.NightEventListener {
    private GridView mGridView;
    private TextView mDisplay;

    private GameManager mGameManager;
    private PlayerAdapter mPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.all_player);
        mDisplay = (TextView) findViewById(R.id.event_panel);

        mGameManager = Factory.get().getGameManager();
        mGridView.setAdapter(mPlayerAdapter = new PlayerAdapter(this, mGameManager.getAllPlayers()));
        mGridView.setOnItemClickListener(this);
        mGameManager.endDayAndStartNight();
        applyStartFragment(true);

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
            ViewUtils.setVisible(mDisplay, mDisplay.getVisibility() != View.VISIBLE);
//            openAboutActivity();
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

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.event_button) {
//            mGameManager.getNightRoundManager().startNightRound();
//            nightNow.setVisibility(View.GONE);
//            positive.setVisibility(View.VISIBLE);
//            negative.setVisibility(View.VISIBLE);
//            currentRole = mGameManager.getNightRoundManager().nextRole();
//            mDisplay.append(currentRole.getName() + "\n");
//            mPlayerAdapter.setSelectedCount(currentRole.getActionTargetNumber());
//
//        } else if (v.getId() == R.id.positive) {
//            Player[] players = mPlayerAdapter.getSelectPlayers();
//            if (players.length == currentRole.getActionTargetNumber()) {
//                mDisplay.append("select " + Arrays.toString(players) + "\n");
//                mGameManager.getNightRoundManager().doAction(currentRole, players);
//                gotoNextRole();
//            } else {
//                Toast.makeText(this, "need select player", Toast.LENGTH_LONG).show();
//            }
//
//        } else if (v.getId() == R.id.negative) {
//            gotoNextRole();
//        } else if (v.getId() == R.id.start_daytime_button) {
//            goToDaytimeActivity();
//        }
//    }

    private void goToDaytimeActivity() {
        startActivityForResult(new Intent(this, DaytimeActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100) {
            mGameManager.endDayAndStartNight();
            applyStartFragment(true);
        }
    }

    private void gotoNextRole() {
        if (mGameManager.getNightRoundManager().hasNext()) {
            currentRole = mGameManager.getNightRoundManager().nextRole();
            gotoRoleFragment();
            mDisplay.append(currentRole.getName() + "\n");
            mPlayerAdapter.setSelectedCount(currentRole.getActionTargetNumber());
        } else {
            mGameManager.endNightAndStartDay();
            mPlayerAdapter.setSelectedCount(0);
            applyStartFragment(false);
            mDisplay.append("-------------------\n");
        }
    }

    private void gotoRoleFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, NightEventFragment.create(currentRole.getRoleID())).commitAllowingStateLoss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPlayerAdapter.setSelectedPosition(position);
    }

    private void applyStartFragment(boolean isNight) {
        getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, StartDayOrNightFragment.create(isNight)).commitAllowingStateLoss();
    }

    @Override
    public void onStart(boolean isNight) {
        if(isNight) {
            gotoNextRole();
        } else {
            goToDaytimeActivity();
        }
    }

    @Override
    public void shouldChoosePlayer(int playNumber) {
        mPlayerAdapter.setSelectedCount(playNumber);
    }

    @Override
    public Player[] getPlayers() {
        return mPlayerAdapter.getSelectPlayers();
    }

    @Override
    public void actionFinished() {
        gotoNextRole();
    }
}
