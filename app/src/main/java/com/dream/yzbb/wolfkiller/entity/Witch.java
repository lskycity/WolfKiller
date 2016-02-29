package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/27.
 */
public class Witch extends NightRole {
    private int actionIndex = 0;

    @Override
    public void doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers) {
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
    }

    @Override
    public int getActionTimes() {
        return 2;
    }
}
