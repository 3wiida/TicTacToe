package tic_tac_toe.view.landing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tic_tac_toe.navigation.Navigator;

public class LandingScreenController {

    @FXML
    private Button btnOffline;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnExit;

    @FXML
    private Label lblLanding;

    @FXML
    void ExitClicked(ActionEvent event) {

    }

    @FXML
    void OffilneClicked(ActionEvent event) {
        try {
            Navigator.navigateToOfflineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LandingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void SignUpClicked(ActionEvent event) {

    }

}
