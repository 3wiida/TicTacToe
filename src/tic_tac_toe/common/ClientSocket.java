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
    
    public static boolean initConnection(String ip){
        if(clientSocket == null){
            try {
                clientSocket = new Socket(ip,5005);
                dis = new DataInputStream(clientSocket.getInputStream());
                ps = new PrintStream(clientSocket.getOutputStream());
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
    
    public static void sendRequest(JSONObject msg){
        System.out.println("enter send msg function  " + msg.toString());
        ps.println(msg.toString());
    }
    
    public static JSONObject recieveResponse(){
        try {
            String response = dis.readLine();
            JSONObject responseJson = new JSONObject(response);
            return responseJson;
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
