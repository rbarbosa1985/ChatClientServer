/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beto_
 */
public class ServerSide implements Runnable {

    private List<User> users;
    private ServerSocket server;
    private Boolean end;

    public ServerSide(ServerSocket server) {
        this.users = new ArrayList<User>();
        this.end = false;
        this.server = server;
        new Thread(this, "Thread Server").start();
    }

    @Override
    public void run() {
         try {
            while (true) {
                Socket connection;
                connection = server.accept();
                ClientConnection client = new ClientConnection(connection, users);
                synchronized (this) {
                    if (end) {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void stop() {
        try {
            if (!server.isClosed()) {
                server.close();
            }
            this.end = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

}
