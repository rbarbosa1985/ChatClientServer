/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import entities.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beto_
 */
public class ConnectionCliente extends Thread {

    Socket client;

    public ConnectionCliente(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            System.out.println("Cliente conectado: " + this.getId() + " : " + client.getInetAddress());
            //Fluxo de Dados
            ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
            Message msg = (Message) entrada.readObject();
            System.out.println(msg.toString());
            msg.TokenServer();
            ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
            saida.writeObject(msg);
            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
