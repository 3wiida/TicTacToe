/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.popups.serverConnectionpopup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tic_tac_toe.common.ClientSocket;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class ServerConnectionPopupController implements Initializable {
    
    private boolean isConnectionSuccess;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label Connectionlbl;
    @FXML
    private TextField ipTF;
    @FXML
    private Text errorTV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    
    public boolean isConnectionSuccess(){
        return isConnectionSuccess;
    }
    
    
    @FXML
    private void onConnectClicked(ActionEvent event){
        String serverIP = ipTF.getText().trim();
        boolean isConnected = ClientSocket.initConnection(serverIP);
        isConnectionSuccess = isConnected;
        if(isConnected){
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }else{
            errorTV.setVisible(true);
        }
    }
    
    @FXML
    private void onCancelClicked(ActionEvent event){
        isConnectionSuccess = false;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
