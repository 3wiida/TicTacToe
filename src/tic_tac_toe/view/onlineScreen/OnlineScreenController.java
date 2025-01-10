/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.onlineScreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ComputerClicked(ActionEvent event) {
    }

    @FXML
    private void onlineClicked(ActionEvent event) {
    }

    @FXML
    private void ExitClicked(ActionEvent event) {
    }
    
    public void setProfileName(String userName){
        profileName.setText(userName);
    }
}
