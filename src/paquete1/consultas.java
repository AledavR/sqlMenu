package paquete1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ramirez Chero Alejandro
 */

public class consultas extends coneccionSQL {

	public String datosCompletos = "SELECT * FROM Doctores";
        public String datosCompletos2 = "SELECT * FROM Pacientes";

	public void imprimirListaMedicos() {
		try {
			conn = DriverManager.getConnection(URL,usuario,contraseña);
			if (conn != null) {
				Statement state = conn.createStatement();
				ResultSet resSet = state.executeQuery(datosCompletos);
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			cerrarConeccion();
		}
	}
        
        //NUEVA METODO AGREGADO IMPRIMIR
        public void imprimirListaPaciente() {
		try {
			conn = DriverManager.getConnection(URL,usuario,contraseña);
			if (conn != null) {
				Statement state = conn.createStatement();
				ResultSet resSet = state.executeQuery(datosCompletos2);
				imprimirFilasPacientes(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			cerrarConeccion();
		}
	}

	public void buscarIDMedico(int id) {
		try {
			conn = DriverManager.getConnection(URL,usuario,contraseña);
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(buscarEnColumna("ID"));
				ps.setInt(1, id);
				ResultSet resSet = ps.executeQuery();
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			cerrarConeccion();
		}
	}

	public void mostrarDoctoresPresentes() {
		try {
			conn = DriverManager.getConnection(URL,usuario,contraseña);
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(buscarEnColumna("Asistencia"));
				ps.setInt(1, 1);
				ResultSet resSet = ps.executeQuery();
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			cerrarConeccion();
		}
	}

}
