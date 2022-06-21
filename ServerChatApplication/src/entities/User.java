/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author beto_
 */
public class User implements Serializable {
    private String nome;
    private ObjectOutputStream objectOutputStream;
    
    public User(){}

    public User(String nome, ObjectOutputStream objectOutputStream) {
        this.nome = nome;
        this.objectOutputStream = objectOutputStream;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public String toString() {
        return "User{" + "nome=" + nome + ", objectOutputStream=" + objectOutputStream + '}';
    }         
    
}
