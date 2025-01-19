/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.popups.invitationpopup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class InvitationPopupController implements Initializable {

    @FXML
    private Label requestlbl;
    private boolean isInvietationAccecpted = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setRequestLabel(String request){
        requestlbl.setText(request);
    }
    
    public boolean isInvitaitonAccepted(){
        return isInvietationAccecpted;
    }
    
    @FXML
    private void onCancelClicked(ActionEvent event) {
        System.out.println("cancel clicked");
        isInvietationAccecpted = false;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onConfirmClicked(ActionEvent event) {
        isInvietationAccecpted = true;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
