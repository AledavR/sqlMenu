package unmsm.rcaled.sqlMenu;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class consultas {

	Scanner input = new Scanner(System.in);
	Connection conn = null;

	String dbURL = "jdbc:sqlserver://localhost:1433;database=PersonalMedico";
	String user = "sa";
	String pass = "Password123";

	String[] headers = {"ID","ApellidoPat","Nombre","Especialidad","Horario"};
	String[] columnas = {"ApellidoPat","Nombre","Especialidad","Horario"};
	String[] horarios = {"Mañana","Tarde","Noche"};
	
	String datosCompletos = "SELECT * FROM Doctores";
	String borrarPorID = "DELETE FROM Doctores WHERE ID = ?";
	String cambiarHora = "UPDATE Doctores SET Asistencia = 1"
		+ " WHERE Horario = ?;"
		+  "UPDATE Doctores SET Asistencia = 0"
		+ " WHERE Horario != ?";

	private String buscarEnColumna(String columna){
		String query = "SELECT * FROM Doctores WHERE " + columna + " = ?";
		return query;
	}

	public String eleccionDeColumna(Scanner input){
		System.out.println("Que columna desea editar:");
		for (int i = 0 ; i < 4 ; i++){
			int indice = i + 1;
			System.out.println(indice + ") "+ columnas[i]);
		}
		System.out.print("Opcion elegida: ");
		int opcion = input.nextInt();
		String columna = switch(opcion){ // Nuevo metodo para usar switch
		case 1 -> "ApellidoPat";
		case 2 -> "Nombre";
		case 3 -> "Especialidad";
		case 4 -> "Horario";
		default -> "Error handler placeholder DO NOT SELECT";
		};
		return columna;
	}

	public String eleccionDeHora(Scanner input){
		System.out.println("Que horario esta activo:");
		for (int i = 0 ; i < 3 ; i++){
			int indice = i + 1;
			System.out.println(indice + ") "+ horarios[i]);
		}
		System.out.print("Opcion elegida: ");
		int opcion = input.nextInt();
		String horaActual = switch(opcion){ // Nuevo metodo para usar switch
		case 1 -> "Mañana";
		case 2 -> "Tarde";
		case 3 -> "Noche";
		default -> "Error handler placeholder DO NOT SELECT";
		};
		return horaActual;
	}

	private String cambiarEnColumna(String columna){
		String query = "UPDATE Doctores SET " + columna + " = ? WHERE ID = ?";
		return query;
	}
	
	private void imprimirHeaders(){
		System.out.printf("%-4s %-12s %-22s %-22s %-12s \n",
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
					System.out.printf("%-4s %-12s %-22s %-22s %-12s \n",
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

	public void mostrarDoctoresPresentes(){
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(buscarEnColumna("Asistencia"));
				ps.setInt(1, 1);
				ResultSet resSet = ps.executeQuery();
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}
	
}
