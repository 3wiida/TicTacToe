/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.common.CurrentPlayer;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.navigation.ScreensRoutes;

/**
 *
 * @author 3wiida
 */
public class Tic_tac_toe extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ScreensRoutes.LANDING_SCREEN_ROUTE));
        Navigator.setMainStage(primaryStage);
        
        primaryStage.setTitle("Tic Tac Toe");
        Scene scene = new Scene(root,860,600);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        
//        String sound = "/tic_tac_toe/assets/Track 01.mp3"; 
//        Media media = new Media(getClass().getResource(sound).toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
//        mediaPlayer.setAutoPlay(true);
        primaryStage.show();
        
        
    }

    @Override
    public void stop() throws Exception {
        if(CurrentPlayer.getPlayer() != null){
            JSONObject logutRequest = new JSONObject();
            logutRequest.put("type", "logout");
            ClientSocket.sendRequest(logutRequest);
            new Thread(
                ()->{
                    try {
                        JSONObject logoutResponse = ClientSocket.responses.take();
                        boolean isOk = logoutResponse.getBoolean("isOk");
                        if(isOk){
                            ClientSocket.closeServerSocket();
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } 
                }
            ).start();
        } 
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
//x
