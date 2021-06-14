/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Disciplina;
import db.Usuario;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Thiago
 */
public class DBListener implements ServletContextListener {
    
        public static final String CLASS_NAME = "org.sqlite.JDBC";
        public static final String URL = "jdbc:sqlite:escolapoo.db";
        public static Exception exception = null;

        public static Connection getConnection() throws Exception{
            return DriverManager.getConnection(URL);
        }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {            
            Class.forName(CLASS_NAME);
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            
            //Execute
            stat.execute(Usuario.getCreateStatement());
            stat.execute(Disciplina.getCreateStatement());
            
            if(Usuario.getUsers().isEmpty()){
                Usuario.insertUser("Thiago", "thiago", "123456");
            }
            
            stat.close();
            conn.close();
        } catch (Exception ex){
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
