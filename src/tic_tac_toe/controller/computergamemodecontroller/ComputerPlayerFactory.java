/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.controller.computergamemodecontroller;

import tic_tac_toe.model.ComputerMove;
import tic_tac_toe.model.GameModeEnum;

/**
 *
 * @author 3wiida
 */
public class ComputerPlayerFactory {
   
    public static ComputerMove createCmputer(GameModeEnum gameMode){
        ComputerMove computer;
        switch(gameMode){
            case COMPUTER_EASY:{
                computer = new EasyComputerModeController();
                break;
            }
            
            case COMPUTER_MEDIUM :{
                computer = new MediumComputerModeController();
                break;
            }
            
            case COMPUTER_HARD : {
                computer = new HardComputerModeController();
                break;
            }
            
            default:{
                computer = new EasyComputerModeController();
                break;
            }
        }
        return computer;
    }
    
}
