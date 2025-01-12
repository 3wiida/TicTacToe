package tic_tac_toe.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;
import tic_tac_toe.view.landing.LandingScreenController;
import tic_tac_toe.view.onlineScreen.OnlineScreenController;
import tic_tac_toe.view.popups.choose_login_signup.LoginOrRegisterPopupController;

/**
 * FXML Controller class
 *
 * @author Khaled Mustafa
 */
public class LoginScreenController implements Initializable {


    @FXML
    private TextField txtFieldUserName;

    @FXML
    private TextField txtFieldUserPassword;

    @FXML
    private Button btnLogin;
    @FXML
    private ImageView backPhoto;
    
    String regex = "^[a-zA-Z0-9]+$"; 
    @FXML
    private Label lblLogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tooltip();
    }
    @FXML
    void LoginClicked(ActionEvent event) {
        Pair<String, String> userData = getUserData();
        if(!userData.getKey().isEmpty() && !userData.getValue().isEmpty()){
            System.err.println("Login Done");
            System.err.println(userData.getKey());
            System.out.println(userData.getValue());
            /* Network code  */
            transferUserNameToOnlineScreen(userData.getKey(), event);
        }else{
            showerrorAlert();
        }
    }
    /* Navigate to Landing Screen */
    @FXML
    private void photoClicked(MouseEvent event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void tooltip(){
        Tooltip userTip = new Tooltip("Please Enter Your Name");
        txtFieldUserName.setTooltip(userTip);
        
        Tooltip passwordTip = new Tooltip("Please Enter Your Password");
        txtFieldUserPassword.setTooltip(passwordTip);
    }
    private String getUserName(){
       return txtFieldUserName.getText().trim();
    }
    
    private String getUserPassword(){
       return txtFieldUserPassword.getText().trim();
    }
      private Pair<String, String> getUserData(){
         Pair<String, String> userData = new Pair(getUserName(), getUserPassword());
         return userData;
    }
    private  void  showerrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill All Information to Login!");
            alert.setHeaderText("Login Error");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle(
                    "-fx-background-color: #ffcccc;" + 
                    "-fx-border-color: #ff0000;" +    
                    "-fx-border-width: 2px;" +       
                    "-fx-padding: 10px;"             
            );
            dialogPane.lookup(".header-panel").setStyle(
                    "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #800000;" 
            );
            dialogPane.lookup(".content").setStyle(
                    "-fx-font-size: 16px;" +
                    "-fx-text-fill: #333333;" 
            );
            alert.showAndWait();
    }
    
    private void transferUserNameToOnlineScreen(String name, ActionEvent event){
        try {
           FXMLLoader Loader = new  FXMLLoader(Navigator.class.getResource(ScreensRoutes.ONLINE_SCREEN_ROUTE));
            Parent root = Loader.load();
            OnlineScreenController controller = Loader.getController();
            controller.setProfileName(name);
            Stage stage = ((Stage)((Node) event.getSource()).getScene().getWindow());        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error in transfer");
        }
    }
}
