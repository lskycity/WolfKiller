package com.dream.yzbb.wolfkiller.service.daytime_handler;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;

import java.util.List;

/**
 * Created by kevinbest on 16/3/6.
 */
public class PublishNightResultHandler extends DaytimeHandler {
    @Override
    public String handleRequest(DaytimeRoundRecord roundRecord) {
        NightRoundRecord record = Factory.get().getGameManager().getNightRoundManager().latestNightRoundRecord();
        //should update game status
        return getNightResult(record);
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

    @Override
    public boolean shouldConsiderCaptain() {
        return true;
    }

    @Override
    public boolean shouldConsiderGameOver() {
        return true;
    }
}
