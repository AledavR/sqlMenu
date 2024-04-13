package unmsm.rcaled.sqlMenu;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class consultas {

	String dbURL = "jdbc:sqlserver://localhost:1433;database=PersonalMedico";
	String user = "sa";
	String pass = "Password123";

	String[] headers = {"ID","ApellidoPat","Nombre","Especialidad","Horario"};
	
	String datosCompletos = "SELECT * FROM Doctores";

	Scanner input = new Scanner(System.in);

	Connection conn = null;

	private String buscarEnColumna(String columna){
		String query = "SELECT * FROM Doctores WHERE "
			+ columna + " = ?";
		return query;
	}

	public String eleccionDeColumna(Scanner input){
		System.out.println("Que columna desea editar:");
		System.out.println("1) ApellidoPat");
		System.out.println("2) Nombre");
		System.out.println("3) Especialidad");
		System.out.println("4) Horario");
		System.out.print("Opcion elegida");
		int opcion = input.nextInt();
		switch(opcion){
		case 1:
			return "ApellidoPat";
		case 2:
			return "Nombre";
		case 3:
			return "Especialidad";
		case 4:
			return "Horario";
		}
		return "Error handler placeholder DO NOT SELECT";
	}

	private String cambiarEnColumna(String columna){
		String query = "UPDATE Doctores SET " + columna + " = ? WHERE ID = ?";
		return query;
	}
	
	private void imprimirHeaders(){
		System.out.printf("%-4s %-12s %-12s %-15s %-12s \n",
						  headers[0],
						  headers[1],
						  headers[2],
						  headers[3],
						  headers[4]
						  );
	}

	private void cerrarConeccion(){
		try {if (conn != null && !conn.isClosed()) {conn.close();}}
		catch (SQLException ex) {ex.printStackTrace();}
	}

	private void imprimirFilas(ResultSet rs){
		try {
			imprimirHeaders();
			while(rs.next())
				{
					System.out.printf("%-4s %-12s %-12s %-15s %-12s \n",
									  rs.getString("ID"),
									  rs.getString("ApellidoPat"),
									  rs.getString("Nombre"),
									  rs.getString("Especialidad"),
									  rs.getString("Horario")
									  );
				}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void imprimirListaMedicos(){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				Statement state = conn.createStatement();
				ResultSet resSet = state.executeQuery(datosCompletos);
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}

	public void buscarIDMedico(int id){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				PreparedStatement ps =
					conn.prepareStatement(buscarEnColumna("ID"));
				ps.setInt(1,id);
				ResultSet resSet = ps.executeQuery();
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
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
}
