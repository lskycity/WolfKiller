package com.dream.yzbb.wolfkiller.service.daytime_handler;

import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;

/**
 * Created by kevinbest on 16/3/4.
 */
public class DaytimeHandler {

    public String handleRequest(DaytimeRoundRecord roundRecord) {
        return null;
    }

    public DaytimeHandler() {
    }

    public String getActionDescription() {
        return null;
    }

    public boolean shouldConsiderGameOver() {
        return false;
    }

    public boolean shouldConsiderCaptain() {
        return false;
    }

    @Override
    public String toString() {
        return "I am "+getClass().getSimpleName();
    }
}
