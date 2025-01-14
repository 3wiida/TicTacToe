/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.onlineScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author yasse
 */
public class OnlineScreenController implements Initializable {

    @FXML
    private Button btnWithPC;
    @FXML
    private Button btnOnline;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblLanding;
    @FXML
    private Label profileName;
    @FXML
    private Button profile;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void ExitClicked(ActionEvent event) {
        Platform.exit();
    }
    
 
    @FXML
    private void onOfflineClicked(ActionEvent event) {
    }

    @FXML
    private void onOnlineClicked(ActionEvent event) {
        /* navigate to available users Screen */
    }

    @FXML
    private void onProfileClicked(ActionEvent event) {
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) {
        try {
            /* Send request to server and based on respone will logout and return to offline screen */
            
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(OnlineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void setProfileName(String userName){
        profileName.setText(userName);
    }

}
