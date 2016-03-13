package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.NightRoundRecord;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

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
        for(int i=0; i<count; i++) {
            Player player = new Player(i);
            players.add(player);
        }
    }

    private void deliverRoles(List<Role> roleList) {
        if(roleList.size() != players.size()) {
            throw new RuntimeException("");
        }
        for(int i=0; i<roleList.size(); i++) {
            players.get(i).setRole(roleList.get(i));
        }
    }

    public void startGame()
    {
        nightRoundManager = new NightRoundManager(players);
        daytimeRoundManager = new DaytimeRoundManager();
        nightRoundManager.startNightRound();
        isNight = true;
    }

    public NightRoundManager endDayAndStartNight() {
        daytimeRoundManager.endDaytimeRound();
        nightRoundManager.startNightRound();
        isNight = true;
        return nightRoundManager;
    }

    public DaytimeRoundManager endNightAndStartDay() {
        NightRoundRecord record = nightRoundManager.endNightRound();
        if(record.getLovers()!=null) {
            lovers = record.getLovers();
        }
        List<Player> deadPlayers = record.deadPlayers(lovers);
        for(Player p:deadPlayers) {
            p.setStatus(Player.Status.DEAD);
        }
        daytimeRoundManager.startDaytimeRound(lovers, captain);
        isNight = false;
        return daytimeRoundManager;
    }

    public NightRoundManager getNightRoundManager() {
        return nightRoundManager;
    }

    public DaytimeRoundManager getDaytimeRoundManager() {return daytimeRoundManager;}

    public List<Player> getAllPlayers()
    {
        //copy a list of player to show information
        return players;
    }


}
