/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemaestudiante;

import java.util.Scanner;

/**
 *
 * @author FelixDDP
 */
public class SistemaEstudiante {

    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        var salir = false;
        var estudianteDAO = new EstudianteDAO();

        // MUESTRA MENU
        while (!salir){
            try{
                muestramenu();
                salir = ejecutarOpciones(consola, estudianteDAO);
            }
            catch (Exception e){
                System.out.println();
                System.out.println("Error en ejecución: " + e.getMessage());
                System.out.println();
            }
        }

    }

    private static void muestramenu(){
        System.out.println();
        System.out.println("""
                ***** SISTEMA DE ESTUDIANTES *****
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar estudiante
                4. Actualizar estudiante
                5. Eliminar estudiante
                6. Buscar estudiante por cedula
                7. Eliminar estudiante por cedula
                8. Salir
                """);
        System.out.print("Por favor, ingrese una opción: ");
        System.out.println();
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        System.out.println();
        boolean salir = false;
        // Validar opciones ingresadas
        switch (opcion){
            case 1 -> { // Listar estudiantes
                System.out.println();
                System.out.println(".:: Lista de estudiantes ::.");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { // Buscar por ID
                System.out.println("Ingrese ID de estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var existeEstudiante = estudianteDAO.findById(estudiante);
                if(existeEstudiante)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("No se encontro estudiante con ID : " + estudiante.getIdEstudiante());
            }
            case 3 -> { // Agregar estudiante
                System.out.println(".:: Agregar Estudiante ::.");
                System.out.print("Cedula: ");
                var cedula = consola.nextLine();
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();

                var nuevoEstudiante = new Estudiante(cedula, nombre, apellido, telefono, email);
                var agregaEstudiante = estudianteDAO.agregarEstudiante(nuevoEstudiante);

                if(agregaEstudiante)
                    System.out.println("Estudiante agregado exitosamente: " + nuevoEstudiante);
                else
                    System.out.println("No se pudo agregar estudiante : " + nuevoEstudiante);

            }

            case 4 -> { // Actualizar estudiante
                System.out.println(".:: Actualizar Estudiante ::.");
                System.out.println("Ingrese ID de estudiante a actualizar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Cedula: ");
                var cedula = consola.nextLine();
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();

                var editaEstudiante = new Estudiante(idEstudiante, cedula, nombre, apellido, telefono, email);
                var actualizado = estudianteDAO.actualizarEstudiante(editaEstudiante);
                if(actualizado)
                    System.out.println("Estudiante actualizado exitosamente: " + editaEstudiante);
                else
                    System.out.println("No se pudo actualizar estudiante : " + editaEstudiante);
            }
            case 5 -> { // Eliminar estudiante
                System.out.println(".:: Eliminar Estudiante ::.");
                System.out.println("Ingrese ID de estudiante a eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var eliminaEstudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(eliminaEstudiante);
                if(eliminado)
                    System.out.println("Estudiante eliminado exitosamente: " + eliminaEstudiante);
                else
                    System.out.println("No se pudo eliminar estudiante : " + eliminaEstudiante);
            }
            
            case 6 -> {
                System.out.println("Ingrese cédula del estudiante a buscar: ");
                var cedula = consola.nextLine();
                var estudiante = new Estudiante();
                estudiante.setCedula(cedula);
                var existeEstudiante = estudianteDAO.findByCedula(estudiante);
                if (existeEstudiante)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("No se encontró estudiante con cédula: " + cedula);
            }
            
            case 7 -> {
                System.out.println(".:: Eliminar Estudiante por Cédula ::.");
                System.out.println("Ingrese cédula de estudiante a eliminar: ");
                var cedula = consola.nextLine();
                var eliminaEstudiante = new Estudiante();
                eliminaEstudiante.setCedula(cedula);
                var eliminado = estudianteDAO.eliminarPorCedula(eliminaEstudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado exitosamente con cédula: " + cedula);
                else
                    System.out.println("No se pudo eliminar estudiante con cédula: " + cedula);
            }
            
            case 8 -> { // Salir de la app
                System.out.println("Gracias, hasta pronto!!!");
                salir = true;
            }
            default -> System.out.println("Opcion ingresada no existe, por favor ingrese nuevamente.");
        }
        return salir;
    }
    
}
