package com.dream.yzbb.wolfkiller.service.daytime_handler;

import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;

/**
 * Created by kevinbest on 16/3/4.
 */
public class DaytimeHandler {

    protected boolean mGameOver;
    protected boolean mChangeCapatain;
    public String handleRequest(DaytimeRoundRecord roundRecord)
    {
        return null;
    }

    public DaytimeHandler() {
        setGameOver(false);
        setmChangeCapatain(false);
    }

    public String getActionDescription() {
        return null;
    }

    public boolean isGameOver()
    {
        return mGameOver;
    }

    protected void setGameOver(boolean gameOver)
    {
        mGameOver = gameOver;
    }

    protected void setmChangeCapatain(boolean changeCapatain)
    {
        mChangeCapatain = changeCapatain;
    }

    public boolean shouldChangeCapatain() {
        return mChangeCapatain;
    }
}
