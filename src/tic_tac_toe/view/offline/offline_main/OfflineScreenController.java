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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import tic_tac_toe.common.CurrentPlayer;
import tic_tac_toe.model.GameModeEnum;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.view.popups.multiplayer_names_popup.MultiplayerNamesPopupController;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class OfflineScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void onPlayWithComputerClicked(ActionEvent event){
        try {
            Navigator.navigateToModeSelectionScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void onPlayWithFriendClicked(ActionEvent event){
        Pair<String, String> playersNames = showNamesPopup();
        if(!playersNames.getKey().isEmpty()){
            try {
                Navigator.naviagteToGameBoardScreen(
                    event,
                    GameModeEnum.MULIPLAYER_OFFLINE,
                    playersNames.getKey(),
                    playersNames.getValue()
                );
            } catch (IOException ex) {
                Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void onBackClicked(Event event){
        try {
            if(CurrentPlayer.getPlayer() == null){
                Navigator.navigateToLandingScreen(event);
            }else{
                Navigator.navigateToOnlineScreen(event);
            }
        } catch (IOException ex) {
            Logger.getLogger(OfflineScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Pair<String, String> showNamesPopup(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.MULTIPLAYER_NAMES_POPUP_ROUTE));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            MultiplayerNamesPopupController controller = loader.getController();
            Stage multiplayerNamesPopup = new Stage();
            multiplayerNamesPopup.setTitle("Enter Players Names");
            multiplayerNamesPopup.initModality(Modality.APPLICATION_MODAL);
            multiplayerNamesPopup.initStyle(StageStyle.TRANSPARENT);
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
