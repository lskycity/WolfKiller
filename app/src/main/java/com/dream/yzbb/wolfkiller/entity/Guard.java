package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/3/1.
 */
public class Guard extends NightRole {
    @Override
    public Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return null;
        }
        //Guard a person
        nightRoundRecord.setGuardedPerson(targetPlayers[0]);
        return null;
    }

    @Override
    public String getNegativeActionString() {
        return "放弃";
    }

    @Override
    public String getPositiveActionString() {
        return "守卫";
    }
}
