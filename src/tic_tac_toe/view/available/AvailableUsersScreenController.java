/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.available;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author reham
 */
public class AvailableUsersScreenController implements Initializable {
    
    @FXML
    private Button btnBack , btn1, btn2, btn3;

    @FXML
    private ImageView img1, img2, img3 , avatarProfile;

    @FXML
    private Label lblAvailable , lbl1, lbl2, lbl3;
    @FXML
     private VBox mainVBox;
    @FXML
     private HBox hbox1 , hbox2 , hbox3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
    
}
