/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DBListener;

/**
 *
 * @author Thiago
 */
public class Usuario {
    private String name;
    private String login;
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS Users("
                + "login VARCHAR(50) UNIQUE NOT NULL,"
                + "nome VARCHAR(100) NOT NULL,"
                + "password_hash LONG NOT NULL"
                + ")";
    }
    
    public static void insertUser(String nome, String login, String passw) throws Exception{
        
        Connection conn = DBListener.getConnection();
        
        String sql = "INSERT INTO Users(login, nome, password_hash)"
                + "VALUES(?, ?, ?)";
        
        PreparedStatement stat = conn.prepareStatement(sql);
        
        stat.setString(1, login);
        stat.setString(2, nome);
        stat.setInt(3, passw.hashCode());
                
        stat.execute();
        
        stat.close();
        conn.close();
    }
    
    public static Usuario getUser(String login, String passw) throws Exception{
        Usuario user = null;
       
        Connection conn = DBListener.getConnection();
        
        String sql = "SELECT * FROM Users WHERE login=? AND password_hash=?";
        
        PreparedStatement stat = conn.prepareStatement(sql);
        
        stat.setString(1, login);
        stat.setInt(2, passw.hashCode());
        
        ResultSet rs = stat.executeQuery();
        
        if(rs.next()){
            String nome = rs.getString("nome");
            user = new Usuario(nome, login);
        }
        
        stat.close();
        conn.close();
        
        return user; 
    }
    
    public static ArrayList<Usuario> getUsers() throws Exception{
        ArrayList<Usuario> userList = new ArrayList<>();
        
        Connection conn = DBListener.getConnection();
        
        Statement stat = conn.createStatement();
        
        String sql = "SELECT * FROM Users";

        ResultSet rs = stat.executeQuery(sql);
        
        while(rs.next()){
            String login = rs.getString("login");
            String nome = rs.getString("nome");
            userList.add( new Usuario(nome, login));
        }

        stat.close();
        conn.close();
        
        return userList; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Usuario(String name, String login) {
        this.name = name;
        this.login = login;
    }
}
