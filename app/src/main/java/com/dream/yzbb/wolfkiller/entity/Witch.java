package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Witch extends NightRole {
    private int actionIndex = 0;

    @Override
    public Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        switch (actionIndex) {
            case 0:
                //save people
                if (targetPlayers.length == getActionTargetNumber()) {
                    nightRoundRecord.setSavedPerson(targetPlayers[0]);
                }
                actionIndex++;
                break;
            case 1:
                //poison people
                if (targetPlayers.length == getActionTargetNumber()) {
                    nightRoundRecord.setPoisonedPerson(targetPlayers[0]);
                }
                actionIndex = 0;
                break;
        }
        return null;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    @Override
    public int getActionTimes() {
        return 2;
    }
}
