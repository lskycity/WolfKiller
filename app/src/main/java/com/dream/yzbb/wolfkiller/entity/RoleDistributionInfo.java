package com.dream.yzbb.wolfkiller.entity;

import java.util.HashMap;

/**
 * Created by kevinbest on 16/2/27.
 */
public class RoleDistributionInfo {
    private int playerCount;
    private HashMap<Role,Integer> distribution;

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public HashMap<Role, Integer> getDistribution() {
        return distribution;
    }

    public void setDistribution(HashMap<Role, Integer> distribution) {
        this.distribution = distribution;
    }
}
