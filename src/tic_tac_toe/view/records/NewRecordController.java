/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.records;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import tic_tac_toe.model.GameModeEnum;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.view.offline.offline_main.OfflineScreenController;

/**
 * FXML Controller class
 *
 * @author eslam
 */
public class NewRecordController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private Label userNamLabel;
    @FXML
    private Button watchButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(String recordName) {
        userNamLabel.setText(recordName);
        watchButton.setOnAction(event -> {
            try {
                Navigator.naviagteToGameBoardScreen(event, GameModeEnum.REPLAY_GAME, recordName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}