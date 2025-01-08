package tic_tac_toe.view.popups.multiplayer_names_popup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 3wiida
 */
public class MultiplayerNamesPopupController implements Initializable {

    @FXML
    private Text headerTxt;
    @FXML
    private Button playBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField playerOneTextField;
    @FXML
    private TextField playerTwoTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        playBtn.setDisable(true);
        
        playerOneTextField.textProperty().addListener(
            (observable,oldValue,newValue) -> {
                if(newValue.isEmpty()){
                    playBtn.setDisable(true);
                }else{
                    if(!getPlayerTwoName().isEmpty()){
                        playBtn.setDisable(false);
                    }
                }
            }
        );
        
        playerTwoTextField.textProperty().addListener(
            (observable,oldValue,newValue) -> {
                if(newValue.isEmpty()){
                    playBtn.setDisable(true);
                }else{
                    if(!getPlayerOneName().isEmpty()){
                        playBtn.setDisable(false);
                    }
                }
            }
        );
        
        cancelBtn.setOnAction(event->{onCancelClicked(event);});
        playBtn.setOnAction(event->{onPlayClicked(event);});
    }    
    
    public String getPlayerOneName() {
        return playerOneTextField.getText();
    }

    public String getPlayerTwoName() {
        return playerTwoTextField.getText();
    }
    
    private void onPlayClicked(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    private void onCancelClicked(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
