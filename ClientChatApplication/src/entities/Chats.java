/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import form.JfrmMessage;

/**
 *
 * @author beto_
 */
public class Chats {
    private String nome;
    private Boolean chat;
    private JfrmMessage app;
    
    public Chats(){
        
    }

    public Chats(String nome, Boolean chat) {
        this.nome = nome;
        this.chat = chat;
    }
    
    public Chats(String nome, Boolean chat, JfrmMessage app) {
        this.nome = nome;
        this.chat = chat;
        this.app = app;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getChat() {
        return chat;
    }

    public void setChat(Boolean chat) {
        this.chat = chat;
    }

    public JfrmMessage getApp() {
        return app;
    }

    public void setApp(JfrmMessage app) {
        this.app = app;
    }

    
    
    @Override
    public String toString() {
        return "Chats{" + "nome=" + nome + ", chat=" + chat + '}';
    }
    
    
}
