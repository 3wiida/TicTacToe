/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tic_tac_toe.navigation.ScreensRoutes;

/**
 *
 * @author 3wiida
 */
public class Tic_tac_toe extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ScreensRoutes.LANDING_SCREEN_ROUTE));
        //root.getStylesheets().add(getClass().getResource("/tic_tac_toe/view/offline/offline_main/offlinescreen.css").toString());
        Scene scene = new Scene(root, 860, 600);


        /*Parent root = FXMLLoader.load(getClass().getResource("/tic_tac_toe/view/offline/offline_with_computer/ModeSelectionScreen.fxml"));
        Scene scene = new Scene(root, 600, 450);
        scene.getStylesheets().add(getClass().getResource("/tic_tac_toe/view/offline/offline_with_computer/modeselectionscreen.css").toExternalForm());*/

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
