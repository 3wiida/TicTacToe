/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.model;

/**
 *
 * @author OmarAbdulaziz
 */

import tic_tac_toe.model.GameMove;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameRecorder {

    private List<GameMove> recordedMoves;
    private String playerOneName;
    private String playerTwoName;

    public GameRecorder() {
        recordedMoves = new ArrayList<>();
    }
    
    public boolean isEmpty(){
        return recordedMoves.isEmpty();
    }
    public void setPlayers(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void recordMove(int row, int col, char player) {
        recordedMoves.add(new GameMove(row, col, player));
    }

    public void saveGameToFile(String fileName) {
        String directory = "records";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileWriter writer = new FileWriter(directory + "/" +fileName)) {
            JSONObject gameData = new JSONObject();
            gameData.put("playerOneName", playerOneName);
            gameData.put("playerTwoName", playerTwoName);

            JSONArray movesArray = new JSONArray();
            for (GameMove recordedMove : recordedMoves) {
                JSONObject moveObject = new JSONObject();
                moveObject.put("row", recordedMove.getRow());
                moveObject.put("col", recordedMove.getCol());
                moveObject.put("player", recordedMove.getPlayer());
                movesArray.put(moveObject);
            }

            gameData.put("moves", movesArray);
         
            writer.write(gameData.toString(4)); 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<GameMove> replayGameFromFile(String fileName) {
        List<GameMove> replayedMoves = new ArrayList<>();
        File file = new File("records/" + fileName);
            System.out.println("records/" + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONObject gameData = new JSONObject(jsonContent.toString());
            playerOneName = gameData.getString("playerOneName");
            playerTwoName = gameData.getString("playerTwoName");
            
            JSONArray movesArray = gameData.getJSONArray("moves");

            for (int i = 0; i < movesArray.length(); i++) {
                JSONObject moveObject = movesArray.getJSONObject(i);
                int row = moveObject.getInt("row");
                int col = moveObject.getInt("col");
                int playerInt = moveObject.getInt("player");
                char player = (char) playerInt;
//                char player = moveObject.getString("player").charAt(0);
                replayedMoves.add(new GameMove(row, col, player));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return replayedMoves;
    }
}
