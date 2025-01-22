/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.popups.serverShutDownpopup;

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
import javafx.scene.input.MouseEvent;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.view.login.LoginScreenController;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class ServerShutDownpopupController implements Initializable {

    @FXML
    private Label oopsLbl;
    @FXML
    private Label playorexitLbl;
    @FXML
    private Button offlineBtn;
    @FXML
    private Button exitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void offlineBtn(MouseEvent event) {
         try {
            Navigator.navigateToOfflineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
       @FXML
    private void ExitClicked(ActionEvent event) {
        Platform.exit();
    }
    
}
