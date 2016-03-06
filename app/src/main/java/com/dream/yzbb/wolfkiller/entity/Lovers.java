package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/24.
 */
public class Lovers {
    private Player lover1;
    private Player lover2;

    public Lovers(Player lover1, Player lover2) {
        this.lover1 = lover1;
        this.lover2 = lover2;
    }

    public boolean isLover(Player player) {
        return player == lover1 || player == lover2;
    }

    public Player getSpouse(Player player) {
        if(player == lover1) {
            return lover2;
        } else if(player == lover2) {
            return lover1;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Lovers are "+lover1.getPlayID()+" and "+ lover2.getPlayID();
    }
}
