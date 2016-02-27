package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Wolf extends NightRole {
    @Override
    public void doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return;
        }
        //Kill one player
        nightRoundRecord.setKilledPerson(targetPlayers[0]);
    }
}
