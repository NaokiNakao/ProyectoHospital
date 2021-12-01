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
	private JTextField txtenfercod;
	private JTextField txtenfernomb;
	private JTextField txtenferdescrip;
	private JComboBox cmbenfertip;

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
		setBounds(100, 100, 493, 274);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 27, 79, 14);
		contentPanel.add(lblNewLabel);
		
		txtenfercod = new JTextField();
		txtenfercod.setEditable(false);
		txtenfercod.setText("E-" + Clinica.getInstance().generadorCodigo(5));
		txtenfercod.setBounds(85, 24, 122, 20);
		contentPanel.add(txtenfercod);
		txtenfercod.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 66, 79, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtenfernomb = new JTextField();
		txtenfernomb.setBounds(85, 63, 122, 20);
		contentPanel.add(txtenfernomb);
		txtenfernomb.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo:");
		lblNewLabel_2.setBounds(241, 66, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		cmbenfertip = new JComboBox();
		cmbenfertip.setModel(new DefaultComboBoxModel(new String[] {"<< Seleccione >>", "Respiratoria", "Gastrointestinal", "Neurologica", "Muscular", "Sexual", "Cardiovascular"}));
		cmbenfertip.setBounds(297, 63, 122, 20);
		contentPanel.add(cmbenfertip);
		
		JLabel lblNewLabel_3 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_3.setBounds(10, 117, 79, 14);
		contentPanel.add(lblNewLabel_3);
		
		txtenferdescrip = new JTextField();
		txtenferdescrip.setBounds(85, 114, 287, 20);
		contentPanel.add(txtenferdescrip);
		txtenferdescrip.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codigo = txtenfercod.getText();
						String nombre = txtenfernomb.getText();
						String tipo = cmbenfertip.getSelectedItem().toString();
						String descripcion = txtenferdescrip.getText();
						Enfermedad enfer = new Enfermedad(codigo,nombre,tipo, descripcion);
					  if(espaciovacio())
					  {
						  JOptionPane.showMessageDialog(null, "Tienes que completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					  }
					  
					  else {
						  Clinica.getInstance().insertarEnfermedad(enfer);
						   limpiar();
						   JOptionPane.showMessageDialog(null, "Registro Exitoso", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
						   txtenfercod.setText("E-" + Clinica.getInstance().generadorCodigo(5));
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
	public void limpiar() {
		txtenfernomb.setText(" ");
		txtenferdescrip.setText(" ");
		cmbenfertip.setSelectedIndex(0);
	}
	
	public boolean espaciovacio()
	{
		boolean aux = false;
		if(txtenfernomb.getText().equalsIgnoreCase(" ") || txtenferdescrip.getText().equalsIgnoreCase(" ") ||cmbenfertip.getSelectedItem().toString().equalsIgnoreCase("<< Seleccione >>"))
		{
			aux = true;
		}
		return aux;
	}
}
