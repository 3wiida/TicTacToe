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
    private ImageView backPhoto;
    @FXML
    private TextField txtFieldFullName;

    String regex = "^[a-zA-Z0-9]+$"; 
    String fullNameregex = "^[a-zA-Z]+$";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tooltip();
    }    

    @FXML
    private void SignUpClicked(ActionEvent event){
        if(!txtFieldFullName.getText().trim().isEmpty() && !txtFieldUserPassword.getText().trim().isEmpty() && !txtFieldUserName.getText().trim().isEmpty()){
            if(txtFieldUserName.getText().matches(regex) && txtFieldFullName.getText().matches(fullNameregex)){
                System.err.println("Valid");
                
                /* Send data to sever to store in data base*/
                
                
            }else{
                System.out.println("Invalid UserName or fullname");
            }
            }else{
                System.out.println("fill all information");
            }
        }
    private void tooltip(){
        Tooltip userTip = new Tooltip("Please Enter Your User Name");
        txtFieldUserName.setTooltip(userTip);
        
        Tooltip passwordTip = new Tooltip("Please Enter Your Password");
        txtFieldUserPassword.setTooltip(passwordTip);
        
        Tooltip FullName = new Tooltip("Please Enter Your Full Name");
        txtFieldFullName.setTooltip(FullName);
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
