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
    private NightRoundManager nightRoundManager;

    public boolean initGame(RoleDistributionInfo distributionInfo)
    {
        //init players according to distribution info
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

    public void startGame()
    {
        nightRoundManager = new NightRoundManager(players);
    }

    public NightRoundManager getNightRoundManager() {
        return nightRoundManager;
    }

    public List<Player> getAllPlayers()
    {
        //copy a list of player to show information
        return players;
    }


}
