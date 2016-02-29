package com.dream.yzbb.wolfkiller.service;

import com.dream.yzbb.wolfkiller.entity.Lovers;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
public class GameManager {
    private List<Player> players;
    private Lovers lovers;
    private GameState gameState;
    private Player captain;

    public boolean initGame(RoleDistributionInfo distributionInfo)
    {
        //init players according to distribution info
        return false;
    }

//    public boolean
    // get next player to show or change information of the player in initial screen
    public Player getNextPlayer()
    {
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
        return null;
    }

}
