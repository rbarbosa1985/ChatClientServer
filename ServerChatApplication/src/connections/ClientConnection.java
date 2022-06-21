/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author beto_
 */
public class ClientConnection implements Runnable {

    private Socket connection;
    private List<User> users;
    private Boolean stop;
    private Util message;
    private JTextArea txtLog;
    private ObjectOutputStream outputStream;

    public ClientConnection(Socket connection, List<User> users, JTextArea txtLog) throws IOException {
        this.connection = connection;
        this.users = users;
        this.txtLog = txtLog;
        message = new Util();
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        stop = false;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            User user = new User();
            String msg;
            msg = message.receivedMessage(connection);
            user.setNome(msg);
            user.setObjectOutputStream(outputStream);
            if (message.testUser(msg, users)) {
                users.add(user);
                System.out.println(users);
                message.listUsers(users, outputStream, msg);
                txtLog.append("Cliente Conectado: " + user.getNome() + "\r\n");
                message.sendToAllMessage(connection, "111;00;00;00;", users, user);
                while (true) {
                    msg = message.receivedMessage(connection);
                    String[] decoded = message.decodeMessage(msg);
                    if (decoded[0].equals("1111")){
                        break;
                    } else if ("111".equalsIgnoreCase(decoded[0])){
                        message.listUsers(users, user.getObjectOutputStream(), user.getNome());
                    }else if("11".equalsIgnoreCase(decoded[0])){
                        message.sendToAllMessage(connection, decoded[1], users, user);
                    }else if("01".equalsIgnoreCase(decoded[0])){
                        if ("Todos".equalsIgnoreCase(decoded[1])){
                            message.sendToAllMessage(connection, decoded[2], users, user);
                        }else{
                            message.sendMessage(decoded, users, user);
                        }
                        
                    }else if("1".equalsIgnoreCase(decoded[0])){
                        message.sendMessage(decoded, users, user);
                    }
                }
                txtLog.append("Cliente Desconectado: " + user.getNome() + "\r\n");
                message.sendMessage(connection, "11111", outputStream);
                for (User cli : users) {
                    if (cli.getNome().equals(user.getNome())) {
                        users.remove(cli);
                    }
                }
                message.sendToAllMessage(connection, "111;00;00;00;", users, user);
            }else{
                message.sendMessage(connection, "NOT", outputStream);
                stop = true;
            }
            //Definir a comunicação inicial como: Nome:Porta:Password
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void stop() {
        stop = true;
    }

}
