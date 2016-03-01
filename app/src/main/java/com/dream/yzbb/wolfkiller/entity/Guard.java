package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/3/1.
 */
public class Guard extends NightRole {
    @Override
    public void doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        if (targetPlayers == null || targetPlayers.length != getActionTargetNumber()) {
            return;
        }
        //Guard a person
        nightRoundRecord.setGuardedPerson(targetPlayers[0]);
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
