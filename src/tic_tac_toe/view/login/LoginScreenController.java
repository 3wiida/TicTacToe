package tic_tac_toe.view.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Khaled Mustafa
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Label lblLogin;

    @FXML
    private TextField txtFieldUserName;

    @FXML
    private TextField txtFieldUserPassword;

    @FXML
    private Button btnLogin;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tooltip();
    }    
        
    
    private void tooltip(){
        Tooltip userTip = new Tooltip("Please Enter Your Name");
        txtFieldUserName.setTooltip(userTip);
        
        Tooltip passwordTip = new Tooltip("Please Enter Your Password");
        txtFieldUserPassword.setTooltip(passwordTip);
    }
    @FXML
    void LoginClicked(ActionEvent event) {

    }
   
    
}
