/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaestudiante;

import static com.mycompany.sistemaestudiante.Connect.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FelixDDP
 */
public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();

        // Trabajo con clase de conexion a BD
        PreparedStatement ps;
        ResultSet rs;
        Connection conect = getConnection();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante;";
        try{
            ps = conect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setCedula(rs.getString("cedula"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }
        catch (SQLException e){
            System.out.println("Ocurrio un error al listar los estudiantes: " + e.getMessage());
        }
        finally {
            try{
                conect.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean findById(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conect = getConnection();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?;";
        try{
            ps = conect.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            while (rs.next()){
                estudiante.setCedula(rs.getString("cedula"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        }
        finally {
            try{
                conect.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection conect = getConnection();
        String sql = "INSERT INTO estudiante(cedula, nombre, apellido, telefono, email) " +
                "VALUES(?, ?, ?, ?, ?);";
        try{
            ps = conect.prepareStatement(sql);
            ps.setString(1, estudiante.getCedula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getTelefono());
            ps.setString(5, estudiante.getEmail());
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Ocurrio un error al agregar estudiante: " + e.getMessage());
        }
        finally {
            try{
                conect.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean actualizarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection conect = getConnection();
        String sql = "UPDATE estudiante SET cedula=?, nombre=?, apellido=?, telefono=?, email=? " +
                "WHERE id_estudiante=?;";
        try{
            ps = conect.prepareStatement(sql);
            ps.setString(1, estudiante.getCedula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getTelefono());
            ps.setString(5, estudiante.getEmail());
            ps.setInt(6, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Ocurrio un error al actualizar estudiante: " + e.getMessage());
        }
        finally {
            try{
                conect.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection conect = getConnection();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?;";
        try{
            ps = conect.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Ocurrio un error al eliminar estudiante: " + e.getMessage());
        }
        finally {
            try{
                conect.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }
    
    public boolean findByCedula(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conect = getConnection();
        String sql = "SELECT * FROM estudiante WHERE cedula = ?;";
        try {
            ps = conect.prepareStatement(sql);
            ps.setString(1, estudiante.getCedula());
            rs = ps.executeQuery();
            while (rs.next()) {
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar estudiante por cédula: " + e.getMessage());
        } finally {
            try {
                conect.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    // Método para eliminar estudiante por cédula
    public boolean eliminarPorCedula(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conect = getConnection();
        String sql = "DELETE FROM estudiante WHERE cedula = ?;";
        try {
            ps = conect.prepareStatement(sql);
            ps.setString(1, estudiante.getCedula());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar estudiante por cédula: " + e.getMessage());
        } finally {
            try {
                conect.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }


    public static void main(String[] args){
        var estudianteDAO = new EstudianteDAO();
        // Buscar por ID
        /*var estudiante1 = new Estudiante(5);
        var existeEstudiabte = estudianteDAO.findById(estudiante1);
        if(existeEstudiabte)
            System.out.println("Estudiante encontrado: " + estudiante1);
        else
            System.out.println("No se encontro estudiante con ID : " + estudiante1.getIdEstudiante());*/

        // Agregar Estudiante nuevo
        /*var estudiante_new = new Estudiante("1717171717", "Jimena", "Suarez", "0912345678", "jimena@mail.com");
        var agregaEstudiante = estudianteDAO.agregarEstudiante(estudiante_new);

        if(agregaEstudiante)
            System.out.println("Estudiante agregado exitosamente: " + estudiante_new);
        else
            System.out.println("No se pudo agregar estudiante : " + estudiante_new);*/

        // Actualizar estudiante
        /*var edit_estudiante = new Estudiante(3, "0985274118", "Marianela",
                "Lopez", "0987654321", "marianela@mail.com");
        var actualizado = estudianteDAO.actualizarEstudiante(edit_estudiante);
        if(actualizado)
            System.out.println("Estudiante actualizado exitosamente: " + edit_estudiante);
        else
            System.out.println("No se pudo actualizar estudiante : " + edit_estudiante);*/

        // Eliminar estudiante
        var delete_estudiante = new Estudiante(4);
        var eliminado = estudianteDAO.eliminarEstudiante(delete_estudiante);
        if(eliminado)
            System.out.println("Estudiante eliminado exitosamente: " + delete_estudiante);
        else
            System.out.println("No se pudo eliminar estudiante : " + delete_estudiante);

        // Listar estudiantes
        System.out.println();
        System.out.println("Lista de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);
    }
}
