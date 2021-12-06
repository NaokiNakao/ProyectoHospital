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


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JComboBox cbxTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroEnfermedad dialog = new RegistroEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroEnfermedad() {
		setModal(true);
		setResizable(false);
		setTitle("Registro de enfermedad");
		setBounds(100, 100, 506, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(20, 27, 79, 14);
		contentPanel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setText("E-" + Clinica.getInstance().generadorCodigo(5));
		txtCodigo.setBounds(95, 24, 149, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(20, 66, 79, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(95, 63, 149, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo:");
		lblNewLabel_2.setBounds(20, 113, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<< Seleccione >>", "Respiratoria", "Gastrointestinal", "Neurologica", "Muscular", "Sexual", "Cardiovascular"}));
		cbxTipo.setBounds(95, 110, 149, 20);
		contentPanel.add(cbxTipo);
		
		JLabel lblNewLabel_3 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_3.setBounds(20, 160, 79, 14);
		contentPanel.add(lblNewLabel_3);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(20, 186, 352, 108);
		contentPanel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 467, 320);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codigo = txtCodigo.getText();
						String nombre = txtNombre.getText();
						String tipo = cbxTipo.getSelectedItem().toString();
						String descripcion = txtDescripcion.getText();
						Enfermedad enfer = new Enfermedad(codigo,nombre,tipo, descripcion);
					  if(espaciovacio())
					  {
						  JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					  }
					  
					  else {
						  Clinica.getInstance().insertarEnfermedad(enfer);
						   limpiar();
						   JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
						   txtCodigo.setText("E-" + Clinica.getInstance().generadorCodigo(5));
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
	public void limpiar() {
		txtNombre.setText(" ");
		txtDescripcion.setText(" ");
		cbxTipo.setSelectedIndex(0);
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtNombre.getText().equalsIgnoreCase(" ") || txtDescripcion.getText().equalsIgnoreCase(" ") ||cbxTipo.getSelectedItem().toString().equalsIgnoreCase("<< Seleccione >>"))
		{
			aux = true;
		}
		return aux;
	}
}
