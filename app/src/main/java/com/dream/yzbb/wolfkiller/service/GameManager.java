package com.dream.yzbb.wolfkiller.service;

import android.util.Log;

import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;
import com.dream.yzbb.wolfkiller.entity.WereWolf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
public class GameManager {
    private List<Player> players;
    private Lovers lovers;
    private GameState gameState;
    private Player captain;
    private NightRoundManager nightRoundManager;
    private DaytimeRoundManager daytimeRoundManager;
    private boolean isNight;

    public Lovers getLovers() {
        return lovers;
    }

    public Player getCaptain() {
        return captain;
    }

    public boolean initGame(RoleDistributionInfo distributionInfo)

    {
        //init players according to distribution info
        initPlayers(distributionInfo.getPlayerCount());
        deliverRoles(distributionInfo.getRandomRoleList());
        return false;
    }

    private void initPlayers(int count) {
        players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Player player = new Player(i);
            players.add(player);
        }
    }

    private void deliverRoles(List<Role> roleList) {
        if (roleList.size() != players.size()) {
            throw new RuntimeException("");
        }
        for (int i = 0; i < roleList.size(); i++) {
            players.get(i).setRole(roleList.get(i));
        }
    }

    public void startGame() {
        nightRoundManager = new NightRoundManager(players);
        daytimeRoundManager = new DaytimeRoundManager();
        isNight = true;
    }

    public NightRoundManager endDayAndStartNight() {
        daytimeRoundManager.endDaytimeRound();
        nightRoundManager.startNightRound(lovers);
        isNight = true;
        return nightRoundManager;
    }

    public DaytimeRoundManager endNightAndStartDay() {
        NightRoundRecord record = nightRoundManager.endNightRound();
        if (record.getLovers() != null) {
            lovers = record.getLovers();
        }
        List<Player> deadPlayers = record.deadPlayers(lovers);
        for (Player p : deadPlayers) {
            p.setStatus(Player.Status.DEAD);
        }
        daytimeRoundManager.startDaytimeRound(lovers, captain);
        isNight = false;
        return daytimeRoundManager;
    }

    public NightRoundManager getNightRoundManager() {
        return nightRoundManager;
    }

    public DaytimeRoundManager getDaytimeRoundManager() {
        return daytimeRoundManager;
    }

    public List<Player> getAllPlayers() {
        //copy a list of player to show information
        return players;
    }

    public boolean isGameOver() {
        List<Player> alivePlayers = getAlivePlayers();
        if (isAllWolf(alivePlayers)) {
            return true;
        } else if (isAllGoodPeople(alivePlayers)) {
            return true;
        } else if (alivePlayers.size() == 2 && lovers.isLover(alivePlayers.get(0)) && lovers.isLover(alivePlayers.get(1))) {
            return true;
        }
        return false;
    }

    private List<Player> getAlivePlayers() {
        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        for (Player p : players) {
            if (p.getStatus() == Player.Status.ALIVE) {
                alivePlayers.add(p);
            }
        }
        return alivePlayers;
    }

    private boolean isAllWolf(List<Player> players) {
        if (players.size() == 0) {
            Log.d(Constants.LOG_TAG, "[isAllWolf], empty player list");
            return false;
        }
        for (Player p : players) {
            if (p.getRole().getClass() != WereWolf.class) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllGoodPeople(List<Player> players) {
        if (players.size() == 0) {
            Log.d(Constants.LOG_TAG, "[isAllGoodPeople], empty player list");
            return false;
        }
        for (Player p : players) {
            if (p.getRole().getClass() == WereWolf.class) {
                return false;
            }
        }
        return true;
    }

}
