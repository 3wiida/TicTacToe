package tic_tac_toe.view.offline.offline_with_computer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tic_tac_toe.model.GameModeEnum;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.utils.ImageRoutes;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class ModeSelectionScreenController implements Initializable {

    @FXML
    private ImageView modeAvatar;
    
    @FXML
    private Button easyBtn;
    
    @FXML
    private Button mediumBtn;
    
    @FXML
    private Button hardBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       easyBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image(ImageRoutes.EASY_AVATAR));
       });
       
       mediumBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image(ImageRoutes.MEDIUM_AVATAR));
       });
       
       hardBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image(ImageRoutes.HARD_AVATAR));
       });
       
    }
    
    @FXML
    public void onEasyClicked(ActionEvent event){
        try {
            Navigator.naviagteToGameBoardScreen(event, GameModeEnum.COMPUTER_EASY);
        } catch (IOException ex) {
            Logger.getLogger(ModeSelectionScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void onMediumClicked(ActionEvent event){
        try {
            Navigator.naviagteToGameBoardScreen(event, GameModeEnum.COMPUTER_MEDIUM);
        } catch (IOException ex) {
            Logger.getLogger(ModeSelectionScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    public void onHardClicked(ActionEvent event){
        try {
            Navigator.naviagteToGameBoardScreen(event, GameModeEnum.COMPUTER_HARD);
        } catch (IOException ex) {
            Logger.getLogger(ModeSelectionScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onBackClicked(Event event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(ModeSelectionScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
