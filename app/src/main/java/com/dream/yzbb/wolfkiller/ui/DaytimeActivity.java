package com.dream.yzbb.wolfkiller.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager;
import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager.DaytimeEvent;
import com.dream.yzbb.wolfkiller.service.GameManager;
import com.dream.yzbb.wolfkiller.service.NightRoundManager;

import java.util.List;
import java.util.Random;

public class DaytimeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mNextAction;
    private TextView mEventInfo;
    private final String FRAGMENT_TAG = "Warn_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daytime);
        mNextAction = (Button) findViewById(R.id.next_event);
        mEventInfo = (TextView) findViewById(R.id.daytime_info);
        mNextAction.setOnClickListener(this);
        GameManager gameManager = Factory.get().getGameManager();
        gameManager.endNightAndStartDay();
        Log.d(Constants.LOG_TAG, getDeadPersons());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_event:
                DaytimeRoundManager dayManager = Factory.get().getGameManager().getDaytimeRoundManager();
                NightRoundManager nightManager = Factory.get().getGameManager().getNightRoundManager();
                if (dayManager.hasNextEvent()) {
                    DaytimeEvent event = dayManager.nextDaytimeEvent();
                    switch (event) {
                        case PUBLISH_DEATH:
                            mEventInfo.append(getNightResult(nightManager.latestNightRoundRecord()) + "\n");
                            break;
                        case CAPTAIN_DEATH:
                            mEventInfo.append("警长已挂，请重新选警长\n");
                            break;
                        case GAME_STATUS:
                            if (isGameOver()) {
                                Toast.makeText(this, "Game is Over!!!", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                mEventInfo.append("计算游戏是否已经结束\n");
                            }
                            break;
                        case SPEECH:
                            mEventInfo.append("请游戏玩家轮流发言\n");
                            break;
                        case VOTE:
                            mEventInfo.append("投票处决玩家\n");
                            break;
                        case LOVER_DEATH:
                            mEventInfo.append("恋人死亡\n");
                            break;
                    }
                } else {
                    finish();
                }
                break;
        }
    }

    public static class WarnDialogFragment extends DialogFragment {
        public static final String TITLE = "dialog_title";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstance) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            Bundle bundle = getArguments();
            builder.setTitle(bundle.getString(TITLE));
            return builder.create();
        }
    }

    private void showTips(String content) {
        WarnDialogFragment dialogFragment = new WarnDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WarnDialogFragment.TITLE, content);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG);
    }

    private String getNightResult(NightRoundRecord record) {
        if (record != null) {
            List<Player> deadPerson = record.deadPlayers(record.getLovers());
            StringBuilder builder = new StringBuilder();
            builder.append("昨天晚上");
            for (int i = 0; i < deadPerson.size(); i++) {
                builder.append(deadPerson.get(i).getPlayID() + " ");
            }
            builder.append("死了");
            return builder.toString();
        }
        return null;
    }

    private boolean isGameOver() {
        return Factory.get().getGameManager().isGameOver();
    }

    private String getDeadPersons() {
        StringBuilder builder = new StringBuilder();
        builder.append("已经死了这些人: ");
        for (Player p : Factory.get().getGameManager().getAllPlayers()) {
            if (p.getStatus() == Player.Status.DEAD) {
                builder.append(p.getPlayID() + "号, ");
            }
        }
        return builder.toString();
    }


}
