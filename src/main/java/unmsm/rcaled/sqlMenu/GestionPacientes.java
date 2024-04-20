
package paquete1;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GestionPacientes extends coneccionSQL {

    // Consulta para insertar un nuevo paciente en la base de datos
    private String insertarPaciente = "INSERT INTO Pacientes (ID, ApellidoPat, ApellidoMat, Nombre, Edad, Telefono, Direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";

    // Método para registrar un nuevo paciente en la base de datos
    public void registrarPaciente() {
        try {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese el ID del paciente:");
            int id = entrada.nextInt();
            entrada.nextLine(); 
            System.out.println("Ingrese el apellido paterno del paciente:");
            String apellidoPat = entrada.nextLine();
            System.out.println("Ingrese el apellido materno del paciente:");
            String apellidoMat = entrada.nextLine();
            System.out.println("Ingrese el nombre del paciente:");
            String nombre = entrada.nextLine();
            System.out.println("Ingrese la edad del paciente:");
            int edad = entrada.nextInt();
            entrada.nextLine(); 
            System.out.println("Ingrese el teléfono del paciente:");
            String telefono = entrada.nextLine();
            System.out.println("Ingrese la dirección del paciente:");
            String direccion = entrada.nextLine();

            conn = DriverManager.getConnection(URL,usuario,contraseña);
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(insertarPaciente);
                ps.setInt(1, id);
                ps.setString(2, apellidoPat);
                ps.setString(3, apellidoMat);
                ps.setString(4, nombre);
                ps.setInt(5, edad);
                ps.setString(6, telefono);
                ps.setString(7, direccion);

                int filasAfectadas = ps.executeUpdate();
                System.out.println(filasAfectadas + " fila(s) insertada(s) correctamente.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConeccion();
        }
    }

}

