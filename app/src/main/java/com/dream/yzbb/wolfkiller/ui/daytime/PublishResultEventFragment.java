package com.dream.yzbb.wolfkiller.ui.daytime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.service.NightRoundManager;

import java.util.List;

/**
 * Created by kevinbest on 16/3/17.
 */
public class PublishResultEventFragment extends DaytimeFragment {
    @Override
    protected String getActionDescription() {

        NightRoundManager nightManager = Factory.get().getGameManager().getNightRoundManager();
        String nightResult = getNightResult(nightManager.latestNightRoundRecord());
        return nightResult == null ? "昨天晚上没有人死亡" : nightResult;
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
}
