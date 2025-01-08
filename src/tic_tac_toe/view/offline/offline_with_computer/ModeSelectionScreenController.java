package tic_tac_toe.view.offline.offline_with_computer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class ModeSelectionScreenController implements Initializable {

    @FXML
    private ImageView modeAvatar;
    @FXML
    private Text selectModeTxt;
    @FXML
    private Button easyBtn;
    @FXML
    private Button mediumBtn;
    @FXML
    private Button hardBtn;
    @FXML
    private ImageView backBrn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       easyBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image("/tic_tac_toe/assets/easy_avatar.png"));
       });
       
       mediumBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image("/tic_tac_toe/assets/medium_avatar.png"));
       });
       
       hardBtn.setOnMouseEntered(event->{
           modeAvatar.setImage(new Image("/tic_tac_toe/assets/hard_avatar.png"));
       });
       
    }
    
    @FXML
    public void onEasyClicked(){
        
    }
    
    @FXML
    public void onMediumClicked(){
        
    }
    
    
    @FXML
    public void onHardClicked(){
        
    }

    @FXML
    private void onBackClicked(Event event) {
        try {
            Navigator.navigateToOfflineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(ModeSelectionScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
