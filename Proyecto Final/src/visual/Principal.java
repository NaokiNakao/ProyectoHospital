package visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logico.Administrador;
import logico.Clinica;
import logico.Servidor;
import logico.Usuario;

import javax.swing.JLabel;
import javax.imageio.IIOException;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private Socket sesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream respaldoIn;
				FileOutputStream respaldoOut;
				ObjectInputStream respaldoRead;
				ObjectOutputStream respaldoWrite;
				
				try {
					respaldoIn = new FileInputStream(Servidor.respaldo);
					respaldoRead = new ObjectInputStream(respaldoIn);
					Clinica temp = (Clinica) respaldoRead.readObject();
					Clinica.setClinica(temp);
					respaldoIn.close();
					respaldoRead.close();
				} catch (FileNotFoundException e) {
					try {
						respaldoOut = new FileOutputStream(Servidor.respaldo);
						respaldoWrite = new ObjectOutputStream(respaldoOut);
						Administrador aux = new Administrador(Clinica.getInstance().generadorCodigo(8), "admin", "admin", "", "", "", "");
						Clinica.getInstance().registroUsuario(aux);
						respaldoWrite.writeObject(Clinica.getInstance());
						respaldoOut.close();
						respaldoWrite.close();
					} catch (FileNotFoundException e1) {
						
					}
					catch (IOException e1) {
						
					}
				}
				catch (IOException e) {
					
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				respaldo();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/pictures/istockphoto-1130389312-612x612.jpg")));
		setResizable(false);
		setTitle("Hospital Juan Jos\u00E9 Almanzar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-60);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel portada = new JLabel("");
		portada.setIcon(new ImageIcon(Principal.class.getResource("/pictures/portada.gif")));
		portada.setBounds(349,56, 578, 453);
		contentPane.add(portada);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO AL ");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(563, 21, 555, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Iniciar sesi\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioSesion ini = new InicioSesion();
				ini.setVisible(true);
			}
		});
		btnNewButton.setBounds(560, 470, 175, 45);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				respaldo();
				dispose();
			}
		});
		btnCancelar.setBounds(560,540, 175, 45);
		contentPane.add(btnCancelar);
		
		JLabel lblHospitalJuanJose = new JLabel("HOSPITAL JUAN JOSE ALMANZAR");
		lblHospitalJuanJose.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblHospitalJuanJose.setBounds(488, 56, 600, 43);
		contentPane.add(lblHospitalJuanJose);
		setLocationRelativeTo(null);
	}
	

	
	private void respaldo() {
		String serverAddr = Servidor.addr;
		int serverPort = Servidor.port;
		
		try {
			sesion = new Socket(serverAddr, serverPort);
			ObjectOutputStream salida = new ObjectOutputStream(sesion.getOutputStream());
			salida.writeObject(Clinica.getInstance());
			sesion.close();
		} catch (IOException ioe) {
			System.out.println("No se puede conectar al servidor.");
			System.exit(1);
		}
	}
	
}










