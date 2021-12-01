package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Vacuna;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegistroVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtFabricante;
	private JComboBox cbxTipo;
	private JTable tableEnfermedades;
	private JTable tableProtección;
	private JButton btnPasarDerecha;
	private JButton btnPasarIzquierda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroVacuna dialog = new RegistroVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroVacuna(Vacuna vacuna) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 558, 531);
		setLocationRelativeTo(null);
		if (vacuna == null) {
			setTitle("Nueva vacuna");
		}
		else {
			setTitle("Modificar vacuna");
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(10, 12, 46, 14);
		contentPanel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(66, 8, 121, 23);
		if (vacuna == null) {
			txtCodigo.setEditable(false);
			txtCodigo.setText("V-" + Clinica.getInstance().generadorCodigo(6));
		}
		contentPanel.add(txtCodigo);
		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(10, 51, 63, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(66, 47, 155, 23);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fabricante:");
			lblNewLabel_1.setBounds(287, 51, 63, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtFabricante = new JTextField();
			txtFabricante.setColumns(10);
			txtFabricante.setBounds(360, 47, 155, 23);
			contentPanel.add(txtFabricante);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Tipo:");
			lblNewLabel_2.setBounds(10, 91, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		
		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Inactivada", "Atenuada", "Vector", "ARN", "Otra"}));
		cbxTipo.setBounds(66, 88, 155, 23);
		contentPanel.add(cbxTipo);
		
		JComboBox cbxAdministracion = new JComboBox();
		cbxAdministracion.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Intramuscular", "Intrad\u00E9rmica", "Subcut\u00E1nea", "Endovenosa", "Oral"}));
		cbxAdministracion.setBounds(360, 87, 155, 23);
		contentPanel.add(cbxAdministracion);
		
		JLabel lblAdministracin = new JLabel("Administraci\u00F3n:");
		lblAdministracin.setBounds(260, 91, 90, 14);
		contentPanel.add(lblAdministracin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Inmunizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 139, 505, 300);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelEnfermedades = new JPanel();
		panelEnfermedades.setBounds(20, 56, 173, 220);
		panel.add(panelEnfermedades);
		panelEnfermedades.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelEnfermedades.add(scrollPane, BorderLayout.CENTER);
		
		tableEnfermedades = new JTable();
		scrollPane.setViewportView(tableEnfermedades);
		
		JPanel panelProteccion = new JPanel();
		panelProteccion.setBounds(311, 56, 173, 220);
		panel.add(panelProteccion);
		panelProteccion.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelProteccion.add(scrollPane_1, BorderLayout.CENTER);
		
		tableProtección = new JTable();
		scrollPane_1.setViewportView(tableProtección);
		
		JLabel lblNewLabel_3 = new JLabel("Enfermedades:");
		lblNewLabel_3.setBounds(63, 31, 86, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Protecci\u00F3n:");
		lblNewLabel_4.setBounds(363, 31, 68, 14);
		panel.add(lblNewLabel_4);
		
		btnPasarDerecha = new JButton(">>");
		btnPasarDerecha.setBounds(216, 114, 77, 23);
		panel.add(btnPasarDerecha);
		
		btnPasarIzquierda = new JButton("<<");
		btnPasarIzquierda.setBounds(216, 161, 77, 23);
		panel.add(btnPasarIzquierda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if (vacuna == null) {
					okButton.setText("Agregar");
				}
				else {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
}
