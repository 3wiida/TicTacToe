/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.popups.serverConnectionpopup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class ServerConnectionPopupController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label Connectionlbl;
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField textField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
