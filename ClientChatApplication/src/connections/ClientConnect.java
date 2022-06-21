/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.Chats;
import form.JfrmMessage;
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
public class ClientConnect implements Runnable {

    private Socket connection;
    private Boolean stop;
    private Util util;
    private JTextArea txtMensagens;
    private String nome;
    private ObjectInputStream inputStream;
    private List<String> users;
    private List<Chats> chats;
    private ModeloTabelaUser modeloUser;

    public ClientConnect() {

    }

    public ClientConnect(Socket connection, JTextArea txtMensagens, String nome, ObjectInputStream inputStream, List<String> users, ModeloTabelaUser modeloUser, List<Chats> chats) {
        this.connection = connection;
        this.inputStream = inputStream;
        util = new Util();
        this.nome = nome;
        this.chats = chats;
        stop = false;
        this.users = users;
        new Thread(this).start();
    }

    public ClientConnect(Socket connection, String nome, ObjectInputStream inputStream, List<String> users, ModeloTabelaUser modeloUser, List<Chats> chats) {
        this.connection = connection;
        this.inputStream = inputStream;
        util = new Util();
        this.nome = nome;
        this.chats = chats;
        System.out.println("Nome: " + nome);
        stop = false;
        this.users = users;
        this.modeloUser = modeloUser;

        new Thread(this).start();
    }

    @Override
    public void run() {
        String msg = "";
        while (!"11111".equalsIgnoreCase(msg)) {
            msg = util.receivedMessage(inputStream);
            System.out.println("Mensagem: ::: " + msg);
            if (msg != null) {
                String decode[] = util.decodeMessage(msg);
                if ("111".equalsIgnoreCase(decode[0])) {
                    util.updateUsers(connection, inputStream, users);
                    modeloUser.atualizarTabela();
                }
                System.out.println("decode: " + decode.length + " ::: " +decode.toString());
                if (decode.length>1) {
                    if (!util.localiza(chats, decode[1])) {
                        if ("1".equalsIgnoreCase(decode[0])) {
                            JfrmMessage app = new JfrmMessage(decode, nome, connection, inputStream, users, modeloUser, chats);
                            app.setVisible(true);
                        } else if ("11".equalsIgnoreCase(decode[0])) {
                            JfrmMessage app = new JfrmMessage(decode, nome, connection, inputStream, users, modeloUser, chats);
                            app.setVisible(true);
                        } else if ("01".equalsIgnoreCase(decode[0])) {
                            JfrmMessage app = new JfrmMessage(decode, nome, connection, inputStream, users, modeloUser, chats);
                            app.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    public synchronized void stop() {
        stop = true;
    }

}
