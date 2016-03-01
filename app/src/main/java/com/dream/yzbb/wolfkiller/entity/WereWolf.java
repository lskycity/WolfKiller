package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/2/27.
 */
public class WereWolf extends NightRole {
    @Override
    public Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return null;
        }
        //Kill one player
        nightRoundRecord.setKilledPerson(targetPlayers[0]);
        return null;
    }
}
