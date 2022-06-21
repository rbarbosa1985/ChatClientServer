/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author beto_
 */
public class Chats {
    private String nome;
    private Boolean chat;
    
    public Chats(){
        
    }

    public Chats(String nome, Boolean chat) {
        this.nome = nome;
        this.chat = chat;
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

    @Override
    public String toString() {
        return "Chats{" + "nome=" + nome + ", chat=" + chat + '}';
    }
    
    
}
