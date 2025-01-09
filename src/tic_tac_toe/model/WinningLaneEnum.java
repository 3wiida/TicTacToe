/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic_tac_toe.model;

/**
 *
 * @author omarabdelaziz
 */
public enum WinningLaneEnum{
    first_column(0),
    second_column(1),
    third_column(2),
    first_row(0),
    second_row(1),
    third_row(2),
    first_diagonal(0),
    second_diagonal(1);
    
    private int code;
    private WinningLaneEnum(int code) {
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
