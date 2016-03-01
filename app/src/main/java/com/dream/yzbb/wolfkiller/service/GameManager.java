package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by kevinbest on 16/2/27.
 */
public class GameManager {
    private List<Player> players;
    private Lovers lovers;
    private GameState gameState;
    private Player captain;
    private int playerPos;

    public boolean initGame(RoleDistributionInfo distributionInfo)
    {
        //init players according to distribution info
        playerPos = 0;
        players = new ArrayList<>();
        HashMap<Role,Integer> map = distributionInfo.getDistribution();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Role role = (Role) entry.getKey();
            for (int i = 0 ; i < (int) entry.getValue() ; i++ ) {
                Player player = new Player();
                player.setRole(role);
                players.add(player);
            }
        }
        Collections.shuffle(players);
        return false;
    }

//    public boolean
    //set next player name and other info
    public void setNextPlayerInfo(String name)
    {
        if(playerPos >= 0 && playerPos < players.size())
        {
            playerPos++;
            players.get(playerPos).setName(name);
        }
    }
    // get next player to show or change information of the player in initial screen
    public Player getNextPlayer()
    {
        if(playerPos >= 0 && playerPos < players.size()-1)
        {
           return players.get(++playerPos);
        }
        return null;
    }

    public int getPlayerCount () {
        return players.size();
    }

    public void startGame()
    {

    }

    public List<Player> getAllPlayers()
    {
        //copy a list of player to show information
        return players;
    }


}
