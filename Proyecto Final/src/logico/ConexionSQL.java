package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
	
	public static ConexionSQL conexionSQL;
	
	private ConexionSQL() {}
	
	public static ConexionSQL getInstance() {
		if (conexionSQL == null) {
			conexionSQL = new ConexionSQL();
		}
		return conexionSQL;
	}
	
	public static Connection getConexion() {
			
	        String Conurl = "jdbc:sqlserver://192.168.100.118:1433;" 
	        				+ "database=Clinica;"
	        				+ "user=mvasquez;"
	        				+ "password=Misael0606;"
	        				+ "loginTimeout=30;"
	        				+ "encrypt=true;"
	        				+ "trustServerCertificate=true;";
	        
			try {
				Connection con = DriverManager.getConnection(Conurl);
				return con;
			}catch(SQLException ex) {
				System.out.println(ex.toString());
				return null;
			}	
			
	}

}
