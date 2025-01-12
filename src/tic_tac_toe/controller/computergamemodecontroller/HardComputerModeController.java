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
public class HardComputerModeController implements ComputerMove {

    private final char computerPlayer = 'O';
    private final char humanPlayer = 'X';

    @Override
    public Pair<Integer, Integer> move(char[][] board) {
        int bestMoveRate = Integer.MIN_VALUE;
        Pair<Integer, Integer> result = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = computerPlayer;
                    int moveRate = miniMax(board, 0, false);
                    board[i][j] = '\0';
                    if (moveRate > bestMoveRate) {
                        result = new Pair<>(i, j);
                        bestMoveRate = moveRate;
                    }
                }
            }
        }

        return result;
    }

    private boolean hasMoreMoves(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return true;
            }
        }
        return false;
    }

    private int evaluateTerminatingState(char[][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '\0') {
                return board[row][0] == computerPlayer ? 10 : -10;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '\0') {
                return board[0][col] == computerPlayer ? 10 : -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '\0') {
            return board[0][0] == computerPlayer ? 10 : -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '\0') {
            return board[0][2] == computerPlayer ? 10 : -10;
        }

        return 0;
    }

    private int miniMax(char[][] board, int depth, boolean isMaxPlayer) {
        int stateValue = evaluateTerminatingState(board);

        if (stateValue == 10) return stateValue - depth;
        if (stateValue == -10) return stateValue + depth;

        if (!hasMoreMoves(board)) return 0;

        if (isMaxPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = computerPlayer;
                        bestScore = Math.max(bestScore, miniMax(board, depth + 1, false));
                        board[i][j] = '\0';
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = humanPlayer;
                        bestScore = Math.min(bestScore, miniMax(board, depth + 1, true));
                        board[i][j] = '\0';
                    }
                }
            }
            return bestScore;
        }
    }
}
