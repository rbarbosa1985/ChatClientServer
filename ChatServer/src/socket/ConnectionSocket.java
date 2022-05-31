/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author beto_
 */
public class ConnectionSocket {
    
    public void Connection(Integer port){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
