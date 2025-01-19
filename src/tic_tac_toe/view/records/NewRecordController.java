/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.records;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class NewRecordController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private Label userNamLabel;
    @FXML
    private Button watchButton;
    private String recordName;

    public NewRecordController(String recordName) {
        System.out.println(recordName);
        this.recordName = recordName;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userNamLabel.setText(recordName);
    }    
    
    
}
