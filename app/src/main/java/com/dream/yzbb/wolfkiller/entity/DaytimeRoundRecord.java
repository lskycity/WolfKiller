package com.dream.yzbb.wolfkiller.entity;

import com.dream.yzbb.wolfkiller.service.DaytimeRoundManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
public class DaytimeRoundRecord {
    private int number;
    private Player votedPerson;
    private Player captain;

    public DaytimeRoundRecord(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getVotedPerson() {
        return votedPerson;
    }

    public void setVotedPerson(Player votedPerson) {
        this.votedPerson = votedPerson;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

}
