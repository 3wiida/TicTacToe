package tic_tac_toe.view.landing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tic_tac_toe.model.Player;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.view.popups.choose_login_signup.LoginOrRegisterPopupController;
import tic_tac_toe.view.popups.multiplayer_names_popup.MultiplayerNamesPopupController;

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
        Platform.exit();
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
        showPopup(event);
    }
    private  void showPopup(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreensRoutes.POPUP_Login_OR_SIGNUP_ROUTE));
            Parent root = loader.load();
            root.setStyle(
                "-fx-background-radius: 15; " +
                "-fx-background-color: white; " + 
                "-fx-border-radius: 15; " +
                "-fx-border-color: lightgray; " +
                "-fx-border-width: 2;"
            );
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            LoginOrRegisterPopupController controller = loader.getController();
            Stage  LoginOrRegisterPopup = new Stage();
            
            LoginOrRegisterPopup.setTitle("Login or Register?");
            LoginOrRegisterPopup.initModality(Modality.APPLICATION_MODAL);
            LoginOrRegisterPopup.setScene(scene);
            LoginOrRegisterPopup.initStyle(StageStyle.TRANSPARENT);
            
             LoginOrRegisterPopup.showAndWait();
            
            int clickedAction =  controller.getAction();
            switch(clickedAction){
                case 1: Navigator.navigateToSignupScreen(event); break;
                case 2: Navigator.navigateToLoginScreen(event); break;
                case 3: Navigator.navigateToLandingScreen(event); break;
            }
        } catch (IOException ex) {
            Logger.getLogger(LandingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
