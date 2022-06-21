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
public class Util {

    public Util() {
    }

    public boolean sendMessage(Socket connection, String message, ObjectOutputStream outputStream) {
        try {
            outputStream.flush();
            outputStream.writeObject(message);
            return true;
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }

        return false;
    }

    public void sendMessage(String[] message, List<User> users, User user) {
        try {
            String msg = "01;" + user.getNome()+ ";" + message[2] + ";";
            
            for (User u : users) {
                if (u.getNome().equals(message[1])) {
                    u.getObjectOutputStream().writeObject(msg);
                    u.getObjectOutputStream().flush();
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }
    }

    public boolean sendToAllMessage(Socket connection, String message, List<User> users, User user) {
        try {
            String msg = "";
            if ("111;00;00;00;".equalsIgnoreCase(message)){
                msg = message;
            }else{ 
                msg = ("11;" + user.getNome() + ";" + message + ";");
            }
            for (User user2 : users) {
                if (!user2.getNome().equals(user.getNome())) {
                    user2.getObjectOutputStream().writeObject(msg);
                    user2.getObjectOutputStream().flush();
                }
            }
            return true;
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }

        return false;
    }

    public String receivedMessage(Socket connection) {
        String message = null;

        try {
            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
            message = (String) inputStream.readObject();
        } catch (IOException ex) {
            System.out.println("Erro recebendo messagem: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro recebendo messagem na Classe: " + ex.getMessage());
        }

        return message;
    }

    public boolean listUsers(List<User> users, ObjectOutputStream outputStream, String nome) {
        try {
            System.out.println("List: " + users);
            for (User user : users) {
                if (!nome.equals(user.getNome())) {
                    outputStream.writeObject(user.getNome());
                    outputStream.flush();
                }
            }
            System.out.println("connections.Util.listUsers()");
            outputStream.writeObject("OK");
            outputStream.flush();
            return true;
        } catch (IOException ex) {
            System.out.println("Erro mandando mensagem: " + ex.getMessage());
        }

        return false;
    }

    public boolean testUser(String name, List<User> users) {
        for (User user : users) {
            if (user.getNome().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public String[] decodeMessage(String msg) {
        String[] decoded = msg.split(";");
        return decoded;
    }

}
