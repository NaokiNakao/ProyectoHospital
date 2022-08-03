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

import logico.Administrador;
import logico.Clinica;
import logico.Medico;
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
	private JPasswordField pfRepetirContrasenna;
	private Usuario userAux;
	private JButton btnRegistrar;
	private JButton btnSalir;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroUsuario dialog = new RegistroUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroUsuario(Usuario user) {
		this.userAux = user;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 615, 601);
		if (user != null) {
			setTitle("Modificaci�n de usuario");
		}
		else {
			setTitle("Registro de usuario");
		}
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_general = new JPanel();
		panel_general.setBounds(27, 11, 552, 493);
		panel_general.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			if (user != null) {
				txtId.setText(user.getId().toString());
			}
			panel_general.add(txtId);
			txtId.setEditable(false);
			//txtId.setText(Clinica.getInstance().generadorCodigo(8));
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
			if(user != null) {
				txtNombre.setText(user.getNombre().toString());
			}
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
			if(user!=null) {
				txtApellido.setText(user.getApellido().toString());
			}
			panel_general.add(txtApellido);
			txtApellido.setColumns(10);
		}
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(48, 127, 69, 14);
		panel_general.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(125, 124, 139, 20);
		if(user != null) {
			txtTelefono.setText(user.getTelefono().toString());
		}
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
				if(user!= null) {
					txtLogin.setText(user.getLogin().toString());
				}
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
			
			pfRepetirContrasenna = new JPasswordField();
			pfRepetirContrasenna.setBounds(386, 84, 119, 20);
			panelDatosUsuario.add(pfRepetirContrasenna);
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
				
				// Generacion de codigo para usuario medico
				txtId.setText("M"+Clinica.getInstance().generadorCodigo(4));
			}
		});
		rdbtnMedico.setSelected(true);
		rdbtnMedico.setBounds(100, 15, 109, 23);
		if(user !=null && user instanceof Medico) {
		
		//rdbtnAdmin.setSelected(false);
		rdbtnMedico.setSelected(true);
		}
		
		panelRdbtns.add(rdbtnMedico);
		
		rdbtnAdmin = new JRadioButton("Administrador");
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmins.setVisible(true);
				panelMedico.setVisible(false);
				rdbtnMedico.setSelected(false);
				
				// Generacion de codigo para usuario administrador
				txtId.setText("A"+Clinica.getInstance().generadorCodigo(4));
			}
		});
		rdbtnAdmin.setBounds(309, 15, 109, 23);
		if(user !=null && user instanceof Administrador) {
		
			rdbtnAdmin.setSelected(true);
			//rdbtnMedico.setSelected(false);
		}
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
		if(user!=null && user instanceof Medico) {
		
			txtEspecialidad.setText( ((Medico) user).getEspecialidad().toString() );
		}
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
		
		if(user!=null && user instanceof Administrador) {
			
			txtCargoLaboral.setText(  ((Administrador) user).getPuestoLaboral().toString()  );
		}
		panelAdmins.add(txtCargoLaboral);
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 523, 599, 38);
		contentPanel.add(buttonPane);
		buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPane.setLayout(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(361, 7, 131, 23);
		if(user != null){
			btnModificar.setEnabled(true);
			btnModificar.setVisible(true);
		}
		buttonPane.add(btnModificar);
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(361, 7, 131, 23);
		if(user == null){
			btnModificar.setVisible(false);
			btnRegistrar.setVisible(true);
			btnRegistrar.setEnabled(true);
		}
		buttonPane.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 if(espaciovacio())
			 {
				 JOptionPane.showMessageDialog(null, "Favor completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
			 }else{
				 
				 if(user(txtLogin.getText().toString())) {
					 JOptionPane.showMessageDialog(null, "Favor escoger otro usuario.", "Error", JOptionPane.ERROR_MESSAGE);
				 }else {
					 
					if(Clinica.getInstance().buscarUsuarioByLogin(txtLogin.getText().toString())!= null) {
						JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					}else { 
					 if(rdbtnMedico.isSelected()) {
						 if(pfContrasenna.getText().equalsIgnoreCase(pfRepetirContrasenna.getText())) {
							 Medico usu = new Medico( txtId.getText(),txtLogin.getText(),pfContrasenna.getText(),
									 txtNombre.getText(),txtApellido.getText(),txtTelefono.getText(),txtEspecialidad.getText().toString());
							 Clinica.getInstance().registroUsuario(usu);
							 limpiar();
							 JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
							 txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4)); 
					 	}else{
					 		JOptionPane.showMessageDialog(null, "Favor repetir las contrase�as correctamente", "Error", JOptionPane.ERROR_MESSAGE);
					 		}
						 }else if (rdbtnAdmin.isSelected()) {
	
					 		if(pfContrasenna.getText().equalsIgnoreCase(pfRepetirContrasenna.getText())) {
								 Administrador usu = new Administrador( txtId.getText(),txtLogin.getText(),pfContrasenna.getText(),
										 txtNombre.getText(),txtApellido.getText(),txtTelefono.getText(),txtCargoLaboral.getText().toString());
								 Clinica.getInstance().registroUsuario(usu);
								 limpiar();
								 JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
								 txtId.setText("2322-" + Clinica.getInstance().generadorCodigo(4)); 
						 	}else{
						 		JOptionPane.showMessageDialog(null, "Favor repetir las contrase�as correctamente", "Error", JOptionPane.ERROR_MESSAGE);
					 		
					 	}
			 		}
				 }
				}
			 
			 }
			}
		});
		btnRegistrar.setActionCommand("OK");
		getRootPane().setDefaultButton(btnRegistrar);
		{
			btnSalir = new JButton("Salir");
			btnSalir.setBounds(512, 7, 77, 23);
			buttonPane.add(btnSalir);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					ListadoUsuario p = new ListadoUsuario();
					p.setVisible(true);
				}
			});
			btnSalir.setActionCommand("Cancel");
		}
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(espaciovacio()) {
					JOptionPane.showMessageDialog(null, "Favor completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					user.setNombre(txtNombre.getText().toString());
					user.setApellido(txtApellido.getText().toString());
					user.setId(txtId.getText().toString());
					user.setLogin(txtLogin.getText().toString());
					user.setTelefono(txtTelefono.getText().toString());
					
					
					if(user instanceof Medico) {
						((Medico) user).setEspecialidad(txtEspecialidad.getText().toString());
					}else if(user instanceof Administrador) {
						((Administrador) user).setPuestoLaboral(txtCargoLaboral.getText().toString());
					}
					
					
					if(pfContrasenna.getText().equalsIgnoreCase(pfRepetirContrasenna.getText())) {
						user.setPassword(pfContrasenna.getText().toString());
					}else {
						JOptionPane.showMessageDialog(null, "Favor repetir las contrase�as correctamente", "Error", JOptionPane.ERROR_MESSAGE);
						pfContrasenna.setText("");
						pfRepetirContrasenna.setText("");
					}
					
					JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					ListadoUsuario r = new ListadoUsuario();
					r.setVisible(true);
				}
				
			}
		});
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtNombre.getText().equalsIgnoreCase("")|| txtApellido.getText().equalsIgnoreCase("") || txtLogin.getText().equalsIgnoreCase("") || txtTelefono.getText().equalsIgnoreCase("")||
				pfContrasenna.getText().equalsIgnoreCase("") || pfRepetirContrasenna.getText().equalsIgnoreCase("")){
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
		pfRepetirContrasenna.setText("");
		txtCargoLaboral.setText("");
		txtEspecialidad.setText("");
	}
	
	public boolean user(String usuario) {
		
		boolean bandera = false;
		
		for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
			if(user.getLogin().equalsIgnoreCase(usuario)) {
				bandera = true;
			}
		}
		
		return bandera;
	}
}
