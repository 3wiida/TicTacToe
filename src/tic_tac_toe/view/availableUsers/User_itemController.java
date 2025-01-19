package tic_tac_toe.view.availableUsers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.navigation.Navigator;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class User_itemController implements Initializable {

    @FXML
    public Label userNameLabel;
    @FXML
    private HBox hbox;
    @FXML
    public Button inviteBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUserName(String userName) {
        userNameLabel.setText(userName);
    }
    

    @FXML
    private void onInviteClicked(ActionEvent event) {
        
    }
    
    
    
}
