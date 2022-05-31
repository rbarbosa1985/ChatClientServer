/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entities.Message;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            //Declarar o socket cliente
            Socket client = new Socket("127.0.0.1", 7000);
            System.out.println("Cliente iniciado");

            Message msg = new Message("Roberto Barbosa", "Oi teste!");
            //Fluxo de dados de envio
            ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Antes do server: " + msg.toString());
            saida.writeObject(msg);

            //
            ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
            Message obj = (Message) entrada.readObject();
            System.out.println("Depois do server: " + obj.toString());

            client.close();

            System.out.println("Cliente finalizado");

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conexao");
        }
    }

}
