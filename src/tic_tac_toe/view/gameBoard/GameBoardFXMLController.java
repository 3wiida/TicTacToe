/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tic_tac_toe.view.gameBoard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.util.Pair;
import tic_tac_toe.controller.computergamemodecontroller.EasyComputerModeController;
import tic_tac_toe.controller.computergamemodecontroller.HardComputerModeController;
import tic_tac_toe.model.ComputerMove;
import tic_tac_toe.model.Game;
import tic_tac_toe.model.GameModeEnum;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_EASY;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_HARD;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_MEDIUM;
import tic_tac_toe.model.WinningLaneEnum;
import tic_tac_toe.utils.ImageRoutes;

/**
 * FXML Controller class
 *
 * @author omarabdelaziz
 */
public class GameBoardFXMLController implements Initializable {

    @FXML
    private ImageView player1Image;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView player2Image;
    @FXML
    private ImageView imageCell00;
    @FXML
    private ImageView imageCell01;
    @FXML
    private ImageView imageCell02;
    @FXML
    private ImageView imageCell10;
    @FXML
    private ImageView imageCell11;
    @FXML
    private ImageView imageCell12;
    @FXML
    private ImageView imageCell20;
    @FXML
    private ImageView imageCell21;
    @FXML
    private ImageView imageCell22;
    @FXML
    private Button buttonCell00;
    @FXML
    private Button buttonCell01;
    @FXML
    private Button buttonCell02;
    @FXML
    private Button buttonCell10;
    @FXML
    private Button buttonCell11;
    @FXML
    private Button buttonCell12;
    @FXML
    private Button buttonCell20;
    @FXML
    private Button buttonCell21;
    @FXML
    private Button buttonCell22;
    
    
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;

    private Button[][] buttonsArray;
    private ImageView[][] imageArray;

    private Game game;
    
    private GameModeEnum gameMode;
    private ComputerMove computer;
    
    public void setGameMode(GameModeEnum mode){
        gameMode = mode;
        switch(gameMode){
            case COMPUTER_EASY:{
                computer = new EasyComputerModeController();
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
    }
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game();
        
        imageArray = new ImageView[][]{
            {imageCell00, imageCell01, imageCell02},
            {imageCell10, imageCell11, imageCell12},
            {imageCell20, imageCell21, imageCell22}
        };
        
        buttonsArray = new Button[][]{
            {buttonCell00, buttonCell01, buttonCell02},
            {buttonCell10, buttonCell11, buttonCell12},
            {buttonCell20, buttonCell21, buttonCell22}
        };
    }    
    
    @FXML
    private void handleCellClick(ActionEvent event) {
        
        Button clickedButton = (Button) event.getSource();

        int row = -1;
        int col = -1;

        if (clickedButton == buttonCell00) { row = 0; col = 0; }
        else if (clickedButton == buttonCell01) { row = 0; col = 1; }
        else if (clickedButton == buttonCell02) { row = 0; col = 2; }
        else if (clickedButton == buttonCell10) { row = 1; col = 0; }
        else if (clickedButton == buttonCell11) { row = 1; col = 1; }
        else if (clickedButton == buttonCell12) { row = 1; col = 2; }
        else if (clickedButton == buttonCell20) { row = 2; col = 0; }
        else if (clickedButton == buttonCell21) { row = 2; col = 1; }
        else if (clickedButton == buttonCell22) { row = 2; col = 2; }

        char currentPlayer = game.getCurrentPlayer();
        
        
        if (game.makeMove(row, col)) {
            if (currentPlayer == 'X') {
                imageArray[row][col].setImage(new Image(ImageRoutes.xImage));
            } else if (currentPlayer == 'O') {
                imageArray[row][col].setImage(new Image(ImageRoutes.oImage));
            }
            if (game.isGameOver()) {
                System.out.println("Player " + currentPlayer + " wins!");
                disableBoard();
                enableWinningCells();
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    showRematchPopup();
                });
                pause.play();
            } else if (game.getGameCounter() == 9) {
                System.out.println("players draw");
                disableBoard();
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    showRematchPopup();
                });
                pause.play();
            }
        }
        
        currentPlayer = game.getCurrentPlayer();
        
        if(gameMode == COMPUTER_EASY || gameMode == COMPUTER_MEDIUM || gameMode == COMPUTER_HARD){
            if(game.getGameCounter()<9){
                makeComputerMove(currentPlayer);
            }
        }
        
    }
    
    private void makeComputerMove(char currentPlayer){
        Pair<Integer,Integer> move = computer.move(game.getBoard());
        if (game.makeMove(move.getKey(), move.getValue())) {
            if (currentPlayer == 'X') {
                imageArray[move.getKey()][move.getValue()].setImage(new Image(ImageRoutes.xImage));
            } else if (currentPlayer == 'O') {
                imageArray[move.getKey()][move.getValue()].setImage(new Image(ImageRoutes.oImage));
            }
            if (game.isGameOver()) {
                System.out.println("Player " + currentPlayer + " wins!");
                disableBoard();
                enableWinningCells();
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    showRematchPopup();
                });
                pause.play();
            } else if (game.getGameCounter() == 9) {
                System.out.println("players draw");
                disableBoard();
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    showRematchPopup();
                });
                pause.play();
            }
        }
        
    }
    
    private void disableBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                node.setDisable(true);
            }
        }
    }
    
     private void enableWinningCells() {
        boolean colWinning = game.getWinningLane() == WinningLaneEnum.first_column
                || game.getWinningLane() == WinningLaneEnum.second_column
                || game.getWinningLane() == WinningLaneEnum.third_column;
        
        boolean rowWinning = game.getWinningLane() == WinningLaneEnum.first_row 
                || game.getWinningLane() == WinningLaneEnum.second_row
                || game.getWinningLane() == WinningLaneEnum.third_row;
        
        if (colWinning ) {
            System.out.println("col winning" + game.getWinningLane().getCode());
            for (int i = 0; i < 3; i++) {
                imageArray[i][game.getWinningLane().getCode()].setDisable(false);
            }
        }
        else if (rowWinning) {
            System.out.println("row winning" + game.getWinningLane().getCode());
            for (int i = 0; i < 3; i++) {
                imageArray[game.getWinningLane().getCode()][i].setDisable(false);
            }
        }
        else if (game.getWinningLane() == WinningLaneEnum.first_diagonal) {
            for (int i = 0; i < 3; i++) {
                imageArray[i][i].setDisable(false);
            }
        }
        else if (game.getWinningLane() == WinningLaneEnum.second_diagonal) {
            for (int i = 0; i < 3; i++) {
                imageArray[i][2-i].setDisable(false);
            }
        }
    }
     private void showRematchPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rematch");
        alert.setHeaderText("Game Over");
        alert.setContentText("Do you want to play a rematch?");
        
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        alert.show();
        alert.setOnHidden(dialogEvent -> {
            if (alert.getResult() == ButtonType.YES) {
                resetGameBoard();
            } else {
                System.out.println("No rematch. Game ended.");
            }
        });
    }
     
    private void resetGameBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                ((ImageView) node).setImage(null);
                
                node.setDisable(false);
            }
        }
        player1Score.setText(""+game.getPlayer1Score());
        player2Score.setText(""+game.getPlayer2Score());

        game.resetBoard();
    }
    
    @FXML
    private void onClickRecordGame(ActionEvent event) {
    }

    @FXML
    private void onClickExitGame(ActionEvent event) {
    }
    
}
