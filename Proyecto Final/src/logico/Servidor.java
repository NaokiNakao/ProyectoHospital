package logico;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor extends Thread {
	
	public static Vector sesiones = new Vector();
	private static int port = 8080;
	
	public static void main(String[] args) {
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
		} catch (IOException ioe) {
			System.out.println("Error al iniciar el servidor.");
			System.exit(1);
		}
		
		while (true) {
			try {
				Socket conn = server.accept();
				System.out.println("Nueva sesión desde " + conn.getInetAddress());
				
			} catch (IOException ioe) {
				System.out.println("Error " + ioe);
			}
		}
	}
	
}
