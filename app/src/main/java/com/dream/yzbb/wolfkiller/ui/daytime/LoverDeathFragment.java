package com.dream.yzbb.wolfkiller.ui.daytime;

import android.util.Log;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager;

/**
 * Created by kevinbest on 16/3/20.
 */
public class LoverDeathFragment extends DaytimeFragment {
    @Override
    protected String getActionDescription() {
        return "情侣死亡";
    }

    @Override
    protected void init() {
        super.init();
        DaytimeRoundManager daytimeRoundManager = Factory.get().getGameManager().getDaytimeRoundManager();
        DaytimeRoundRecord latestRecord = daytimeRoundManager.latestDaytimeRoundRecord();
        Player lover1 = latestRecord.getVotedPerson();
        //set another lover to death
        Player lover2 = Factory.get().getGameManager().getLovers().getSpouse(lover1);
        lover2.setStatus(Player.Status.DEAD);
        Log.d(Constants.LOG_TAG, "Set status of lover " + lover2.getPlayID() + "号 to DEAD");
        Toast.makeText(getActivity(),"Set status of lover " + lover2.getPlayID() + "号 to DEAD",Toast.LENGTH_SHORT).show();
    }
}
