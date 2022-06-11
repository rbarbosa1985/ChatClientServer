/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import entities.User;
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
    private Message message;
    private JTextArea txtLog;
    
    public ClientConnection(Socket connection, List<User> users, JTextArea txtLog) {
        this.connection = connection;
        this.users = users;
        this.txtLog = txtLog;
        message = new Message();
        stop = false;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            User user = new User();
            String msg;
            msg = message.receivedMessage(connection);
            txtLog.append("Novo Cliente Conectado: " + msg + "\r\n");
            System.out.println(msg);
            while (!msg.equals("slkjdl;kfjlak;jfkl;asdjflk;asdjfl;kasdjflk;asdjflasjdfl;jsadl;fj")) {
                msg = message.receivedMessage(connection);
                System.out.println(msg);
            }
            txtLog.append("Cliente Desconectado: " + msg + "\r\n");
            //Definir a comunicação inicial como: Nome:Porta:Password
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    
    String strMain = "Alpha, Beta, Delta, Gamma, Sigma";
    String[] arrSplit = strMain.split(", ");
    for (int i=0; i < arrSplit.length; i++)
    {
      System.out.println(arrSplit[i]);
    }
  }
    
        public void run() {
        try {
            String msg;
            OutputStream ou = this.con.getOutputStream();
            Writer ouw = new OutputStreamWriter(ou);
            BufferedWriter bfw = new BufferedWriter(ouw);
            clientes.add(bfw);
            System.out.println(bfw);
            System.out.println(clientes);
            nome = msg = bfr.readLine();
            System.out.println(nome);
            while (!"Sair".equalsIgnoreCase(msg) && msg != null) {
                msg = bfr.readLine();
                sendToAll(bfw, msg);
                System.out.println(msg);
                synchronized (this) {
                    if(this.stop){
                        System.out.println("socket.Servidor.run()");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {
        BufferedWriter bwS;

        for (BufferedWriter bw : clientes) {
            bwS = (BufferedWriter) bw;
            if (!(bwSaida == bwS)) {
                bw.write(nome + " -> " + msg + "\r\n");
                bw.flush();
            }
        }
    }
     */
    public synchronized void stop() {
        stop = true;
    }

}
