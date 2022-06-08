/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatcliendevmedia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author beto_
 */
public class ChatClienDevMedia extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JTextArea texto;
    private JTextField txtMsg;
    private JButton btnSend;
    private JButton btnSair;
    private JLabel lblHistorico;
    private JLabel lblMsg;
    private JPanel pnlContent;
    private Socket socket;
    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;
    private JTextField txtIP;
    private JTextField txtPorta;
    private JTextField txtNome;
    private Cliente cliente;

    public ChatClienDevMedia() throws IOException {
        JLabel lblMessage = new JLabel("Verificar!");
        txtIP = new JTextField("127.0.0.1");
        txtPorta = new JTextField("7777");
        txtNome = new JTextField("Cliente");
        Object[] texts = {lblMessage, txtIP, txtPorta, txtNome};
        JOptionPane.showMessageDialog(null, texts);
        pnlContent = new JPanel();
        texto = new JTextArea(10, 20);
        texto.setEditable(false);
        texto.setBackground(new Color(240, 240, 240));
        txtMsg = new JTextField(20);
        lblHistorico = new JLabel("Histórico");
        lblMsg = new JLabel("Mensagem");
        btnSend = new JButton("Enviar");
        btnSend.setToolTipText("Enviar Mensagem");
        btnSair = new JButton("Sair");
        btnSair.setToolTipText("Sair do Chat");
        btnSend.addActionListener(this);
        btnSair.addActionListener(this);
        btnSend.addKeyListener(this);
        txtMsg.addKeyListener(this);
        JScrollPane scroll = new JScrollPane(texto);
        texto.setLineWrap(true);
        pnlContent.add(lblHistorico);
        pnlContent.add(scroll);
        pnlContent.add(lblMsg);
        pnlContent.add(txtMsg);
        pnlContent.add(btnSair);
        pnlContent.add(btnSend);
        pnlContent.setBackground(Color.LIGHT_GRAY);
        texto.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        setTitle(txtNome.getText());
        setContentPane(pnlContent);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(250, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void conectar() throws IOException {
        socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));
        cliente = new Cliente(txtNome.getText(), txtPorta.getText(), "123456");
        String msg = "Teste";
        ObjectOutputStream teste = new ObjectOutputStream(socket.getOutputStream());
        teste.flush();
        teste.writeObject(msg);

    }

    public void enviarMensagem(String msg) throws IOException {

        ObjectOutputStream teste = new ObjectOutputStream(socket.getOutputStream());
        teste.flush();
        teste.writeObject(msg);
        texto.append(txtNome.getText() + " diz -> " + txtMsg.getText() + "\r\n");

        txtMsg.setText("");
    }

    public void escutar() throws IOException {
        String msg = "";

        while (!"Sair".equalsIgnoreCase(msg)) {

            try {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                msg = (String) inputStream.readObject();
                texto.append(msg + "\r\n");
            } catch (IOException ex) {
                System.out.println("Erro recebendo messagem: " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro recebendo messagem na Classe: " + ex.getMessage());
            }
        }
    }

    public void sair() throws IOException, InterruptedException {

        ObjectOutputStream teste = new ObjectOutputStream(socket.getOutputStream());
        teste.flush();
        teste.writeObject("slkjdl;kfjlak;jfkl;asdjflk;asdjfl;kasdjflk;asdjflasjdfl;jsadl;fj");
        texto.append("Desconectado \r\n");
        socket.close();
        Thread.sleep(200);
        dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getActionCommand().equals(btnSend.getActionCommand())) {
                enviarMensagem(txtMsg.getText());
            } else if (e.getActionCommand().equals(btnSair.getActionCommand())) {
                sair();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatClienDevMedia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                enviarMensagem(txtMsg.getText()); // TODO Auto-generated catch block
            } catch (IOException ex) {
                Logger.getLogger(ChatClienDevMedia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ChatClienDevMedia app = new ChatClienDevMedia();
        app.conectar();
        app.escutar();
    }

}
