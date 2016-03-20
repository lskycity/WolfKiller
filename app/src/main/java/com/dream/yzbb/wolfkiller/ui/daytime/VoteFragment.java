package com.dream.yzbb.wolfkiller.ui.daytime;

import android.widget.Toast;

/**
 * Created by kevinbest on 16/3/20.
 */
public class VoteFragment extends ActionDaytimeFragment {

    @Override
    protected String getPositiveActionText() {
        return "处决";
    }

    @Override
    protected void doPositiveAction() {
        //kill the selected player
        Toast.makeText(getActivity(), "投票结束", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String getActionDescription() {
        return "选择一个玩家并处决他";
    }
}
