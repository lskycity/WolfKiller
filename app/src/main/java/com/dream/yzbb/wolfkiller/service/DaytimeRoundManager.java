package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.service.daytime_handler.DaytimeHandler;
import com.dream.yzbb.wolfkiller.service.daytime_handler.DaytimeSpeechHandler;
import com.dream.yzbb.wolfkiller.service.daytime_handler.PublishNightResultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
public class DaytimeRoundManager {
    private int dayRoundCount;
    private int currentActionIndex;

    private LinkedList<DaytimeRoundRecord> daytimeRoundRecords;
    private LinkedList<DaytimeHandler> daytimeEventHandlers;

    public DaytimeRoundManager() {
        dayRoundCount = 0;
        this.daytimeRoundRecords = new LinkedList<DaytimeRoundRecord>();
    }

    public DaytimeRoundRecord startDaytimeRound() {
        dayRoundCount++;
        currentActionIndex = 0;
        DaytimeRoundRecord daytimeRoundRecord = new DaytimeRoundRecord(dayRoundCount);
        daytimeRoundRecords.add(daytimeRoundRecord);
        initDaytimeEventHandlers();
        return daytimeRoundRecord;
    }

    private void initDaytimeEventHandlers() {
        daytimeEventHandlers.add(new PublishNightResultHandler());
        daytimeEventHandlers.add(new DaytimeSpeechHandler());
    }

    public boolean hasNextAction() {
        return currentActionIndex < daytimeEventHandlers.size();
    }

    /**
     * Return next DaytimeHandler
     *
     * @return nextDaytimeHandler
     */
    public DaytimeHandler nextDaytimeHandler() {
        return daytimeEventHandlers.get(currentActionIndex++);
    }

    public void endDaytimeRound() {
        currentActionIndex = 0;
    }
}
