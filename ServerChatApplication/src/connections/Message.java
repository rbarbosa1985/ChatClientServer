/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author beto_
 */
public class Message {
    
    public Message(){}
    
    public boolean sendMessage(Socket connection, String message){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            outputStream.writeObject(message);
            return true;
        }catch(IOException ex){
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }
        
        return false;
    }
    
    public String receivedMessage(Socket connection) {
        String message = null;
        
        try{
            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
            message = (String) inputStream.readObject();
        }catch(IOException ex){
            System.out.println("Erro recebendo messagem: " + ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println("Erro recebendo messagem na Classe: " + ex.getMessage());
        }
        
        return message;
    }
}
