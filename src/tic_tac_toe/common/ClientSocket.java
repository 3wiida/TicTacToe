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
import org.json.JSONObject;

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
                        responses.put(responseJson);
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
}
