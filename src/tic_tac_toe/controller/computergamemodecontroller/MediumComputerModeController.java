/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.controller.computergamemodecontroller;

import javafx.util.Pair;
import tic_tac_toe.model.ComputerMove;

/**
 *
 * @author 3wiida
 */
public class MediumComputerModeController implements ComputerMove{
    
    private boolean isRandomMove = true;
    private final EasyComputerModeController randomMove = new EasyComputerModeController();
    private final HardComputerModeController aiMove = new HardComputerModeController();
    
    @Override
    public Pair<Integer, Integer> move(char[][] board) {
        Pair<Integer, Integer> mediumMove;
        if(isRandomMove){
            mediumMove = randomMove.move(board);
        }else{
            mediumMove = aiMove.move(board);
        }
        isRandomMove = !isRandomMove;
        return mediumMove;
    }
    
}
