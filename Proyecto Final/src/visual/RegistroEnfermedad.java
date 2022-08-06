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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Enfermedad;
import logico.Usuario;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class RegistroEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox cbxTipo;
	private JTextPane textPaneDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroEnfermedad dialog = new RegistroEnfermedad(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param user 
	 */
	public RegistroEnfermedad(Usuario user,Enfermedad enfermedad) {
		setModal(true);
		setResizable(false);
		setTitle("Registro de enfermedad");
		setBounds(100, 100, 516, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 15, 467, 320);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		textPaneDescripcion = new JTextPane();
		textPaneDescripcion.setBounds(30, 175, 419, 129);
		if(enfermedad!=null) {
			textPaneDescripcion.setText(enfermedad.getDescripcionEnfermedad().toString());
		}
		panel.add(textPaneDescripcion);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 17, 79, 14);
		panel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(85, 14, 149, 20);
		if(enfermedad!=null) {
			txtCodigo.setText(enfermedad.getCodigo().toString());
		}else {
			txtCodigo.setText("E-" + Clinica.getInstance().generadorCodigo(5));
		}
		
		panel.add(txtCodigo);
		txtCodigo.setEditable(false);
		//txtCodigo.setText("E-" + Clinica.getInstance().generadorCodigo(5));
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 56, 79, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(85, 53, 149, 20);
		if(enfermedad!=null) {
			txtNombre.setText(enfermedad.getNombreEnfermedad().toString());
		}
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo:");
		lblNewLabel_2.setBounds(10, 103, 46, 14);
		panel.add(lblNewLabel_2);
		
		cbxTipo = new JComboBox();
		cbxTipo.setBounds(85, 100, 149, 20);
		if(enfermedad!=null) {
		//	txtCodigo.setText(enfermedad.getCodigo().toString());
		}
		panel.add(cbxTipo);
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<< Seleccione >>", "Respiratoria", "Gastrointestinal", "Neurologica", "Muscular", "Sexual", "Cardiovascular"}));
		
		JLabel lblNewLabel_3 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_3.setBounds(10, 150, 79, 14);
		panel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					if(enfermedad != null) {
						if(espaciovacio()) {
								JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
								enfermedad.setDescripcionEnfermedad(textPaneDescripcion.getText().toString());
								enfermedad.setNombreEnfermedad(txtNombre.getText().toString());
						
						if(cbxTipo.getSelectedItem().toString().equalsIgnoreCase("<< Seleccione >>")) {
								JOptionPane.showMessageDialog(null, "Favor seleccionar un tipo.", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
								enfermedad.setTipoEnfermedad(cbxTipo.getSelectedItem().toString());
							
								JOptionPane.showMessageDialog(null, "Modificacion Exitosa", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								EstadisticaEnfermedad r = new EstadisticaEnfermedad(user);
								r.setVisible(true);
							}
						}
					}else {
					  if(espaciovacio())
					  {
						  	JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					  }
					  
					  else {
							String codigo = txtCodigo.getText();
							String nombre = txtNombre.getText();
							String tipo = cbxTipo.getSelectedItem().toString();
							String descripcion = textPaneDescripcion.getText();
							Enfermedad enfer = new Enfermedad(codigo,nombre,tipo, descripcion);
							Clinica.getInstance().insertarEnfermedad(enfer);
							JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
							limpiar();
							txtCodigo.setText("E-" + Clinica.getInstance().generadorCodigo(5));
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
					}
				});
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						EstadisticaEnfermedad r;
						try {
							r = new EstadisticaEnfermedad(user);
							r.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
	public void limpiar() {
		txtNombre.setText("");
		textPaneDescripcion.setText("");
		cbxTipo.setSelectedIndex(0);
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtNombre.getText().equalsIgnoreCase("") || textPaneDescripcion.getText().equalsIgnoreCase("") ||cbxTipo.getSelectedItem().toString().equalsIgnoreCase("<< Seleccione >>"))
		{
			aux = true;
		}
		return aux;
	}
}
