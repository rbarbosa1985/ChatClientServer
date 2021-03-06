/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beto_
 */
public class Connection implements Runnable {

    private ServerSocket server;
    private Boolean end;
    private String nome;
    private ArrayList<BufferedWriter> clientes;
    private volatile boolean isRunning = true;

    public Connection(ServerSocket server, String nome) {
        this.nome = nome;
        this.server = server;
        this.end = false;
        clientes = new ArrayList<BufferedWriter>();
        new Thread(this, "ThreadServer").start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket con;
                con = server.accept();
                Servidor t = new Servidor(con, clientes);
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
