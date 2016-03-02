package com.dream.yzbb.wolfkiller.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    private List<Role> getRandomRoleList() {
        ArrayList<Role> list = new ArrayList<>(playerCount);
        for(Map.Entry<Role, Integer> entry : distribution.entrySet()) {
            addRoleToList(list, entry.getKey(), entry.getValue());
        }
        if(playerCount != list.size()) {
            throw new RuntimeException("playerCount should be same with distribution size playerCount="+playerCount+", distribution="+list.size());
        }
        Collections.shuffle(list);
        return list;
    }

    private void addRoleToList(ArrayList<Role> list, Role role, final int count) {
        for(int i=0; i<count; i++) {
            list.add(role);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total player count: ").append(playerCount).append("\n");
        builder.append("Role size is ").append(distribution.size()).append("\n");
        for (Map.Entry<Role, Integer> entry : distribution.entrySet()) {
            builder.append("Role name: ").append(entry.getKey().getName()).append(", Role count: ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }

    public void setDistribution(HashMap<Role, Integer> distribution) {
        this.distribution = distribution;
    }
}
