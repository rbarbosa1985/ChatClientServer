/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.Chats;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import modelo.ModeloTabelaUser;

/**
 *
 * @author beto_
 */
public class ClientMenssage implements Runnable {

    private Socket connection;
    private Boolean stop;
    private Util util;
    private JTextArea txtMensagens;
    private String nome;
    private ObjectInputStream inputStream;
    private List<String> users;
    private List<Chats> chats;
    private ModeloTabelaUser modeloUser;

    public ClientMenssage() {

    }

    public ClientMenssage(Socket connection, JTextArea txtMensagens, String nome, ObjectInputStream inputStream, List<Chats> chats) {
        this.connection = connection;
        this.inputStream = inputStream;
        this.txtMensagens = txtMensagens;
        this.chats = chats;
        util = new Util();
        this.nome = nome;
        stop = false;
        new Thread(this).start();
    }

    @Override
    public void run() {
        String msg = "";
        while (!"11111".equalsIgnoreCase(msg)) {
            msg = util.receivedMessage(inputStream);
            System.out.println(msg);
            if (msg != null) {
                String decode[] = util.decodeMessage(msg);
                if (util.localiza(chats, decode[1])) {
                    if ("1".equalsIgnoreCase(decode[0])) {
                        if (nome.equalsIgnoreCase(decode[1])) {
                            txtMensagens.append(nome + ": " + decode[2] + "\n\r");
                        }
                    } else if ("11".equalsIgnoreCase(decode[0])) {
                        if (nome.equalsIgnoreCase(decode[1])) {
                            txtMensagens.append(nome + ": " + decode[2] + "\n\r");
                        }
                    } else if ("01".equalsIgnoreCase(decode[0])) {
                        if (nome.equalsIgnoreCase(decode[1])) {
                            txtMensagens.append(nome + ": " + decode[2] + "\n\r");
                        }
                    }
                }
            }
        }
    }
}
