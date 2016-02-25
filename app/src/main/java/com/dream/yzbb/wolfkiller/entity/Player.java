package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/24.
 */
public class Player {
    private int playID;
    private Role role;
    private String name;
    private Status status;

    public void setPlayID(int playID) {
        this.playID = playID;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {

        return status;
    }

    public int getPlayID() {
        return playID;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public enum Status {ALIVE, DEAD}
}
