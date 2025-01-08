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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author Khaled Mustafa
 */
public class RegisterScreenController implements Initializable {

    @FXML
    private Label lblSignUp;
    @FXML
    private TextField txtFieldUserName;
    @FXML
    private TextField txtFieldUserPassword;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtFieldConfirmPassword;
    @FXML
    private ImageView backPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tooltip();
    }    

    @FXML
    private void SignUpClicked(ActionEvent event) {
    }
    
    private void tooltip(){
        Tooltip userTip = new Tooltip("Please Enter Your Name");
        txtFieldUserName.setTooltip(userTip);
        
        Tooltip passwordTip = new Tooltip("Please Enter Your Password");
        txtFieldUserPassword.setTooltip(passwordTip);
        
        Tooltip confirmPasswordTip = new Tooltip("Please Enter Same Password");
        txtFieldConfirmPassword.setTooltip(confirmPasswordTip);
    }

    @FXML
    private void photoClicked(MouseEvent event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
