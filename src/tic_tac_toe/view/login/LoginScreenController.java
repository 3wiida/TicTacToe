package tic_tac_toe.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tic_tac_toe.navigation.Navigator;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tooltip();
    }
    
    @FXML
    void LoginClicked(ActionEvent event) {
        if(!txtFieldUserName.getText().trim().isEmpty() && !txtFieldUserPassword.getText().trim().isEmpty()){
           if(txtFieldUserName.getText().matches(regex) && txtFieldUserPassword.getText().matches(regex)){
               System.err.println("Login Done");
               /* 
                    Send data to server 
               */
           }
        }else{
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
}
