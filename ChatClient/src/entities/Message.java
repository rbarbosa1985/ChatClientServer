/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author beto_
 */
public class Message implements Serializable {
    public String nome;
    public String msg;
    public String token;
    
    public Message(){
        
    }

    public Message(String nome, String msg) {
        this.nome = nome;
        this.msg = msg;
    }
    
    public void TokenServer() {
        this.token = UUID.randomUUID().toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" + "nome=" + nome + ", msg=" + msg + ", token=" + token + '}';
    }
    
    
    
}
