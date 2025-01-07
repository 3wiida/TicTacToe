/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.offline.offline_main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import tic_tac_toe.view.popups.multiplayer_names_popup.MultiplayerNamesPopupController;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class OfflineScreenController implements Initializable {

    @FXML
    private Button withComputerBtn;
    @FXML
    private Button withFriendBtn;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void onPlayWithComputerClicked(){
        
    }
    
    @FXML
    public void onPlayWithFriendClicked(){
        Pair<String, String> playersNames = showNamesPopup();
        if(!playersNames.getKey().isEmpty()){
            System.out.println(playersNames.getKey());
            System.out.println(playersNames.getValue());
        }
    }
    
    @FXML
    public void onBackClicked(){
        
    }
    
    private Pair<String, String> showNamesPopup(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tic_tac_toe/view/popups/multiplayer_names_popup/MultiplayerNamesPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MultiplayerNamesPopupController controller = loader.getController();
            
            scene.getStylesheets().add(getClass().getResource("/tic_tac_toe/view/popups/multiplayer_names_popup/multiplayernamespopup.css").toExternalForm());
            Stage multiplayerNamesPopup = new Stage();
            multiplayerNamesPopup.setTitle("Enter Players Names");
            multiplayerNamesPopup.initModality(Modality.APPLICATION_MODAL);
            multiplayerNamesPopup.setScene(scene);
            multiplayerNamesPopup.showAndWait();
           
            Pair<String, String> playersNames = new Pair(controller.getPlayerOneName(),controller.getPlayerTwoName());
            return playersNames;
        } catch (IOException ex) {
            Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Pair<>("","");
    }
    
}
