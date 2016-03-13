package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.service.daytime_handler.DaytimeHandler;
import com.dream.yzbb.wolfkiller.service.daytime_handler.DaytimeSpeechHandler;
import com.dream.yzbb.wolfkiller.service.daytime_handler.PublishNightResultHandler;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kevinbest on 16/2/27.
 */
public class DaytimeRoundManager {
    private int dayRoundCount;
    private int currentActionIndex;

    private LinkedList<DaytimeRoundRecord> daytimeRoundRecords;
    private ArrayList<DaytimeHandler> daytimeEventHandlers;
    private Lovers lovers;

    public DaytimeRoundManager() {
        dayRoundCount = 0;
        this.daytimeRoundRecords = new LinkedList<DaytimeRoundRecord>();
    }

    public DaytimeRoundRecord startDaytimeRound(Lovers lovers) {
        dayRoundCount++;
        currentActionIndex = 0;
        this.lovers = lovers;
        DaytimeRoundRecord daytimeRoundRecord = new DaytimeRoundRecord(dayRoundCount);
        daytimeRoundRecords.add(daytimeRoundRecord);
        initDaytimeEventHandlers();
        return daytimeRoundRecord;
    }

    private void initDaytimeEventHandlers() {
        daytimeEventHandlers = new ArrayList<DaytimeHandler>();
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

    public DaytimeRoundRecord latestDaytimeRoundRecord() {
        return daytimeRoundRecords.getLast();
    }
}
