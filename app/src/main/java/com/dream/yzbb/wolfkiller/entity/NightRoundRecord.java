package com.dream.yzbb.wolfkiller.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
//represent a round in the night, it records information in the night
public class NightRoundRecord {
    private final int number;

    private Player killedPerson;
    private Player savedPerson;
    private Player poisonedPerson;
    private Player guardedPerson;
    private Player inspectedPerson;

    private Lovers lovers;

    public NightRoundRecord(int number) {
        this.number = number;
    }


    public Player getInspectedPerson() {
        return inspectedPerson;
    }

    public void setInspectedPerson(Player inspectedPerson) {

        this.inspectedPerson = inspectedPerson;
    }

    public Player getGuardedPerson() {
        return guardedPerson;
    }

    public void setGuardedPerson(Player guardedPerson) {
        this.guardedPerson = guardedPerson;
    }

    public int getNumber() {
        return number;
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
    public List<Player> deadPlayers(Lovers lovers)
    {
        ArrayList<Player> players = new ArrayList<>(3);
        if(killedPerson != null && savedPerson!=null && guardedPerson!=killedPerson) {
            players.add(killedPerson);
            addSpouseIfNeed(killedPerson, lovers, players);
        }
        if(poisonedPerson!=null && !players.contains(poisonedPerson)) {
            players.add(poisonedPerson);
            addSpouseIfNeed(poisonedPerson, lovers, players);
        }
        return players;
    }

    private void addSpouseIfNeed(Player deadPlayer,Lovers lovers, ArrayList<Player> players) {
        Player player = lovers.getSpouse(deadPlayer);
        if(player!=null && !players.contains(player)) {
            players.add(player);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("round no = ").append(number).append('\n');
        if(killedPerson != null) {
            sb.append("killed Person = ").append(killedPerson).append('\n');
        }
        if(savedPerson != null) {
            sb.append("saved Person = ").append(savedPerson).append('\n');
        }
        if(poisonedPerson != null) {
            sb.append("poisonedPerson = ").append(poisonedPerson).append('\n');
        }
        if(guardedPerson != null) {
            sb.append("guardedPerson = ").append(guardedPerson).append('\n');
        }
        if(inspectedPerson != null) {
            sb.append("inspectedPerson = ").append(inspectedPerson).append('\n');
        }
        if(lovers != null) {
            sb.append("lovers = ").append(lovers).append('\n');
        }
      return sb.toString();
    }
}


