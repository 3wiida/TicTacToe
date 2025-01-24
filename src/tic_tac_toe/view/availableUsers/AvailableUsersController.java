package tic_tac_toe.view.availableUsers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.common.CurrentPlayer;
import tic_tac_toe.model.GameModeEnum;
import tic_tac_toe.model.Player;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.view.login.LoginScreenController;
import tic_tac_toe.view.offline.offline_main.OfflineScreenController;
import tic_tac_toe.view.popups.invitationpopup.InvitationPopupController;
import tic_tac_toe.view.popups.waitingRequestpopup.WaitingRequestPopupController;

public class AvailableUsersController implements Initializable {

    @FXML
    private Button reloadBtn;
    @FXML
    private ListView<HBox> usersListView;
    User_itemController userController;
    private Stage waitingPopup;
    private Stage invitationPopup;
    private String currentOpponentUsername;
    @FXML
    private Label availableLbl;
    @FXML
    private ImageView backBtn;
    @FXML
    private TextField searchField;
    private ObservableList<HBox> allUsers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendGetOnlineUsersRequest();
        handleScreenResponses();
        reloadBtn.setOnAction(event -> {
            sendGetOnlineUsersRequest();
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        String searchText = newValue.trim().toLowerCase(); 
        filterUsers(searchText); 
    });
    }

    private void addUser(String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user_item.fxml"));
            HBox userItem = loader.load();
            userController = loader.getController();
            userController.setUserName(userName);
            userController.inviteBtn.setOnAction(
                    (event) -> {
                        String opponentUsername = userController.userNameLabel.getText();
                        currentOpponentUsername = opponentUsername;
                        sendInvitation(opponentUsername);
                        waitingPopup = showWaitingPopup();
                    }
            );
            allUsers.add(userItem);
            usersListView.getItems().add(userItem);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private void filterUsers(String searchText) {
    ObservableList<HBox> filteredUsers = FXCollections.observableArrayList();

    if (searchText.isEmpty()) {
        usersListView.setItems(allUsers);
        return;
    }

    for (HBox userItem : allUsers) {
        Label userNameLabel = (Label) userItem.lookup("#userNameLabel"); 
        if (userNameLabel != null) {
            String userName = userNameLabel.getText().toLowerCase();
            if (userName.contains(searchText)) {
                filteredUsers.add(userItem);
            }
        }
    }
    usersListView.setItems(filteredUsers);
}

    @FXML
    private void photoClicked(MouseEvent event) {
        try {
            Navigator.navigateToOnlineScreen(event);
            JSONObject closeThread = new JSONObject();
            closeThread.put("type", "closeThread");
            ClientSocket.responses.put(closeThread);
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AvailableUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleScreenResponses() {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            JSONObject response = ClientSocket.responses.take();
                            if (response == null) {
                                break;
                            }
                            String responseType = response.getString("type");
                            switch (responseType) {
                                case "onlinePlayers":
                                    updatePlayersList(response);
                                    break;
                                case "invitationRecieved":
                                    handleRecievedInvitation(response);
                                    break;
                                case "invitationRejected":
                                    Platform.runLater(() -> waitingPopup.close());
                                    break;
                                case "invitationAccecpted":
                                    handleInvitationAccepted(response);
                                    break;
                                case "invitationCanceled":
                                    System.out.println("hey, invitation canceled");
                                    Platform.runLater(() -> invitationPopup.close());
                                    break;
                                case "closeThread":
                                    Thread.currentThread().interrupt();
                                    break;
                            }
                        } catch (InterruptedException ex) {
                            break;
                        } catch (JSONException ex) {
                            break;
                        }
                    }
                }
        ).start();
    }

    private void sendGetOnlineUsersRequest() {
        JSONObject request = new JSONObject();
        request.put("type", "get Avaliable users");
        ClientSocket.sendRequest(request);
    }

    private void updatePlayersList(JSONObject response) {
        JSONArray onlinePlayers = response.getJSONArray("onlinePlayers");

        Platform.runLater(() -> {
            usersListView.getItems().clear();
            allUsers.clear();
            for (int i = 0; i < onlinePlayers.length(); i++) {
                JSONObject playerJson = onlinePlayers.getJSONObject(i);
                String username = playerJson.getString("username");
                addUser(username);
            }
        });
    }

    private void handleRecievedInvitation(JSONObject response) {
        String hostUsername = response.getString("hostUsername");
        String hostId = response.getString("hostId");
        int hostScore = response.getInt("hostScore");
        Player opponent = new Player(hostId, hostUsername, hostScore);
        Platform.runLater(
                () -> {
                    boolean isInvitaionAccecpted = showInvitationPopup(hostUsername);
                    if (isInvitaionAccecpted) {
                        sendAccecptInvitation(hostUsername);
                        JSONObject closeThread = new JSONObject();
                        closeThread.put("type", "closeThread");
                        try {
                            ClientSocket.responses.put(closeThread);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AvailableUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        navigateToGameBoard(reloadBtn, opponent, false);
                    } else {
                        sendRejectInvitation(hostUsername);
                    }
                }
        );
    }

    private void handleInvitationAccepted(JSONObject response) {
        JSONObject opponent = response.getJSONObject("opponent");
        String opponentId = opponent.getString("opponentId");
        String opponentUsername = opponent.getString("opponentUsername");
        int opponentScore = opponent.getInt("opponentScore");
        Player opponentPlayer = new Player(opponentId, opponentUsername, opponentScore);
        Platform.runLater(
                () -> {
                    JSONObject closeThread = new JSONObject();
                    closeThread.put("type", "closeThread");
                    try {
                        ClientSocket.responses.put(closeThread);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AvailableUsersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    navigateToGameBoard(reloadBtn, opponentPlayer, true);
                    waitingPopup.close();
                }
        );
    }

    private void sendInvitation(String opponentUsername) {
        JSONObject invitationRequest = new JSONObject();
        invitationRequest.put("type", "invite");
        invitationRequest.put("opponentUsername", opponentUsername);
        ClientSocket.sendRequest(invitationRequest);
    }

    private void sendRejectInvitation(String hostUsername) {
        JSONObject rejectRequest = new JSONObject();
        rejectRequest.put("type", "invitationRejected");
        rejectRequest.put("from", CurrentPlayer.getPlayer().getUsername());
        rejectRequest.put("to", hostUsername);
        ClientSocket.sendRequest(rejectRequest);
    }

    private void sendAccecptInvitation(String hostUsername) {
        JSONObject accecptRequest = new JSONObject();
        accecptRequest.put("type", "invitationAccecpted");
        accecptRequest.put("to", hostUsername);
        JSONObject opponentData = new JSONObject();
        opponentData.put("opponentId", CurrentPlayer.getPlayer().getId());
        opponentData.put("opponentUsername", CurrentPlayer.getPlayer().getUsername());
        opponentData.put("opponentScore", CurrentPlayer.getPlayer().getScore());
        accecptRequest.put("opponent", opponentData);
        ClientSocket.sendRequest(accecptRequest);
    }

    private void sendCancelInvitation(String opponentUsername) {
        JSONObject cancelRequest = new JSONObject();
        cancelRequest.put("type", "invitationCanceled");
        cancelRequest.put("to", opponentUsername);
        ClientSocket.sendRequest(cancelRequest);
    }

    private boolean showInvitationPopup(String hostUsername) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.INVTATION_POPUP_ROUTE));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            InvitationPopupController controller = loader.getController();
            controller.setRequestLabel(hostUsername + " is inviting you to play");
            Stage invitationStage = new Stage();
            invitationStage.initModality(Modality.APPLICATION_MODAL);
            invitationStage.initStyle(StageStyle.TRANSPARENT);
            invitationStage.setScene(scene);
            invitationPopup = invitationStage;
            invitationStage.showAndWait();
            return controller.isInvitaitonAccepted();
        } catch (IOException ex) {
            Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private Stage showWaitingPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.WAITING_POPUP_ROUTE));
            Parent root = loader.load();
            WaitingRequestPopupController controller = loader.getController();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage waitingStage = new Stage();
            waitingStage.initModality(Modality.APPLICATION_MODAL);
            waitingStage.initStyle(StageStyle.TRANSPARENT);
            waitingStage.setScene(scene);
            controller.cancelBtn.setOnAction(
                    (event) -> {
                        sendCancelInvitation(currentOpponentUsername);
                        waitingPopup.close();
                    }
            );
            waitingStage.show();
            return waitingStage;
        } catch (IOException ex) {
            Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void navigateToGameBoard(Node event, Player opponent, boolean isHosting) {
        try {
            Navigator.naviagteToGameBoardScreen(event, GameModeEnum.MULTIPLAYER_ONLINE, opponent, isHosting);
        } catch (IOException ex) {
            Logger.getLogger(AvailableUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
