package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
	
	
public static Connection getConexion() {
		
        String Conurl = "jdbc:sqlserver://192.168.100.118:1433;" 
    + "database=ventas_Grupo_2;"
    + "user=mvasquez;"
    + "password=Eict@2022;"
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
