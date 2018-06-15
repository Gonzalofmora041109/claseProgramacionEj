/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RLCR
 */
public class Conexion {
    
    private static Connection  cnx=null;
     
    public static  Connection obtener()throws SQLException,ClassNotFoundException {
        if(cnx==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                cnx=DriverManager.getConnection("jdbc:mysql://localhost/cliente","root","root");
            
            }catch(SQLException e){
                throw new SQLException (e);
            
            }
        }
    return cnx;
    }
     public List <String>select(String sql) throws ClassNotFoundException {
        List <String> lista=new ArrayList();
         try{
            cnx=obtener();
            Statement sentencia=cnx.createStatement();
            ResultSet rs=sentencia.executeQuery(sql);
            
            while(rs.next()){
                lista.add(rs.getString("nombre"));
                
            }
            
        } catch(SQLException e){
            e.printStackTrace();
            
            
        } 
         
         return lista;
    
    }
    
}
