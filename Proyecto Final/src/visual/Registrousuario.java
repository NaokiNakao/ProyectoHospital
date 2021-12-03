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

public class Registrousuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtusunomb;
	private JTextField txtusuid;
	private JTextField txtusulogin;
	private JTextField txtusuape;
	private JPasswordField pfusucontras;
	private JTextField txtusutel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Registrousuario dialog = new Registrousuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Registrousuario() {
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
			txtusunomb = new JTextField();
			txtusunomb.setBounds(64, 19, 86, 20);
			contentPanel.add(txtusunomb);
			txtusunomb.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(382, 22, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtusuid = new JTextField();
			txtusuid.setEditable(false);
			txtusuid.setText("2322-" + Clinica.getInstance().generadorCodigo(4));
			txtusuid.setBounds(445, 19, 86, 20);
			contentPanel.add(txtusuid);
			txtusuid.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Login:");
			lblNewLabel_2.setBounds(10, 66, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtusulogin = new JTextField();
			txtusulogin.setBounds(64, 63, 86, 20);
			contentPanel.add(txtusulogin);
			txtusulogin.setColumns(10);
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
			txtusuape = new JTextField();
			txtusuape.setColumns(10);
			txtusuape.setBounds(268, 19, 86, 20);
			contentPanel.add(txtusuape);
		}
		
		pfusucontras = new JPasswordField();
		pfusucontras.setText(generadorContraseña(8));
		pfusucontras.setEditable(false);
		pfusucontras.setBounds(268, 63, 86, 20);
		contentPanel.add(pfusucontras);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(382, 66, 69, 14);
		contentPanel.add(lblTelefono);
		
		txtusutel = new JTextField();
		txtusutel.setColumns(10);
		txtusutel.setBounds(445, 63, 86, 20);
		contentPanel.add(txtusutel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nomb = txtusunomb.getText();
						 String ape = txtusuape.getText();
						 String login = txtusulogin.getText();
						 String tele = txtusutel.getText();
						 String id = txtusuid.getText();
						 String contraseña = pfusucontras.getText();
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
						 txtusuid.setText("2322-" + Clinica.getInstance().generadorCodigo(4));
						 pfusucontras.setText(generadorContraseña(8));
					 }
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtusunomb.getText().equalsIgnoreCase(" ")|| txtusuape.getText().equalsIgnoreCase(" ") || txtusulogin.getText().equalsIgnoreCase(" ") || txtusutel.getText().equalsIgnoreCase(" "))
		{
			aux = true;
		}
		return aux;
	}
	public void limpiar()
	{
		txtusunomb.setText(" ");
		txtusuape.setText(" ");
		txtusulogin.setText(" ");
		txtusutel.setText(" ");
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
