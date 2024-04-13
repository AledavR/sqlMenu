package unmsm.rcaled.sqlMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class consultas {

	String dbURL = "jdbc:sqlserver://localhost:1433";
	String user = "sa";
	String pass = "Password123";

	String[] headers = {"ID","ApellidoPat","Nombre","Especialidad","Horario"};
	
	String datosCompletos = "USE PersonalMedico;"
		+ "SELECT * "
		+ "FROM Doctores";

	Connection conn = null;

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
				PreparedStatement ps;
				ps = conn.prepareStatement
					("USE DBPersonalMedico;" +
					 "SELECT * " +
					 "FROM Doctores " +
					 "WHERE ID = ?");
				ps.setInt(1,id);
				ResultSet resSet = ps.executeQuery();
				imprimirFilas(resSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {cerrarConeccion();}
	}
}
