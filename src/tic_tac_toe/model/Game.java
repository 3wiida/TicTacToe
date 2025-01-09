/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic_tac_toe.model;

/**
 *
 * @author omarabdelaziz
 */
public class Game {
    private char [][] board;
    private char currentPlayer;
    private boolean gameOver;
    private int player1Score;
    private int player2Score;
    private int gameCounter;
    private WinningLaneEnum winningLane;
    
    public Game() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        gameCounter = 0;
        player1Score = 0;
        player2Score = 0;
    }

    public WinningLaneEnum getWinningLane() {
        return winningLane;
    }
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    public int getGameCounter() {
        return gameCounter;
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
    
    
    
    public boolean makeMove(int row, int col) {
        if (board[row][col] == '\0' && !gameOver) {
            gameCounter +=1;
            board[row][col] = currentPlayer;
            if (checkWin(row, col)) {
                gameOver = true;
                if (currentPlayer == 'X') {
                    player1Score++;
                } else{
                    player2Score++;
                }
            } else{
                switchPlayer();
            }
            return true;
        }
        return false;
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    private boolean checkWin(int row, int col) {
        return checkRow(row) || checkColumn(col) || checkFirstDiagonal() || checkSecondDiagonal();
    }
    
    private boolean checkRow(int row) {
        boolean firstPlace = board[row][0] == currentPlayer;
        boolean secondPlace = board[row][1] == currentPlayer;
        boolean thirdPlace = board[row][2] == currentPlayer;
        if (firstPlace && secondPlace && thirdPlace){
            switch (row) {
                case 0 : winningLane = WinningLaneEnum.first_row;
                case 1 : winningLane = WinningLaneEnum.second_row;
                case 2 : winningLane = WinningLaneEnum.third_row;
            }
        }
        return firstPlace && secondPlace && thirdPlace;
    }
    
    private boolean checkColumn(int col) {
        boolean firstPlace = board[0][col] == currentPlayer;
        boolean secondPlace = board[1][col] == currentPlayer;
        boolean thirdPlace = board[2][col] == currentPlayer;
        if (firstPlace && secondPlace && thirdPlace){
            switch (col) {
                case 0 : winningLane = WinningLaneEnum.first_column;
                case 1 : winningLane = WinningLaneEnum.second_column;
                case 2 : winningLane = WinningLaneEnum.third_column;
            }
        }
        return firstPlace && secondPlace && thirdPlace;

    }
    
    private boolean checkFirstDiagonal() {
        boolean firstPlace = board[0][0] == currentPlayer;
        boolean secondPlace = board[1][1] == currentPlayer;
        boolean thirdPlace = board[2][2] == currentPlayer;
        if (firstPlace && secondPlace && thirdPlace){
            winningLane = WinningLaneEnum.first_diagonal;
        }
        return firstPlace && secondPlace && thirdPlace;
    }
    
    private boolean checkSecondDiagonal(){
        boolean firstPlace = board[0][2] == currentPlayer;
        boolean secondPlace = board[1][1] == currentPlayer;
        boolean thirdPlace = board[2][0] == currentPlayer;
        if (firstPlace && secondPlace && thirdPlace){
            winningLane = WinningLaneEnum.second_diagonal;
        }
        return firstPlace && secondPlace && thirdPlace;
    }
    
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
        currentPlayer = 'X';
        gameOver = false;
        gameCounter = 0;
    }
}

