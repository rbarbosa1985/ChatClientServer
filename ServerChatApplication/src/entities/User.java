/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.BufferedWriter;
import java.io.Serializable;

/**
 *
 * @author beto_
 */
public class User implements Serializable {
    private String nome;
    private String password;
    private Integer port;
    private BufferedWriter bufferedWriter;
    
    public User(){}

    public User(String nome, String password, Integer port, BufferedWriter bufferedWriter) {
        this.nome = nome;
        this.password = password;
        this.port = port;
        this.bufferedWriter = bufferedWriter;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public String toString() {
        return "User{" + "nome=" + nome + ", password=" + password + ", port=" + port + ", bufferedWriter=" + bufferedWriter + '}';
    }  
    
}
