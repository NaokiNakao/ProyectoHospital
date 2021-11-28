package visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logico.Clinica;
import logico.Usuario;

import javax.swing.JLabel;
import javax.imageio.IIOException;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream dbInput;
				FileOutputStream dbOutput;
				ObjectInputStream dbRead;
				ObjectOutputStream dbWrite;
				
				try {
					dbInput = new FileInputStream(Clinica.getInstance().getDbPath());
					dbRead = new ObjectInputStream(dbInput);
					Clinica temp = (Clinica) dbRead.readObject();
					Clinica.setClinica(temp);
					dbInput.close();
					dbRead.close();
				} catch (FileNotFoundException e) {
					try {
						dbOutput = new FileOutputStream(Clinica.getInstance().getDbPath());
						dbWrite = new ObjectOutputStream(dbOutput);
						Usuario aux = new Usuario(Clinica.getInstance().generadorCodigo(8), "admin", "admin", "", "", "");
						Clinica.getInstance().registroUsuario(aux);
						dbWrite.writeObject(Clinica.getInstance());
						dbOutput.close();
						dbWrite.close();
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
				guardarInformacion();
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
				guardarInformacion();
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
	
	public void guardarInformacion() {
		FileOutputStream file;
		ObjectOutputStream writer;
		
		try {
			file = new FileOutputStream(Clinica.getInstance().getDbPath());
			writer = new ObjectOutputStream(file);
			writer.writeObject(Clinica.getInstance());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
}










