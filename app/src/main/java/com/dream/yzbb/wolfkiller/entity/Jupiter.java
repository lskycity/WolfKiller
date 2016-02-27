package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Jupiter extends NightRole {
    @Override
    public void doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return;
        }

        nightRoundRecord.setLovers(new Lovers(targetPlayers[0], targetPlayers[1]));
    }

    @Override
    public int getActionTargetNumber() {
        return 2;
    }
}
