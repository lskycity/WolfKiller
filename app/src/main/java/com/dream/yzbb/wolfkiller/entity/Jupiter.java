package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Jupiter extends NightRole {
    @Override
    public Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return null;
        }

        nightRoundRecord.setLovers(new Lovers(targetPlayers[0], targetPlayers[1]));
        return null;
    }

    @Override
    public int getActionTargetNumber() {
        return 2;
    }
}
