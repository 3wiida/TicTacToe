/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

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
        
        Tooltip confirmPasswordTip = new Tooltip("Please Enter Your Password");
        txtFieldUserPassword.setTooltip(confirmPasswordTip);
    }
    
}
