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
    public Pair<Integer,Integer> move(char[][] board);
}
