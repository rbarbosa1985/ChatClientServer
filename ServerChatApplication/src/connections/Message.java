/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author beto_
 */
public class Message {
    
    public Message(){}
    
    public boolean sendMessage(Socket connection, String[] message){
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
    
    public boolean sendToAllMessage(Socket connection, String message, List<User> users){
        try{
            for (User user : users){
                user.getObjectOutputStream().flush();
                user.getObjectOutputStream().writeObject(message);
            }
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
    
    public String[] decodeMessage(String msg){
        String[] decoded = msg.split("; ");
        return decoded;
    }
    
    //String strMain = "Alpha, Beta, Delta, Gamma, Sigma";
    //String[] arrSplit = strMain.split(", ");
}
