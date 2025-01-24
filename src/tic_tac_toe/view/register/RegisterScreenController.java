/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.register;

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
import javafx.scene.control.TextField;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.common.CurrentPlayer;
import tic_tac_toe.model.StatusEnum;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author Khaled Mustafa
 */
public class RegisterScreenController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmPasswordTF;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void onSignUpClicked(ActionEvent event) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String confirmPassword = confirmPasswordTF.getText();
        String error = validateUserInput(username, password, confirmPassword);
        
        if(error == null){
            sendRegisterRequest(username, password);
            handleRegisterResponse(event);
        }else{
            showErrorAlert(error);
        }
        
    }

    @FXML
    private void onBackClicked(Event event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String validateUserInput(String username, String password, String confirmPassword){
        if(username.isEmpty() || password.isEmpty()){
            return "Username or password can't be empty";
        }
        
        if(password.length() < 8){
            return "Password should be 8 digits at least";
        }
        
        if(!password.equals(confirmPassword)){
            return "Password and confirm password should be identical";
        }
        
        return null;
    }
    
    private void sendRegisterRequest(String username, String password){
        JSONObject registerRequest = new JSONObject();
        registerRequest.put("type", "register");
        registerRequest.put("username", username);
        registerRequest.put("password", password);
        ClientSocket.sendRequest(registerRequest);
    }
    
    private void handleRegisterResponse(ActionEvent event){
        new Thread(
            ()->{
                try {
                    JSONObject registerResponse = ClientSocket.responses.take();
                    boolean isOk = registerResponse.getBoolean("isOk");
                    Platform.runLater(
                            ()->{
                                if(isOk){
                                    String username = registerResponse.getString("username");
                                    String id = registerResponse.getString("id");
                                    CurrentPlayer.initPlayer(id,username, 500, StatusEnum.AVAILABLE);
                                    navigateToOnlineScreen(event);
                                }else{
                                    String error = registerResponse.getString("error");
                                    showErrorAlert(error);
                                }
                            }
                    );
                } catch (InterruptedException ex) {
                    Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        ).start();
    }
    
    private void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Signup Error");
        alert.setContentText(message);
            
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
    
    private void navigateToOnlineScreen(ActionEvent event){
        try {
            Navigator.navigateToOnlineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
