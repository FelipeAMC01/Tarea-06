/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaestudiante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FelixDDP
 */
public class Connect {
    public static Connection getConnection(){
        Connection connection = null;
        // definir datos para crear conexion
        var database = "estudiantes_app";
        var url = "jdbc:mysql://localhost:3306/" + database;
        var username = "root";
        var password = "";

        // Cargar clase del driver mysql en memoria
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
        //catch(Exception e){
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en conexion a BD: " + e.getMessage());
        }

        return connection;
    }
}
