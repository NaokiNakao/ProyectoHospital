package logico;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
	
	public static int port = 8080;
	public static String addr = "127.0.0.1";
	public static String respaldo = "respaldo.dat"; // nombre del fichero donde se almacena la información

	public static void main(String[] args) {
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("Servidor iniciado.");
		} catch (IOException ioe) {
			System.out.println("No se pudo iniciar el servidor.\n" + ioe);
			System.exit(1);
		}
		
		while (true) {
			
			try {
				Socket conn = server.accept();
				System.out.println("Conexión establecida con " + conn.getInetAddress());
				ObjectInputStream data = new ObjectInputStream(conn.getInputStream());
				FileOutputStream file = new FileOutputStream(respaldo);
				ObjectOutputStream writer = new ObjectOutputStream(file);
				Clinica temp = (Clinica) data.readObject();
				writer.writeObject(temp);
				file.close();
				writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
