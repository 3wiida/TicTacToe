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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.view.register.RegisterScreenController;

public class LoginOrRegisterPopupController implements Initializable {

    @FXML
    private Label asklbl;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private ImageView backPhoto;
    
    private int clickedAction; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void onRegisterClicked(ActionEvent event) {
       clickedAction = 1;
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onLoginClicked(ActionEvent event) {
       clickedAction = 2;
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Onclick(MouseEvent event) {
        clickedAction = 3;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public int  getAction(){
        return  clickedAction;
    }
    
}
