/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import org.json.JSONObject;
import tic_tac_toe.navigation.Navigator;

/**
 *
 * @author 3wiida
 */
public class ClientSocket {
    
    private static Socket clientSocket;
    private static DataInputStream dis;
    private static PrintStream ps;
    public static BlockingQueue<JSONObject> responses;
    
    
    public static boolean initConnection(String ip){
        if(clientSocket == null){
            try {
                clientSocket = new Socket(ip,5005);
                dis = new DataInputStream(clientSocket.getInputStream());
                ps = new PrintStream(clientSocket.getOutputStream());
                responses = new LinkedBlockingDeque<>();
                System.out.println("conection success");
                return true;
            } catch (IOException ex) {
                System.out.println("Can't connect to the server");
                return false;
            }
        }
        return true;
    }
    
    public static boolean isServerConnected(){
        if(clientSocket != null){
            return clientSocket.isConnected();
        }
        return false;
    }
    
    public static void sendRequest(JSONObject msg){
        ps.println(msg.toString());
        ps.flush();
    }
    
    public static void recieveResponse(){
        new Thread(
            ()->{
                 while(true){
                    try {
                        String response = dis.readLine();
                        JSONObject responseJson = new JSONObject(response);
                        System.out.println(responseJson);
                        if(responseJson.toString().contains("serverClosed")){
                           System.out.println("recieve server closed");
                           CurrentPlayer.clear();
                           Platform.runLater(()->{
                               try {
                                   Navigator.navigateToServerShutdownScreen();
                               } catch (IOException ex) {
                                   Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           });
                           closeServerSocket();
                           break;
                        }else{
                            responses.put(responseJson);
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getLocalizedMessage());
                        break;
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getLocalizedMessage());
                        break;
                    }
                }   
            }   
        ).start();
        
    }
    
    public static boolean checkSocketStat(){
        return clientSocket.isClosed();
    }
    
    public static void closeServerSocket() {
        try {
            clientSocket.close();
            dis.close();
            ps.close();
            clientSocket = null;
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private  static void  showServerClosedAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Server Closed Alert");
        alert.setContentText("Server is down, you can play offline only");
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
        alert.setOnCloseRequest((event)->{
            try {
                System.out.println("enter closed request");
                Navigator.navigateToLandingScreen();
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        alert.showAndWait();
    }
}
