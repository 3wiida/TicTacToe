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
import javafx.event.Event;
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
import org.json.JSONException;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.common.CurrentPlayer;
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
    private boolean isMyTurn;
    private Player opponent;
    @FXML
    private Label scoreLabel1;
    @FXML
    private Label scoreLabel2;

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

    public void setOnlineParameters(Player opponent, boolean isHosting) {
        this.opponent = opponent;
        this.isHosting = isHosting;
        this.gameMode = MULTIPLAYER_ONLINE;
        isMyTurn = isHosting;
        currentPlayer = isHosting ? 'X' : 'O';
    }

    public void setGameMode(GameModeEnum mode) {
        gameMode = mode;
        if (null != gameMode) {
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
                    recieveMessagesFromServer();
                    setupBoardForOnlineMultiplayerGame();

                    break;
                case REPLAY_GAME:
                    replaySavedGame();
                    break;
                default:
                    break;
            }
        }
    }

    private void setupBoardForOfflineMultiplayerGame() {
        playerOneTV.setText(playerOne.getUsername());
        playerTwoTV.setText(playerTwo.getUsername());
    }

    private void setupBoardForComputerGame() {
        playerOneTV.setText("You");
        playerTwoTV.setText("PC");
        playerOne = new Player("YOU");
        playerTwo = new Player("PC");
        player2Image.setImage(new Image(ImageRoutes.COMPUTER_AVATAR));
    }

    private void setupBoardForOnlieMultiplayerGame() {

    }

    public void setPlayersNames(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
    }

    public void setFileNameForGameReplay(String fileName) {
        this.fileName = fileName;
    }

    @FXML
    private void handleCellClick(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();

        int row = -1;
        int col = -1;

        if (clickedButton == buttonCell00) {
            row = 0;
            col = 0;
        } else if (clickedButton == buttonCell01) {
            row = 0;
            col = 1;
        } else if (clickedButton == buttonCell02) {
            row = 0;
            col = 2;
        } else if (clickedButton == buttonCell10) {
            row = 1;
            col = 0;
        } else if (clickedButton == buttonCell11) {
            row = 1;
            col = 1;
        } else if (clickedButton == buttonCell12) {
            row = 1;
            col = 2;
        } else if (clickedButton == buttonCell20) {
            row = 2;
            col = 0;
        } else if (clickedButton == buttonCell21) {
            row = 2;
            col = 1;
        } else if (clickedButton == buttonCell22) {
            row = 2;
            col = 2;
        }

        if (isEmptyCell(row, col)) {
            if (gameMode == MULTIPLAYER_ONLINE) {
                if (isMyTurn) {
                    System.out.println(currentPlayer);
                    commitMove(currentPlayer, row, col);
                    sendMoveOverNetwork(currentPlayer + "", row, col);
                    isMyTurn = !isMyTurn;
                }
            } else {
                currentPlayer = game.getCurrentPlayer();
                commitMove(currentPlayer, row, col);
                currentPlayer = game.getCurrentPlayer();
                if (gameMode == COMPUTER_EASY || gameMode == COMPUTER_MEDIUM || gameMode == COMPUTER_HARD) {
                    if (game.getGameCounter() < 9) {
                        Pair<Integer, Integer> move = computer.move(game.getBoard());
                        commitMove(currentPlayer, move.getKey(), move.getValue());
                    }
                }
            }

        }

    }

    private void commitMove(char currentPlayer, int row, int col) {
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
                if (gameMode == MULTIPLAYER_ONLINE) {
                    System.out.println("host => " + isHosting);
                    System.out.println("my turn => " + isMyTurn);
                    if (isMyTurn) {
                        System.out.println("I won");
                        sendUpdateScore(CurrentPlayer.getPlayer());
                        CurrentPlayer.getPlayer().setScore((CurrentPlayer.getPlayer().getScore()) + 10);
                        winner = 1;
                    } else {
                        System.out.println("I lose");
                        sendDecreaseScore(opponent);
                        winner = 2;
                        CurrentPlayer.getPlayer().setScore((CurrentPlayer.getPlayer().getScore()) - 10);
                    }
                } else {
                    if (currentPlayer == 'X') {
                        winner = 1;
                    } else if (currentPlayer == 'O') {
                        winner = 2;
                    }
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
                    showRematchPopup(boardAnchorPane,Navigator.getMainStage());
                }
            }

        }
    }

    private boolean isEmptyCell(int row, int col) {
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

        if (colWinning) {
            System.out.println("col winning " + game.getWinningLane().getCode());
            currentButton = buttonsArray[0][game.getWinningLane().getCode()];
            x1 = currentButton.getLayoutX() + currentButton.getWidth() / 2;
            y1 = currentButton.getLayoutY();
            currentButton = buttonsArray[2][game.getWinningLane().getCode()];
            x2 = currentButton.getLayoutX() + currentButton.getWidth() / 2;
            y2 = currentButton.getLayoutY() + currentButton.getHeight();
        } else if (rowWinning) {
            System.out.println("row winning " + game.getWinningLane().getCode());
            currentButton = buttonsArray[game.getWinningLane().getCode()][0];
            x1 = currentButton.getLayoutX();
            y1 = currentButton.getLayoutY() + currentButton.getHeight() / 2;
            currentButton = buttonsArray[game.getWinningLane().getCode()][2];
            x2 = currentButton.getLayoutX() + currentButton.getWidth();
            y2 = currentButton.getLayoutY() + currentButton.getHeight() / 2;
        } else if (game.getWinningLane() == WinningLaneEnum.first_diagonal) {
            currentButton = buttonsArray[0][0];
            x1 = currentButton.getLayoutX();
            y1 = currentButton.getLayoutY();
            currentButton = buttonsArray[2][2];
            x2 = currentButton.getLayoutX() + currentButton.getWidth();
            y2 = currentButton.getLayoutY() + currentButton.getHeight();
        } else if (game.getWinningLane() == WinningLaneEnum.second_diagonal) {
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
                System.out.println(currentPlayer);
                showRematchPopup(boardAnchorPane,Navigator.getMainStage());
            }
        });
        timeline.play();
    }

    private void showRematchPopup(Node node, Stage parentStage) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.POPUP_GAME_STATUS_ROUTE));
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 400);
                scene.setFill(Color.TRANSPARENT);

                Stage gameStatusPopup = new Stage();
                gameStatusPopup.initOwner(parentStage); // Set the parent stage
                gameStatusPopup.initModality(Modality.WINDOW_MODAL); // Make the popup modal
                gameStatusPopup.initStyle(StageStyle.TRANSPARENT); // Optional: Transparent background
                gameStatusPopup.setScene(scene);

                PopUpGameController controller = loader.getController();
                controller.setPlayAgainVisablility(gameMode != MULTIPLAYER_ONLINE);
                controller.setPopupStage(gameStatusPopup);
                controller.setPlayAgainBtnFunc(this::resetGameBoard);

                controller.setCloseBtnFunc(() -> {
                    if (CurrentPlayer.getPlayer() == null) {
                        try {
                            Navigator.navigateToLandingScreen(node);
                        } catch (IOException ex) {
                            Logger.getLogger(GameBoardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            sendOnlineGameFinishedToServer();
                            Navigator.navigateToOnlineScreen(node);
                        } catch (IOException ex) {
                            Logger.getLogger(GameBoardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                String msg;
                switch (winner) {
                    case 0:
                        msg = "Draw, play again and fight to win";
                        break;
                    case 1:
                        msg = "You Won, Congratulations";
                        break;
                    case 2:
                        msg = "Unfortunately, you lost. Better luck next time <3";
                        break;
                    default:
                        msg = "Game Over";
                        break;
                }
                controller.setPopupStatusMsg(msg);

                // Center the popup relative to the parent stage
                gameStatusPopup.setOnShowing(event -> {
                    double parentX = parentStage.getX();
                    double parentY = parentStage.getY();
                    double parentWidth = parentStage.getWidth();
                    double parentHeight = parentStage.getHeight();

                    double popupWidth = gameStatusPopup.getWidth();
                    double popupHeight = gameStatusPopup.getHeight();

                    gameStatusPopup.setX(parentX + (parentWidth - 600) / 2);
                    gameStatusPopup.setY(parentY + (parentHeight - 400) / 2);
                });

                gameStatusPopup.showAndWait();

            } catch (IOException ex) {
                Logger.getLogger(GameBoardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void sendOnlineGameFinishedToServer() {
        JSONObject gameFinished = new JSONObject();
        gameFinished.put("type", "onlineGameFinished");
        ClientSocket.sendRequest(gameFinished);
    }

    private void resetGameBoard() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                ((ImageView) node).setImage(null);
            } else if (node instanceof Button) {
                node.setDisable(false);
            }
        }

        if (line != null) {
            boardAnchorPane.getChildren().remove(line);
            line = null;
        }

        player1Score.setText("" + game.getPlayer1Score());
        player2Score.setText("" + game.getPlayer2Score());
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

    private void recordGame() {
        String p1Name;
        String p2Name;
        if (gameMode == COMPUTER_EASY || gameMode == COMPUTER_MEDIUM || gameMode == COMPUTER_HARD) {
            p1Name = "You";
            p2Name = "PC";
        } else {
            p2Name = playerTwoTV.getText();
            p1Name = playerOneTV.getText();
        }

        gameRecorder.setPlayers(p1Name, p2Name);

        gameRecorder.saveGameToFile(p1Name + "Vs" + p2Name + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + LocalDateTime.now().getSecond() + ".json");
    }

    @FXML
    private void onClickExitGame(ActionEvent event) {
        doExitFunction(event);
    }

    private void doExitFunction(Event event) {
        try {
            if (gameMode == MULTIPLAYER_ONLINE) {
                JSONObject closeThread = new JSONObject();
                closeThread.put("type", "closeThread");
                try {
                    ClientSocket.responses.put(closeThread);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                sendWithdrawalRequest();
            }

            if (CurrentPlayer.getPlayer() == null) {
                Navigator.navigateToLandingScreen(event);
            } else {
                Navigator.navigateToOnlineScreen(event);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendWithdrawalRequest() {
        JSONObject withdrawalRequest = new JSONObject();
        withdrawalRequest.put("type", "withdrawal");
        withdrawalRequest.put("to", opponent.getUsername());
        ClientSocket.sendRequest(withdrawalRequest);
        CurrentPlayer.getPlayer().setScore(CurrentPlayer.getPlayer().getScore() - 10);
    }

    private void replaySavedGame() {

        recordGameBtn.setDisable(true);
        player1Score.setVisible(false);
        player2Score.setVisible(false);
        scoreLabel1.setVisible(false);
        scoreLabel2.setVisible(false);
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

    /* Online Game Logic */
    private void recieveMessagesFromServer() {
        System.out.println("enter recieve function");
        new Thread(() -> {
            if (!ClientSocket.checkSocketStat()) {
                System.out.println("server is connected");
                while (true) {
                    try {
                        JSONObject recievedMSG = ClientSocket.responses.take();
                        if (recievedMSG == null) {
                            break;
                        }
                        String responseType = recievedMSG.getString("type");
                        switch (responseType) {
                            case "closeThread":
                                Thread.currentThread().interrupt();
                                break;

                            case "withdrawal": {
                                System.out.println("recieved withdrawl form the opponent");
                                winner = 1;
                                showRematchPopup(boardAnchorPane,Navigator.getMainStage());
                                CurrentPlayer.getPlayer().setScore(CurrentPlayer.getPlayer().getScore() + 10);
                                break;
                            }

                            default:
                                int row = recievedMSG.getInt("row");
                                int col = recievedMSG.getInt("col");
                                char turn = recievedMSG.getString("turn").charAt(0);
                                Platform.runLater(() -> {
                                    commitMove(turn, row, col);
                                    isMyTurn = !isMyTurn;
                                });
                                break;
                        }
                    } catch (InterruptedException ex) {
                        break;
                    } catch (JSONException ex) {
                        break;
                    }
                }

            } else {
                System.out.println("may be connection faild");
            }
        }).start();
    }

    private void setupBoardForOnlineMultiplayerGame() {
        playerOneTV.setText(CurrentPlayer.getPlayer().getUsername());
        playerTwoTV.setText(opponent.getUsername());
        player1Score.setText(CurrentPlayer.getPlayer().getScore() + "");
        player2Score.setText(opponent.getScore() + "");
        Navigator.getMainStage().setOnCloseRequest((event) -> {
            System.out.println("window closed");
            doExitFunction(event);
        });
    }

    private void sendMoveOverNetwork(String currentPlayer, int row, int col) {
        if (!ClientSocket.checkSocketStat()) {
            /* prepare JSON object */
            JSONObject moveJSON = new JSONObject();
            moveJSON.put("type", "move");
            moveJSON.put("turn", currentPlayer);
            moveJSON.put("row", row);
            moveJSON.put("col", col);
            moveJSON.put("from", CurrentPlayer.getPlayer().getUsername());
            moveJSON.put("to", opponent.getUsername());
            /* Send it */
            ClientSocket.sendRequest(moveJSON);
        } else {
            /* Handle for testing */
            System.out.println("Error in sending move, may be connection is faild");
        }
    }

    private void sendUpdateScore(Player player) {
        if (!ClientSocket.checkSocketStat()) {
            JSONObject scoreJson = new JSONObject();
            scoreJson.put("type", "increase_score");
            scoreJson.put("name", player.getUsername());
            scoreJson.put("id", player.getId());
            ClientSocket.sendRequest(scoreJson);
        } else {
            System.out.println("error in send increase score");
        }
    }

    private void sendDecreaseScore(Player opponent) {
        System.out.println("I lost sent decrease to server");
        if (!ClientSocket.checkSocketStat()) {
            JSONObject scoreJson = new JSONObject();
            scoreJson.put("type", "decrease_score");
            scoreJson.put("name", opponent.getUsername());
            scoreJson.put("id", opponent.getId());
            ClientSocket.sendRequest(scoreJson);
        } else {
            System.out.println("error in send increase score");
        }
    }
}
