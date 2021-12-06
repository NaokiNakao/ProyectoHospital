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
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class RegistroUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtId;
	private JTextField txtLogin;
	private JTextField txtApellido;
	private JPasswordField pfContrasenna;
	private JTextField txtTelefono;
	private JPanel panelMedico;
	private JTextField txtEspecialidad;
	private JPanel panelAdmins;
	private JTextField txtCargoLaboral;
	private JRadioButton rdbtnMedico;
	private JRadioButton rdbtnAdmin;
	private JPasswordField pfRepetirContraseña;

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
		setBounds(100, 100, 615, 601);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_general = new JPanel();
		panel_general.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_general.setBounds(27, 11, 552, 493);
		contentPanel.add(panel_general);
		panel_general.setLayout(null);
		{
			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(48, 26, 46, 14);
			panel_general.add(lblNewLabel_1);
		}
		{
			txtId = new JTextField();
			txtId.setBounds(125, 23, 139, 20);
			panel_general.add(txtId);
			txtId.setEditable(false);
			txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4));
			txtId.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(48, 80, 69, 14);
			panel_general.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(125, 77, 137, 20);
			panel_general.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(290, 80, 67, 14);
			panel_general.add(lblApellido);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setBounds(367, 77, 137, 20);
			panel_general.add(txtApellido);
			txtApellido.setColumns(10);
		}
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(48, 127, 69, 14);
		panel_general.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(125, 124, 139, 20);
		panel_general.add(txtTelefono);
		txtTelefono.setColumns(10);
		{
			JPanel panelDatosUsuario = new JPanel();
			panelDatosUsuario.setBorder(new TitledBorder(null, "Datos de Usuario:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosUsuario.setBounds(10, 335, 518, 133);
			panel_general.add(panelDatosUsuario);
			panelDatosUsuario.setLayout(null);
			{
				JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
				lblContrasea.setBounds(13, 87, 96, 14);
				panelDatosUsuario.add(lblContrasea);
			}
			
			pfContrasenna = new JPasswordField();
			pfContrasenna.setBounds(116, 84, 119, 20);
			panelDatosUsuario.add(pfContrasenna);
			{
				txtLogin = new JTextField();
				txtLogin.setBounds(116, 35, 119, 20);
				panelDatosUsuario.add(txtLogin);
				txtLogin.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Usuario:");
				lblNewLabel_2.setBounds(10, 38, 62, 14);
				panelDatosUsuario.add(lblNewLabel_2);
			}
			
			JLabel lblNewLabel_5 = new JLabel("Repetir contrase\u00F1a:");
			lblNewLabel_5.setBounds(254, 87, 119, 14);
			panelDatosUsuario.add(lblNewLabel_5);
			
			pfRepetirContraseña = new JPasswordField();
			pfRepetirContraseña.setBounds(386, 84, 119, 20);
			panelDatosUsuario.add(pfRepetirContraseña);
		}
		
		JPanel panelRdbtns = new JPanel();
		panelRdbtns.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRdbtns.setBounds(10, 176, 518, 54);
		panel_general.add(panelRdbtns);
		panelRdbtns.setLayout(null);
		
		rdbtnMedico = new JRadioButton("Medico\r\n");
		rdbtnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMedico.setVisible(true);
				panelAdmins.setVisible(false);
				rdbtnAdmin.setSelected(false);
			}
		});
		rdbtnMedico.setSelected(true);
		rdbtnMedico.setBounds(100, 15, 109, 23);
		panelRdbtns.add(rdbtnMedico);
		
		rdbtnAdmin = new JRadioButton("Administrador");
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmins.setVisible(true);
				panelMedico.setVisible(false);
				rdbtnMedico.setSelected(false);
			}
		});
		rdbtnAdmin.setBounds(309, 15, 109, 23);
		panelRdbtns.add(rdbtnAdmin);
		
		panelMedico = new JPanel();
		panelMedico.setBounds(10, 240, 518, 67);
		panel_general.add(panelMedico);
		panelMedico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMedico.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Especialidad:");
		lblNewLabel_3.setBounds(38, 28, 132, 14);
		panelMedico.add(lblNewLabel_3);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(155, 25, 208, 20);
		panelMedico.add(txtEspecialidad);
		txtEspecialidad.setColumns(10);
		
		panelAdmins = new JPanel();
		panelAdmins.setBounds(10, 240, 518, 67);
		panel_general.add(panelAdmins);
		panelAdmins.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdmins.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Puesto Laboral:");
		lblNewLabel_4.setBounds(38, 28, 179, 14);
		panelAdmins.add(lblNewLabel_4);
		
		txtCargoLaboral = new JTextField();
		txtCargoLaboral.setColumns(10);
		txtCargoLaboral.setBounds(155, 25, 208, 20);
		panelAdmins.add(txtCargoLaboral);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 if(espaciovacio())
					 {
						 JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					 }else{
						 
						 if(pfContrasenna.getText().equalsIgnoreCase(pfRepetirContraseña.getText())) {
							 Usuario usu = new Usuario( txtId.getText(),txtLogin.getText(),pfContrasenna.getText(),txtNombre.getText(),txtApellido.getText(),txtTelefono.getText());
							 Clinica.getInstance().registroUsuario(usu);
							 limpiar();
							 JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
							 txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4)); 
					 	}else{
					 		JOptionPane.showMessageDialog(null, "Favor repetir las contraseñas correctamente", "Error", JOptionPane.ERROR_MESSAGE);
					 	}
					 
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
		if(txtNombre.getText().equalsIgnoreCase("")|| txtApellido.getText().equalsIgnoreCase("") || txtLogin.getText().equalsIgnoreCase("") || txtTelefono.getText().equalsIgnoreCase("")||
				pfContrasenna.getText().equalsIgnoreCase("") || pfRepetirContraseña.getText().equalsIgnoreCase("")){
			aux = true;
		}else if(rdbtnAdmin.isSelected() && txtCargoLaboral.getText().equalsIgnoreCase("")) {
			aux=true;
		}else if(rdbtnMedico.isSelected() && txtEspecialidad.getText().equalsIgnoreCase("")) {
			aux = true;
		}
		
		
		
		return aux;
	}
	public void limpiar()
	{
		txtNombre.setText("");
		txtApellido.setText("");
		txtLogin.setText("");
		txtTelefono.setText("");
		pfContrasenna.setText("");
		pfRepetirContraseña.setText("");
		txtCargoLaboral.setText("");
		txtEspecialidad.setText("");
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
