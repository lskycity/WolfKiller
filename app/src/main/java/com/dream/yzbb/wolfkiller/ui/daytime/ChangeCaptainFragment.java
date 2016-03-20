package com.dream.yzbb.wolfkiller.ui.daytime;

import android.util.Log;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.apputils.Constants;

/**
 * Created by kevinbest on 16/3/20.
 */
public class ChangeCaptainFragment extends ActionDaytimeFragment {

    @Override
    protected String getPositiveActionText() {
        return "更换";
    }

    @Override
    protected String getActionDescription() {
        return "请重新选择警长";
    }

    @Override
    protected boolean doPositiveAction() {
        if (mDaytimeListener.getSelectedPlayers().length != getActionTargetNumber()) {
            Toast.makeText(getActivity(), "请选择新警长", Toast.LENGTH_SHORT).show();
            Log.d(Constants.LOG_TAG, "未选择新警长,选中的人数为" + mDaytimeListener.getSelectedPlayers().length + "个");
            return false;
        } else {
            //Set Global Captain
            Factory.get().getGameManager().setCaptain(mDaytimeListener.getSelectedPlayers()[0]);
            //Set Daytime Captain
            Factory.get().getGameManager().getDaytimeRoundManager().latestDaytimeRoundRecord().setCaptain(mDaytimeListener.getSelectedPlayers()[0]);
            Toast.makeText(getActivity(), "新警长是" + mDaytimeListener.getSelectedPlayers()[0].getPlayID() + "号", Toast.LENGTH_SHORT).show();
            Log.d(Constants.LOG_TAG,"新警长是"+mDaytimeListener.getSelectedPlayers()[0].getPlayID()+"号");
            return true;
        }
    }

    @Override
    protected boolean doNegativeAction() {
        Toast.makeText(getActivity(), "放弃更换警长", Toast.LENGTH_SHORT).show();
        Log.d(Constants.LOG_TAG, "放弃更换警长");
        return true;
    }
}
