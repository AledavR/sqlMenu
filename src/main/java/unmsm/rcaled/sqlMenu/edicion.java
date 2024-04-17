package unmsm.rcaled.sqlMenu;

import java.util.Scanner;

import javax.annotation.processing.Generated;

import java.io.ObjectInputStream.GetField;
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

public class edicion extends coneccionSQL{

	public String borrarPorID = "DELETE FROM Doctores WHERE ID = ?";
	public String cambiarHora = "UPDATE Doctores SET Asistencia = 1"
		+ " WHERE Horario = ?;"
		+  "UPDATE Doctores SET Asistencia = 0"
		+ " WHERE Horario != ?";

	private String cambiarEnColumna(String columna){
		String query = "UPDATE Doctores SET " + columna + " = ? WHERE ID = ?";
		return query;
	}

	public void cambiarDatoColumna(String columna, String nuevoDato, int id){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				PreparedStatement ps =
					conn.prepareStatement(cambiarEnColumna(columna));
				ps.setString(1,nuevoDato);
				ps.setInt(2,id);
				int nFilasAfect = ps.executeUpdate();
				System.out.println(nFilasAfect + " filas fueron afectadas");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}

	public void borrarFila(int id){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(borrarPorID);
				ps.setInt(1,id);
				int nFilasAfect = ps.executeUpdate();
				System.out.println(nFilasAfect + " filas fueron afectadas");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}

	public void actualizarAsistencias(String hora){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(cambiarHora);
				ps.setString(1, hora);
				ps.setString(2, hora);
				int nFilasAfect = ps.executeUpdate();
				System.out.println(nFilasAfect + " filas fueron afectadas");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}

	
}
