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
public class Disciplina {
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiaAula() {
        return diaAula;
    }

    public void setDiaAula(String diaAula) {
        this.diaAula = diaAula;
    }

    public String getHoraAula() {
        return horaAula;
    }

    public void setHoraAula(String horaAula) {
        this.horaAula = horaAula;
    }

    public int getQtAulas() {
        return qtAulas;
    }

    public void setQtAulas(int qtAulas) {
        this.qtAulas = qtAulas;
    }

    public double getNotaP1() {
        return notaP1;
    }

    public void setNotaP1(double notaP1) {
        this.notaP1 = notaP1;
    }

    public double getNotaP2() {
        return notaP2;
    }

    public void setNotaP2(double notaP2) {
        this.notaP2 = notaP2;
    }

    public Disciplina(String nome, String diaAula, String horaAula, int qtAulas, double notaP1, double notaP2) {
        this.nome = nome;
        this.diaAula = diaAula;
        this.horaAula = horaAula;
        this.qtAulas = qtAulas;
        this.notaP1 = notaP1;
        this.notaP2 = notaP2;
    }
    String diaAula;
    String horaAula;
    int qtAulas;
    double notaP1;
    double notaP2;
    
    public static String getCreateStatement(){
        return "create table if not exists disciplina("
                    + "nome varchar(50) unique not null,"
                    + "diaAula varchar(50) not null,"
                    + "horaAula varchar(5) not null,"
                    + "qtAula int not null,"
                    + "notaP1 decimal(10,2) not null,"
                    + "notaP2 decimal(10,2) not null"
                + ")";
    }
    
    public static void insertDisciplina(String nome, String diaAula, String horaAula, int qtAula, double notaP1, double notaP2) throws Exception{
        Connection conn = DBListener.getConnection();
                
        String sql = "INSERT INTO disciplina(nome, diaAula, horaAula, qtAula, notaP1, notaP2)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stat = conn.prepareStatement(sql);
        
        stat.setString(1, nome);
        stat.setString(2, diaAula);
        stat.setString(3, horaAula);
        stat.setInt(4, qtAula);
        stat.setDouble(5, notaP1);
        stat.setDouble(6, notaP2);
        
        stat.execute();

        stat.close();
        conn.close();
    }
    
    public static void deleteDisciplina(String nome) throws Exception{
        Connection conn = DBListener.getConnection();
                
        String sql = "DELETE FROM disciplina WHERE nome=?";

        
        PreparedStatement stat = conn.prepareStatement(sql);
        
        stat.setString(1, nome);
        
        stat.execute();

        stat.close();
        conn.close();
    }
    
    public static void updateDisciplina(String nome, double notaP1, double notaP2) throws Exception{
        Connection conn = DBListener.getConnection();
                
        String sql = "UPDATE disciplina SET notaP1=?, notaP2=? WHERE nome=?";

        
        PreparedStatement stat = conn.prepareStatement(sql);
        
        stat.setDouble(1, notaP1);
        stat.setDouble(2, notaP2);
        stat.setString(3, nome);
        
        stat.execute();

        stat.close();
        conn.close();
    }
    
    public static ArrayList<Disciplina> getDisciplinaList() throws Exception{
        ArrayList<Disciplina> disciplinaList = new ArrayList<>();
        
        Connection conn = DBListener.getConnection();
        
        Statement stat = conn.createStatement();
        
        String sql = "SELECT * FROM disciplina";

        ResultSet rs = stat.executeQuery(sql);
        
        while(rs.next()){
            String nome = rs.getString("nome");
            String diaAula = rs.getString("diaAula");
            String horaAula = rs.getString("horaAula");
            int qtAula = rs.getInt("qtAula");
            double notaP1 = rs.getDouble("notaP1");
            double notaP2 = rs.getDouble("notaP2");
            disciplinaList.add( new Disciplina(nome, diaAula, horaAula, qtAula, notaP1, notaP2));
        }

        stat.close();
        conn.close();
        
        return disciplinaList; 
    }
}
