package com.dream.yzbb.wolfkiller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.app.commonui.AboutUsActivity;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Witch;
import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager;
import com.dream.yzbb.wolfkiller.service.GameManager;
import com.dream.yzbb.wolfkiller.service.NightRoundManager;
import com.dream.yzbb.wolfkiller.ui.daytime.ActionDaytimeFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.ChangeCaptainFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.DaytimeFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.GameOverFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.LoverDeathFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.PublishResultFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.SpeechFragment;
import com.dream.yzbb.wolfkiller.ui.daytime.VoteFragment;
import com.dream.yzbb.wolfkiller.utils.ViewUtils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        StartDayOrNightFragment.OnStartClickListener, NightEventFragment.NightEventListener, DaytimeFragment.NextActionListener, ActionDaytimeFragment.DaytimeListener {
    private GridView mGridView;
    private TextView mDisplay;
    private TextView mCaptainInfo;

    private GameManager mGameManager;
    private PlayerAdapter mPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.all_player);
        mDisplay = (TextView) findViewById(R.id.event_panel);
        mCaptainInfo = (TextView) findViewById(R.id.captain_info);

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

    private void goToDaytimeActivity() {
        startActivityForResult(new Intent(this, DaytimeActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
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

    private void gotoNextDayEvent() {
        DaytimeRoundManager dayManager = Factory.get().getGameManager().getDaytimeRoundManager();
        NightRoundManager nightManager = Factory.get().getGameManager().getNightRoundManager();
        if (dayManager.hasNextEvent()) {
            DaytimeRoundManager.DaytimeEvent event = dayManager.nextDaytimeEvent();
            Log.d(Constants.LOG_TAG, "Go to next event: " + event);
            switch (event) {
                case PUBLISH_DEATH:
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new PublishResultFragment()).commitAllowingStateLoss();
                    break;
                case CAPTAIN_DEATH:
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new ChangeCaptainFragment()).commitAllowingStateLoss();
                    break;
                case GAME_STATUS:
                    if (Factory.get().getGameManager().isGameOver()) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new GameOverFragment()).commitAllowingStateLoss();
                    } else {
                        gotoNextDayEvent();
                    }
                    break;
                case SPEECH:
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new SpeechFragment()).commitAllowingStateLoss();
                    break;
                case VOTE:
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new VoteFragment()).commitAllowingStateLoss();
                    break;
                case LOVER_DEATH:
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, new LoverDeathFragment()).commitAllowingStateLoss();
                    break;
            }
            updateCaptainInfo();
        } else {
            //Go to night mode
            applyStartFragment(true);
        }
    }

    private void gotoRoleFragment() {
        if (currentRole instanceof Witch) {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, WitchEventFragment.create(currentRole.getRoleID())).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_panel, NightEventFragment.create(currentRole.getRoleID())).commitAllowingStateLoss();
        }
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
        if (isNight) {
            //night
            gotoNextRole();
        } else {
            //daytime
            gotoNextDayEvent();
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

    @Override
    public void onNextDaytimeActionTriggered() {
        gotoNextDayEvent();
    }

    @Override
    public void onTargetPlayerDecided(int playerCount) {
        mPlayerAdapter.setSelectedCount(playerCount);
    }

    @Override
    public Player[] getSelectedPlayers() {
        return mPlayerAdapter.getSelectPlayers();
    }

    @Override
    public void daytimeActionFinished() {
        Log.i(Constants.LOG_TAG, "MainActivity [daytimeActionFinished] is called, go to next day event");
        gotoNextDayEvent();
        mPlayerAdapter.notifyDataSetChanged();
    }

    private void updateCaptainInfo() {
        GameManager gameManager = Factory.get().getGameManager();
        if (gameManager.getCaptain() != null) {
            mCaptainInfo.setText("当前警长是" + Factory.get().getGameManager().getCaptain().getPlayID() + "号");
        } else {
            mCaptainInfo.setText("当前无警长");
        }
    }
}
