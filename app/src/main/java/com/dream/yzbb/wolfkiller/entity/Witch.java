package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Witch extends NightRole {
    private int actionIndex = 0;
    private boolean isSaved = false;
    private boolean isPoisoned = false;

    @Override
    public Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
        switch (actionIndex) {
            case 0:
                //save people
                if (targetPlayers.length == getActionTargetNumber() && !isSaved) {
                    nightRoundRecord.setSavedPerson(nightRoundRecord.getKilledPerson());
                    isSaved = true;
                }
                break;
            case 1:
                //poison people
                if (targetPlayers.length == getActionTargetNumber() && !isPoisoned) {
                    nightRoundRecord.setPoisonedPerson(targetPlayers[0]);
                    isPoisoned = true;
                }
                break;
        }
        return null;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void nextAction() {
        actionIndex++;
        if(actionIndex>1) {
            actionIndex = 0;
        }
    }

    @Override
    public int getActionTargetNumber() {
        return actionIndex==0 ? 0 : 1;
    }

    @Override
    public int getActionTimes() {
        return 2;
    }
}
