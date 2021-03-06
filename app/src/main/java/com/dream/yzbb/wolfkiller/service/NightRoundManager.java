package com.dream.yzbb.wolfkiller.service;

import android.os.Bundle;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.entity.Jupiter;
import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.Witch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by kevinbest on 16/2/27.
 */
//responsible for states transition in the night round
public class NightRoundManager {
    private List<NightRole> nightRoles;
    private int currentRoleIndex;
    private LinkedList<NightRoundRecord> nightRecords;
    private Lovers lovers;

    public NightRoundManager(List<Player> players) {
        nightRoles = new ArrayList<>(players.size());
        currentRoleIndex = 0;
        nightRecords = new LinkedList<>();
        fillNightRole(players);
    }

    private void fillNightRole(List<Player> players) {
        for (Player player : players) {
            Role role = player.getRole();
            if (role instanceof NightRole && !nightRoles.contains(role)) {
                nightRoles.add((NightRole) role);
            }
        }
        //TODO: Implement comparable interface
        Collections.sort(nightRoles);
    }

    public void startNightRound(Lovers lovers) {
        //Init roles appearing in the night round
        this.lovers = lovers;
        NightRoundRecord record = new NightRoundRecord(nightRecords.size());
        nightRecords.add(record);
    }

    public NightRole nextRole() {
        return nightRoles.get(currentRoleIndex++);
    }

    public boolean hasNext() {
        return currentRoleIndex < nightRoles.size();
    }

    public Bundle doAction(NightRole role, Player... players) {
        NightRoundRecord roundRecord = nightRecords.getLast();
        return role.doAction(roundRecord, players);
    }

    public NightRoundRecord endNightRound() {
        //return latest night round record
        currentRoleIndex = 0;
        if (nightRoles.get(0) instanceof Jupiter) {
            nightRoles.remove(0);
        }

        //update players' status
        return nightRecords.getLast();
    }

    public NightRoundRecord latestNightRoundRecord() {
        NightRoundRecord latestNightRecord = nightRecords.getLast();
        return latestNightRecord;
    }

    public List<Player> getDeadPlayers() {
        return latestNightRoundRecord().deadPlayers(lovers);
    }

    //TODO: update players' status
}
