package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.Role;

import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
//responsible for states transition in the night round
public class NightRoundManager {
    private List<Role> nightRoles;
    private int currentNightRoundNumber = 0;
    private int currentRoleIndex;

    public void startNightRound() {
        //Init roles appearing in the night round
        currentNightRoundNumber++;
    }

    public boolean nextRole() {
        //transit role
        return true;
    }
}
