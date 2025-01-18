/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.model;

/**
 *
 * @author 3wiida
 */
public class Player {
    private String username;
    private int score;
    private StatusEnum status;
    private GenderEnum gender;
    public static boolean isOnline = false;
    
    public Player(String username) {
        this.username = username;
    }

    public Player(String username, int score, StatusEnum status) {
        this.username = username;
        this.score = score;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    
    
}
