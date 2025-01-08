/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.popups.choose_login_signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author yasse
 */
public class LoginOrRegisterPopupController implements Initializable {

    @FXML
    private Label asklbl;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRegisterClicked(ActionEvent event) {
        try {
            Navigator.navigateToSignupScreen(event);
            //ownerStage.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginOrRegisterPopupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onLoginClicked(ActionEvent event) {
        try {
            Navigator.navigateToLoginScreen(event);
           // ownerStage.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginOrRegisterPopupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
