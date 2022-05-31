/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.net.ServerSocket;
import socket.ConnectionCliente;

/**
 *
 * @author beto_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            ServerSocket servidor = new ServerSocket(7000);
            System.out.println("Servidor iniciado...");

            while (true) {

                ConnectionCliente cli = new ConnectionCliente(servidor.accept());
                cli.start();

            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro!");
        }
    }

}
