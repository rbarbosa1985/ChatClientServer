/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.Chats;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author beto_
 */
public class Util {

    public Util(){
        
    }
    
    public void sendMessage2(Socket connection, String message, String user) {
        String msg = "01;" + user + ";" + message + ";";
        System.out.println(msg);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            outputStream.writeObject(msg);   
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }
    }
    
    public boolean sendMessage(Socket connection, String message) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            outputStream.writeObject(message);
            return true;
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }

        return false;
    }

    public void updateUsers(Socket connection, ObjectInputStream inputStream, List<String> users) {
        users.clear();
        String message = "Todos";
        this.sendMessage(connection, "111");
        while (!message.equals("OK") || (message == null)) {
            System.out.println(message);
            users.add(message);
            message = this.receivedMessage(inputStream);
            System.out.println("Oi" + message);
        }
    }

    public String receivedMessage(ObjectInputStream inputStream) {
        String message = null;
        try {
            message = (String) inputStream.readObject();
        } catch (IOException ex) {
            System.out.println("Erro recebendo messagem: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro recebendo messagem na Classe: " + ex.getMessage());
        }

        return message;
    }

    public String[] decodeMessage(String msg) {
        String[] decoded = msg.split(";");
        return decoded;
    }

    public void close(Socket connection) {
        try {
            if (!connection.isClosed()) {
                this.sendMessage(connection, "1111");
                connection.close();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
    
    public boolean localiza(List<Chats> chats, String nome){
        System.out.println("Localiza: " + nome);
        for (Chats chat : chats){
            if(chat.getNome().equals(nome)){
                System.out.println("Localiza1: " + nome);
                return true;
            }
        }
        Chats chat = new Chats(nome, true);
        chats.add(chat);
        System.out.println("Localiza2: " + nome);
        return false;
    }
}
