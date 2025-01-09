/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.model;

import javafx.util.Pair;

/**
 *
 * @author 3wiida
 */
public interface ComputerMove {
    default public Pair<Integer,Integer> move(char[][] board){
        return null;
    }
    
    default public Pair<Integer,Integer> move(char[][] board,int depth, boolean isMaxPlayer){
        return null;
    }
}
