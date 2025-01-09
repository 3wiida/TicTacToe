/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.controller.computergamemodecontroller;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;
import tic_tac_toe.model.ComputerMove;

/**
 *
 * @author 3wiida
 */
public class EasyComputerModeController implements ComputerMove{

    @Override
    public Pair<Integer, Integer> move(char[][] board) {
        ArrayList<Pair<Integer,Integer>> availablePlaces = getAvailablePlaces(board);
        int randomIndex = new Random().nextInt(availablePlaces.size());
        return availablePlaces.get(randomIndex);
    }
    
    private ArrayList<Pair<Integer,Integer>> getAvailablePlaces(char[][] board){
        ArrayList<Pair<Integer,Integer>> availablePlaces = new ArrayList<>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == '\0'){
                    availablePlaces.add(new Pair<>(i,j));
                }
            }
        }
        return availablePlaces;
    }
    
}
