/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.records;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.view.landing.LandingScreenController;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class RecordsController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private ListView<?> RecordsListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
       @FXML
    void BackClicked(ActionEvent event) {
        try {
            Navigator.navigateToOfflineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LandingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
