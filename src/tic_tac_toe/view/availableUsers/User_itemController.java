package tic_tac_toe.view.availableUsers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class User_itemController implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private Button inviteUserButton;
    @FXML
    private HBox hbox;

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

    public void setInviteButtonAction(Runnable action) {
        inviteUserButton.setOnAction(event -> {
            try {

                Navigator.navigateToWaitingPopup(ScreensRoutes.WAITING_POPUP_SCREEN_ROUTE, "Waiting Request");
                action.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    


}
