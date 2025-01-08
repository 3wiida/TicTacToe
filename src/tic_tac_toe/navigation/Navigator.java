/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.navigation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 3wiida
 */
public class Navigator {

    public static void navigateToOfflineScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.OFFLINE_SCREEN_ROUTE));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToWaitingPopup(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);
        popupStage.setScene(scene);

        popupStage.show();
    }

}
