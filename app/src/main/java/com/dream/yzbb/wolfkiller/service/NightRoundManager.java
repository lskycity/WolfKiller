package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Role;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
//responsible for states transition in the night round
public class NightRoundManager {
    private List<Role> nightRoles;
    private int currentRoleIndex;
    private LinkedList<NightRoundRecord> nightRecords;

    public void startNightRound() {
        //Init roles appearing in the night round
        NightRoundRecord record = new NightRoundRecord();
        nightRecords.add(record);
    }

    public boolean nextRole() {
        //transit role
        return true;
    }

    public NightRoundRecord endNightRound() {
        //return latest nigh round record
        return nightRecords.getLast();
    }
}
