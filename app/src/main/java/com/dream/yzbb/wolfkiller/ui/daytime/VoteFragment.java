package com.dream.yzbb.wolfkiller.ui.daytime;

import android.util.Log;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.Player;

/**
 * Created by kevinbest on 16/3/20.
 */
public class VoteFragment extends ActionDaytimeFragment {

    @Override
    protected String getPositiveActionText() {
        return "处决";
    }

    @Override
    protected boolean doPositiveAction() {
        //kill the selected player
        if(mDaytimeListener.getSelectedPlayers().length!=getActionTargetNumber())
        {
            Toast.makeText(getActivity(), "请选择需要处决的人!!!", Toast.LENGTH_SHORT).show();
            Log.d(Constants.LOG_TAG, "未选择要处决的人,选中的人数为" + mDaytimeListener.getSelectedPlayers().length + "个");
            return false;
        }
        else
        {
            // kill person
            mDaytimeListener.getSelectedPlayers()[0].setStatus(Player.Status.DEAD);
            Log.d(Constants.LOG_TAG, "投死的是" + mDaytimeListener.getSelectedPlayers()[0].getPlayID() + "号");
            return true;
        }
    }

    @Override
    protected boolean doNegativeAction() {
        super.doNegativeAction();
        Toast.makeText(getActivity(), "放弃投票处决", Toast.LENGTH_SHORT).show();
        Log.d(Constants.LOG_TAG, "放弃投票处决");
        return true;
    }

    @Override
    protected String getActionDescription() {
        return "选择一个玩家并处决他";
    }
}
