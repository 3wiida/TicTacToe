/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.common;

import tic_tac_toe.model.Player;
import tic_tac_toe.model.StatusEnum;

/**
 *
 * @author 3wiida
 */
public class CurrentPlayer {
    private static Player player;
    
    public static void initPlayer(String id, String username, int score, StatusEnum status){
        if(player == null){
            player = new Player(id, username,score,status);
        }
    }
    
    public static Player getPlayer(){
        return player;
    }
}
