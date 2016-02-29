package com.dream.yzbb.wolfkiller.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by kevinbest on 16/2/27.
 */
public class RoleDistributionInfo {
    private int playerCount;
    private HashMap<Role, Integer> distribution;

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public HashMap<Role, Integer> getDistribution() {
        return distribution;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total player count: " + playerCount + "\n");
        builder.append("Role size is "+distribution.size()+"\n");
        for (Iterator<Map.Entry<Role, Integer>> it = distribution.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Role, Integer> entry = it.next();
            builder.append("Role name: " + ((Role) entry.getKey()).getName() + ", Role count: " + entry.getValue() + "\n");
        }
        return builder.toString();
    }

    public void setDistribution(HashMap<Role, Integer> distribution) {
        this.distribution = distribution;
    }
}
