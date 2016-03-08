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

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager;
import com.dream.yzbb.wolfkiller.service.daytime_handler.DaytimeHandler;

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
        Factory.get().getGameManager().getDaytimeRoundManager().startDaytimeRound();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_event:
                DaytimeRoundManager daytimeRoundManager = Factory.get().getGameManager().getDaytimeRoundManager();
                if (daytimeRoundManager.hasNextAction()) {
                    DaytimeHandler handler = daytimeRoundManager.nextDaytimeHandler();
                    Log.d(Constants.LOG_TAG,handler.toString());
                    String result = handler.handleRequest(daytimeRoundManager.latestDaytimeRoundRecord());
                    if (handler.shouldConsiderGameOver()) {
                        if (isGameOver()) {
                            showTips("Game is Over");
                        } else {
                            return;
                        }
                    }
                    if (handler.shouldConsiderCaptain()) {
                        if (isCaptainDead()) {
                            showTips("Capatain is dead");
                        }
                    }
                    mEventInfo.append(result+"\n");

                } else {
                    daytimeRoundManager.endDaytimeRound();
                    //天黑了
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

    private boolean isGameOver() {
        return true;
    }

    private boolean isCaptainDead() {
        return true;
    }
}
