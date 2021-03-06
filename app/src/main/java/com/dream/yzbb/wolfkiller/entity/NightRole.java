package com.dream.yzbb.wolfkiller.entity;

import android.os.Bundle;

/**
 * Created by kevinbest on 16/2/27.
 */
public abstract class NightRole extends Role{

    public int getActionTimes()
    {
        return 1;
    }

    public int getActionTargetNumber()
    {
        return 1;
    }

    public String getActionDescription()
    {
        return "";
    }

    public String getPositiveActionString()
    {
        return "Yes";
    }

    public String getNegativeActionString()
    {
        return "No";
    }

    public abstract Bundle doAction(NightRoundRecord nightRoundRecord, Player... targetPlayers);
}

