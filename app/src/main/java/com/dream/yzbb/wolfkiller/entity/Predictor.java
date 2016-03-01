package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/3/1.
 */
public class Predictor extends NightRole {
    @Override
    public String getPositiveActionString() {
        return "验人";
    }

    @Override
    public String getNegativeActionString() {
        return "放弃";
    }

    @Override
    public void doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return;
        }
        //验人
        nightRoundRecord.setInspectedPerson(targetPlayers[0]);
    }
}
