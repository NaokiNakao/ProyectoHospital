package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Usuario;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class RegistroUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtId;
	private JTextField txtLogin;
	private JTextField txtApellido;
	private JPasswordField pfContrasenna;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroUsuario dialog = new RegistroUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroUsuario() {
		setResizable(false);
		setModal(true);
		setTitle("Registro de usuario");
		setBounds(100, 100, 586, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(10, 22, 69, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(64, 19, 86, 20);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(382, 22, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4));
			txtId.setBounds(445, 19, 86, 20);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Login:");
			lblNewLabel_2.setBounds(10, 66, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtLogin = new JTextField();
			txtLogin.setBounds(64, 63, 86, 20);
			contentPanel.add(txtLogin);
			txtLogin.setColumns(10);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(181, 66, 96, 14);
			contentPanel.add(lblContrasea);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(181, 22, 67, 14);
			contentPanel.add(lblApellido);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(268, 19, 86, 20);
			contentPanel.add(txtApellido);
		}
		
		pfContrasenna = new JPasswordField();
		pfContrasenna.setText(generadorContraseña(8));
		pfContrasenna.setEditable(false);
		pfContrasenna.setBounds(268, 63, 86, 20);
		contentPanel.add(pfContrasenna);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(382, 66, 69, 14);
		contentPanel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(445, 63, 86, 20);
		contentPanel.add(txtTelefono);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nomb = txtNombre.getText();
						 String ape = txtApellido.getText();
						 String login = txtLogin.getText();
						 String tele = txtTelefono.getText();
						 String id = txtId.getText();
						 String contraseña = pfContrasenna.getText();
						 Usuario usu = new Usuario(id,login,contraseña,nomb,ape, tele);
					 if(espaciovacio())
					 {
						 JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					 }
					 else
					 {
						 Clinica.getInstance().registroUsuario(usu);
						 limpiar();
						 JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
						 txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4));
						 pfContrasenna.setText(generadorContraseña(8));
					 }
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtNombre.getText().equalsIgnoreCase(" ")|| txtApellido.getText().equalsIgnoreCase(" ") || txtLogin.getText().equalsIgnoreCase(" ") || txtTelefono.getText().equalsIgnoreCase(" "))
		{
			aux = true;
		}
		return aux;
	}
	public void limpiar()
	{
		txtNombre.setText(" ");
		txtApellido.setText(" ");
		txtLogin.setText(" ");
		txtTelefono.setText(" ");
	}
	public String generadorContraseña(int longitud) {
		String caracteres = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz123456790";
		Random rand = new Random();
		char[] aux = new char[longitud];
		String password = "";
		
		for (int i = 0; i < longitud; i++) {
			aux[i] = caracteres.charAt(rand.nextInt(caracteres.length()));
		}
							
		for (int i = 0; i < aux.length; i++) {
			password += aux[i];
		}
		
		return password;
	}
}
