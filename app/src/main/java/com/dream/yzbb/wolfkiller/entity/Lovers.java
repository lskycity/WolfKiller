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

    @Override
    public String toString() {
        return "Lovers are "+lover1.getPlayID()+" and "+ lover2.getPlayID();
    }
}
