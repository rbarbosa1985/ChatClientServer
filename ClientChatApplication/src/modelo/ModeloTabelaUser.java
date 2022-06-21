/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author beto_
 */
public class ModeloTabelaUser extends AbstractTableModel {
    
    private static final int COLUNA_NOME = 0;
    
    private String [] colunas = new String[] {"Contatos"};
    
    
    private List<String> users;
    
    public ModeloTabelaUser(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    
    public String getUser(int indice){
        return users.get(indice);
    }
        
    public void atualizarTabela(){
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
    @Override
    public Object getValueAt(int row, int col){
        String user = users.get(row);
        
        switch(col){
            case COLUNA_NOME:
                return user;
        }
        
        return "";
    }
}
