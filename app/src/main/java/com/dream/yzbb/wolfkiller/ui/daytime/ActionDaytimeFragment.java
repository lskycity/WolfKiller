package com.dream.yzbb.wolfkiller.ui.daytime;


import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ActionDaytimeFragment extends DaytimeFragment implements View.OnClickListener {

    private DaytimeListener mDaytimeListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof DaytimeListener) {
            mDaytimeListener = (DaytimeListener) getActivity();
        } else {
            throw new RuntimeException("Activity containing ActionDaytimeFragment should implement DaytimeListener");
        }
    }

    public ActionDaytimeFragment() {
        // Required empty public constructor
    }

    public interface DaytimeListener {
        void onTargetPlayerDecided(int count);

        Player[] getSelectedPlayers();

        void daytimeActionFinished();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDaytimeListener.onTargetPlayerDecided(getActionTargetNumber());
    }

    protected abstract String getPositiveActionText();

    protected String getNegativeActionText() {
        return getString(R.string.negative);
    }

    //Action Target Player's number
    protected int getActionTargetNumber() {
        return 1;
    }

    protected abstract void doPositiveAction();

    protected void doNegativeAction() {
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        Button button = (Button) rootView.findViewById(R.id.next_event_btn);
        button.setVisibility(View.GONE);
        View actionLayout = rootView.findViewById(R.id.action_layout);
        actionLayout.setVisibility(View.VISIBLE);
        Button positiveBtn = (Button) rootView.findViewById(R.id.positive_btn);
        positiveBtn.setText(getPositiveActionText());
        positiveBtn.setOnClickListener(this);
        Button negativeBtn = (Button) rootView.findViewById(R.id.negative_btn);
        negativeBtn.setText(getNegativeActionText());
        negativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.positive_btn:
                doPositiveAction();
                mDaytimeListener.daytimeActionFinished();
                break;
            case R.id.negative_btn:
                doNegativeAction();
                mDaytimeListener.daytimeActionFinished();
                break;
        }
    }
}
