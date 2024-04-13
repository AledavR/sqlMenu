package unmsm.rcaled.sqlMenu;

import java.util.Scanner;
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

public class coneccionSQL {
	
	Scanner input = new Scanner(System.in);
	protected Connection conn = null;

	protected String dbURL = "jdbc:sqlserver://localhost:1433;database=PersonalMedico";
	protected String user = "sa";
	protected String pass = "Password123";

	protected String[] headers = {"ID","ApellidoPat","Nombre","Especialidad","Horario"};
	protected String[] columnas = {"ApellidoPat","Nombre","Especialidad","Horario"};
	protected String[] horarios = {"Mañana","Tarde","Noche"};

	protected void cerrarConeccion(){
		try {
			if (conn != null && !conn.isClosed()) {conn.close();}
		} catch (SQLException ex) {ex.printStackTrace();}
	}
	protected String buscarEnColumna(String columna){
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

	protected void imprimirHeaders(){
		System.out.printf("%-4s %-12s %-22s %-22s %-12s \n",
						  headers[0],
						  headers[1],
						  headers[2],
						  headers[3],
						  headers[4]
						  );
	}

	protected void imprimirFilas(ResultSet rs){
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
}
