package com.dream.yzbb.wolfkiller.service;

import android.util.Log;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.DaytimeRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.Player;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by kevinbest on 16/2/27.
 */
public class DaytimeRoundManager {
    private int dayRoundCount;
    private int currentActionIndex;
    private static final DaytimeEvent[] DAYTIME_EVENTS_SEQUENCE = {DaytimeEvent.PUBLISH_DEATH, DaytimeEvent.GAME_STATUS, DaytimeEvent.CAPTAIN_DEATH, DaytimeEvent.SPEECH, DaytimeEvent.VOTE, DaytimeEvent.LOVER_DEATH, DaytimeEvent.CAPTAIN_DEATH, DaytimeEvent.GAME_STATUS};

    private LinkedList<DaytimeRoundRecord> daytimeRoundRecords;
    private Lovers lovers;
    private LinkedList<DaytimeEvent> daytimeEvents;


    public enum DaytimeEvent {PUBLISH_DEATH, GAME_STATUS, CAPTAIN_DEATH, SPEECH, VOTE, LOVER_DEATH}


    public DaytimeRoundManager() {
        dayRoundCount = 0;
        this.daytimeRoundRecords = new LinkedList<DaytimeRoundRecord>();
    }

    public DaytimeRoundRecord startDaytimeRound(Lovers lovers, Player captain) {
        dayRoundCount++;
        currentActionIndex = 0;
        this.lovers = lovers;
        DaytimeRoundRecord daytimeRoundRecord = new DaytimeRoundRecord(dayRoundCount);
        daytimeRoundRecord.setCaptain(captain);
        daytimeRoundRecords.add(daytimeRoundRecord);
        initDaytimeEvents();
        return daytimeRoundRecord;
    }

    private void initDaytimeEvents() {
        daytimeEvents = new LinkedList<DaytimeEvent>(Arrays.asList(DAYTIME_EVENTS_SEQUENCE));
    }

    public boolean hasNextEvent() {
        return !daytimeEvents.isEmpty();
    }

    public DaytimeEvent nextDaytimeEvent() {
        DaytimeEvent event = daytimeEvents.removeFirst();
        //remove event according to daytimeRecord status
        Log.i(Constants.LOG_TAG, "[nextDaytimeEvent] is called");
        if (event != DaytimeEvent.CAPTAIN_DEATH && event != DaytimeEvent.LOVER_DEATH) {
            return event;
        } else if (event == DaytimeEvent.LOVER_DEATH) {
            if (isLoverDead()) {
                Log.i(Constants.LOG_TAG, "[nextDaytimeEvent], lover is dead.");
                return event;
            } else {
                Log.i(Constants.LOG_TAG, "[nextDaytimeEvent], lover is not dead.");
                return nextDaytimeEvent();
            }
        } else {
            //CAPTAIN_DEATH EVENT
            if (isCaptainDead()) {
                Log.i(Constants.LOG_TAG, "[nextDaytimeEvent], captain is dead.");
                return event;
            }
            Log.i(Constants.LOG_TAG, "[nextDaytimeEvent], captain is not dead.");
            return nextDaytimeEvent();
        }
    }

    private boolean isLoverDead() {
        DaytimeRoundRecord record = latestDaytimeRoundRecord();
        Log.i(Constants.LOG_TAG,"[isLoverDead], voted person is No."+record.getVotedPerson()+"");
        return lovers.isLover(record.getVotedPerson());
    }

    private boolean isCaptainDead() {
        Player currentCaptain = latestDaytimeRoundRecord().getCaptain();
        return currentCaptain == null || currentCaptain.getStatus() == Player.Status.DEAD;
    }

    public void endDaytimeRound() {
        currentActionIndex = 0;
    }

    public DaytimeRoundRecord latestDaytimeRoundRecord() {
        return daytimeRoundRecords.getLast();
    }
}
