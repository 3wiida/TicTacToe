/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.navigation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author 3wiida
 */
public class Navigator {
    
    public static void navigateToLandingScreen(Event event) throws IOException{
        //TODO
    }
    
    public static void navigateToOfflineScreen(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.OFFLINE_SCREEN_ROUTE));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void navigateToModeSelectionScreen(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.MODE_SELECTION_SCREEN));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
