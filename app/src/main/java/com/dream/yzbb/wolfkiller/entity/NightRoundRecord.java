package com.dream.yzbb.wolfkiller.entity;

import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
//represent a round in the night, it records information in the night
public class NightRoundRecord {
    private int number;

    private Player killedPerson;
    private Player savedPerson;
    private Player poisonedPerson;
    private Player guardedPerson;
    private Player inspectedPerson;

    public Player getInspectedPerson() {
        return inspectedPerson;
    }

    public void setInspectedPerson(Player inspectedPerson) {

        this.inspectedPerson = inspectedPerson;
    }

    private Lovers lovers;

    public Player getGuardedPerson() {
        return guardedPerson;
    }

    public void setGuardedPerson(Player guardedPerson) {
        this.guardedPerson = guardedPerson;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getKilledPerson() {
        return killedPerson;
    }

    public void setKilledPerson(Player killedPerson) {
        this.killedPerson = killedPerson;
    }

    public Player getSavedPerson() {
        return savedPerson;
    }

    public void setSavedPerson(Player savedPerson) {
        this.savedPerson = savedPerson;
    }

    public Player getPoisonedPerson() {
        return poisonedPerson;
    }

    public void setPoisonedPerson(Player poisonedPerson) {
        this.poisonedPerson = poisonedPerson;
    }

    public Lovers getLovers() {
        return lovers;
    }

    public void setLovers(Lovers lovers) {
        this.lovers = lovers;
    }


    // calcuate who is dead in this night round
    public static List<Player> deadPlayers(Lovers lovers)
    {
        return null;
    }
}


