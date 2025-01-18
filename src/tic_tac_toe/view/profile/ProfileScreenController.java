package tic_tac_toe.view.profile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.common.CurrentPlayer;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class ProfileScreenController implements Initializable {

    @FXML
    private Text scoreTV;
    @FXML
    private Text usernameTV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameTV.setText(CurrentPlayer.getPlayer().getUsername());
        scoreTV.setText("("+CurrentPlayer.getPlayer().getScore()+" Points)");
    }    

    @FXML
    private void navigateToRecordsScreen(ActionEvent event) {
        try {
            Navigator.navigateToRecordsScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void logout(ActionEvent event) {
        sendLogoutRequest();
        handleLogoutResponse(event);
    }

    @FXML
    private void navigateBack(Event event) {
        try {
            Navigator.navigateToOnlineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendLogoutRequest(){
        JSONObject logutRequest = new JSONObject();
        logutRequest.put("type", "logout");
        ClientSocket.sendRequest(logutRequest);
    }
    
    private void handleLogoutResponse(ActionEvent event){
        new Thread(
            ()->{
                JSONObject logoutResponse = ClientSocket.recieveResponse();
                boolean isOk = logoutResponse.getBoolean("isOk");
                Platform.runLater(
                    ()->{
                        if(isOk){
                            navigateToLandingScreen(event);
                        }else{
                            showErrorAlert();
                        }
                    }
                );
                
            }
        ).start();
    }
    
    private void navigateToLandingScreen(ActionEvent event){
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Logout Error");
        alert.setContentText("Unkown error occured, please try again later");
            
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle(
            "-fx-background-color: #ffcccc;" + 
            "-fx-border-color: #ff0000;" +    
            "-fx-border-width: 2px;" +       
            "-fx-padding: 10px;"             
        );
        dialogPane.lookup(".header-panel").setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #800000;" 
        );
        dialogPane.lookup(".content").setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #333333;" 
        );
        
        alert.showAndWait();
    }
}
