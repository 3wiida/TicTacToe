/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tic_tac_toe.view.gameBoard;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;
import tic_tac_toe.controller.computergamemodecontroller.ComputerPlayerFactory;
import tic_tac_toe.model.ComputerMove;
import tic_tac_toe.model.Game;
import tic_tac_toe.model.GameModeEnum;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_EASY;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_HARD;
import static tic_tac_toe.model.GameModeEnum.COMPUTER_MEDIUM;
import static tic_tac_toe.model.GameModeEnum.MULIPLAYER_OFFLINE;
import static tic_tac_toe.model.GameModeEnum.MULTIPLAYER_ONLINE;
import tic_tac_toe.model.GameMove;
import tic_tac_toe.model.GameRecorder;
import tic_tac_toe.model.Player;
import tic_tac_toe.model.WinningLaneEnum;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.utils.ImageRoutes;
import tic_tac_toe.view.popups.popupgamestatus.PopUpGameController;

/**
 * FXML Controller class
 *
 * @author omarabdelaziz
 */
public class GameBoardFXMLController implements Initializable {

    @FXML
    private GridPane gridPane;
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
    private ImageView player1Image;
    @FXML
    private ImageView player2Image;
    @FXML
    private Label playerOneTV;
    @FXML
    private Label playerTwoTV;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private AnchorPane boardAnchorPane;
    @FXML
    private Button recordGameBtn;
    
    private Button[][] buttonsArray;
    private ImageView[][] imageArray;

    private Game game;
    private char currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private GameModeEnum gameMode;
    private ComputerMove computer;
    private int winner;
    private Line line;
    private GameRecorder gameRecorder = new GameRecorder();
    private boolean isGameRecording = false;
    
    
    private String fileName;
    
    /* online parameters */
    private boolean isHosting;
    private Player opponent;
    
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
    
    public void setOnlineParameters(Player opponent, boolean isHosting){
        this.opponent = opponent;
        this.isHosting = isHosting;
        this.gameMode = MULTIPLAYER_ONLINE;
    }
    
    public void setGameMode(GameModeEnum mode){
        gameMode = mode;
        if(null != gameMode){
            switch (gameMode) {
                case COMPUTER_EASY:
                case COMPUTER_MEDIUM:
                case COMPUTER_HARD:
                    computer = ComputerPlayerFactory.createCmputer(gameMode);
                    setupBoardForComputerGame();
                    break;
                case MULIPLAYER_OFFLINE:
                    setupBoardForOfflineMultiplayerGame();
                    break;
                case MULTIPLAYER_ONLINE:
                    setupBoardForOnlieMultiplayerGame();
                    break;
                case REPLAY_GAME:
                    replaySavedGame();
                    break;
                default:
                    break;
            }
        }    
    }
    
    private void setupBoardForOfflineMultiplayerGame(){
        playerOneTV.setText(playerOne.getUsername());
        playerTwoTV.setText(playerTwo.getUsername());
    }  
    
    
    private void setupBoardForComputerGame(){
        playerOneTV.setText("You");
        playerTwoTV.setText("PC");
        player2Image.setImage(new Image(ImageRoutes.COMPUTER_AVATAR));
    }
    
    private void setupBoardForOnlieMultiplayerGame(){
        
    }
    
    public void setPlayersNames(String playerOneName, String playerTwoName){
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
    }
    
    public void setFileNameForGameReplay(String fileName){
        this.fileName = fileName;
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
        
        if(isEmptyCell(row, col)){
            currentPlayer = game.getCurrentPlayer();
            commitMove(currentPlayer, row, col);
            currentPlayer = game.getCurrentPlayer();
            if(gameMode == COMPUTER_EASY || gameMode == COMPUTER_MEDIUM || gameMode == COMPUTER_HARD){
                if(game.getGameCounter()<9){
                    Pair<Integer,Integer> move = computer.move(game.getBoard());
                    commitMove(currentPlayer, move.getKey(), move.getValue());
                }
            }
        }
    }
 
    private void commitMove(char currentPlayer, int row, int col){
         if (game.makeMove(row, col)) {
            if (gameMode != GameModeEnum.REPLAY_GAME) {
                gameRecorder.recordMove(row, col, currentPlayer);
            }
            if (currentPlayer == 'X') {
                imageArray[row][col].setImage(new Image(ImageRoutes.xImage));
            } else if (currentPlayer == 'O') {
                imageArray[row][col].setImage(new Image(ImageRoutes.oImage));
            }
            
            if (game.isGameOver() && !game.getDidDraw()) {
                if (currentPlayer == 'X') {
                    winner = 1;
                } else if (currentPlayer == 'O'){
                    winner = 2;
                }
                if (isGameRecording) {
                    recordGame();
                }
                disableBoard();
                drawWinningLine();
                
            } else if (game.getDidDraw()) {
                winner = 0;
                if (isGameRecording) {
                    recordGame();
                }
                disableBoard();
                if (gameMode != GameModeEnum.REPLAY_GAME) {
                    showRematchPopup();
                }
            }
        }
    }
    
    private boolean isEmptyCell(int row, int col){
        return game.getBoard()[row][col] == '\0';
    }
    
    private void disableBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }
    
     private void drawWinningLine() {
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        Button currentButton;
        boolean colWinning = game.getWinningLane() == WinningLaneEnum.first_column
                || game.getWinningLane() == WinningLaneEnum.second_column
                || game.getWinningLane() == WinningLaneEnum.third_column;
        
        boolean rowWinning = game.getWinningLane() == WinningLaneEnum.first_row 
                || game.getWinningLane() == WinningLaneEnum.second_row
                || game.getWinningLane() == WinningLaneEnum.third_row;
        
        if (colWinning ) {
            System.out.println("col winning " + game.getWinningLane().getCode());
            currentButton = buttonsArray[0][game.getWinningLane().getCode()];
            x1 = currentButton.getLayoutX() + currentButton.getWidth()/2;
            y1 = currentButton.getLayoutY();
            currentButton = buttonsArray[2][game.getWinningLane().getCode()];
            x2 = currentButton.getLayoutX() + currentButton.getWidth()/2;
            y2 = currentButton.getLayoutY() + currentButton.getHeight(); 
        }
        else if (rowWinning) {
            System.out.println("row winning " + game.getWinningLane().getCode());
            currentButton = buttonsArray[game.getWinningLane().getCode()][0];
            x1 = currentButton.getLayoutX();
            y1 = currentButton.getLayoutY() + currentButton.getHeight()/2;
            currentButton = buttonsArray[game.getWinningLane().getCode()][2];
            x2 = currentButton.getLayoutX() + currentButton.getWidth();
            y2 = currentButton.getLayoutY() + currentButton.getHeight()/2; 
        }
        else if (game.getWinningLane() == WinningLaneEnum.first_diagonal) {
            currentButton = buttonsArray[0][0];
            x1 = currentButton.getLayoutX();
            y1 = currentButton.getLayoutY();
            currentButton = buttonsArray[2][2];
            x2 = currentButton.getLayoutX() + currentButton.getWidth();
            y2 = currentButton.getLayoutY() + currentButton.getHeight(); 
        }
        else if (game.getWinningLane() == WinningLaneEnum.second_diagonal) {
            currentButton = buttonsArray[0][2];
            x1 = currentButton.getLayoutX() + currentButton.getWidth();
            y1 = currentButton.getLayoutY();
            currentButton = buttonsArray[2][0];
            x2 = currentButton.getLayoutX();
            y2 = currentButton.getLayoutY() + currentButton.getHeight(); 
        }
        line = new Line(x1, y1, x1, y1);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5.0);
        line.setOpacity(0);
        boardAnchorPane.getChildren().add(line);
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.5), new KeyValue(line.endXProperty(), x2)),
            new KeyFrame(Duration.seconds(0.5), new KeyValue(line.endYProperty(), y2)),
            new KeyFrame(Duration.seconds(0.5), new KeyValue(line.opacityProperty(), 1))
        );
        timeline.setOnFinished(e -> {
            if (gameMode != GameModeEnum.REPLAY_GAME) {
                showRematchPopup();
            }
        });
        timeline.play();
    }
     
    private void showRematchPopup() {
       Platform.runLater(() -> {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.POPUP_GAME_STATUS_ROUTE));
               Parent root = loader.load();
               Scene scene = new Scene(root,600,400);
               scene.setFill(Color.TRANSPARENT);

               Stage  gameStatusPopup = new Stage();
               gameStatusPopup.initModality(Modality.APPLICATION_MODAL);
               gameStatusPopup.setScene(scene);
               gameStatusPopup.initStyle(StageStyle.TRANSPARENT);

               PopUpGameController controller = loader.getController();

               controller.setPopupStage(gameStatusPopup);
               controller.setPlayAgainBtnFunc(() -> {
                   resetGameBoard();
               });
               String msg = "";
               switch (winner){
                   case 0:
                       msg = "Draw, play again and fight to win"; 
                       break;
                   case 1:
                       msg = "You Won, Congratulations"; 
                       break;
                   case 2:
                       msg = "unfortunately you lost, Better luck next Time <3 ";
                       break;
               }
               controller.setPopupStatusMsg(msg);
               gameStatusPopup.showAndWait();

           } catch (IOException ex) {
               Logger.getLogger(GameBoardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   }

    private void resetGameBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                ((ImageView) node).setImage(null);
            }
            else if (node instanceof Button) {
                node.setDisable(false);
            }
        }
        
        if (line != null) {
            boardAnchorPane.getChildren().remove(line);
            line = null;
        }
        
        player1Score.setText(""+game.getPlayer1Score());
        player2Score.setText(""+game.getPlayer2Score());
        winner = Integer.MAX_VALUE;
        if (!gameRecorder.isEmpty()) {
            gameRecorder = null;
            gameRecorder = new GameRecorder();
        }
        
        if (isGameRecording) {
            onClickRecordGame(new ActionEvent());
        }
        game.resetBoard();
    }
    
    @FXML
    private void onClickRecordGame(ActionEvent event) {
        isGameRecording = (isGameRecording == false) ? true : false;
        if (isGameRecording) {
            recordGameBtn.setStyle("-fx-background-color: red;");
        } else {
            recordGameBtn.setStyle("-fx-background-color: black;");
        }
    }
    
    private  void recordGame(){
        String p1Name;
        String p2Name;
        if(gameMode==COMPUTER_EASY || gameMode == COMPUTER_MEDIUM || gameMode == COMPUTER_HARD){
            p1Name = "You";
            p2Name = "PC";
        }else{
            p2Name = playerTwo.getUsername();
            p1Name = playerOne.getUsername();
        }
        
        
        gameRecorder.setPlayers(p1Name, p2Name);
      
        gameRecorder.saveGameToFile(p1Name + "Vs" + p2Name + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)+ LocalDateTime.now().getSecond() + ".json");
    }
    @FXML
    private void onClickExitGame(ActionEvent event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void replaySavedGame() {
       
        recordGameBtn.setDisable(true);
        recordGameBtn.setOpacity(0);
        disableBoard();
        
        List<GameMove> replayedMoves = gameRecorder.replayGameFromFile(fileName);
        setPlayersNames(gameRecorder.getPlayerOneName(), gameRecorder.getPlayerTwoName());
        playerOneTV.setText(playerOne.getUsername());
        playerTwoTV.setText(playerTwo.getUsername());
        
        Timeline timeline = new Timeline();
        int[] index = {0};

        for (GameMove replayedMove : replayedMoves) {
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(index[0]), event -> {
                char player = replayedMove.getPlayer();
                int row = replayedMove.getRow();
                int col = replayedMove.getCol();

                commitMove(player, row, col);
            });
            timeline.getKeyFrames().add(keyFrame);
            index[0]++;
        }
        timeline.play();
    }
}
