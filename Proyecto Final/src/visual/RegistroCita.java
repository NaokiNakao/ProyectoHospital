package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnBuscarCedula;
	private JTextField txtCodigoCita;
	private JTextField txtFecha;
	private JTable tableDoctoresDisponibles;
	private JButton btnBuscarFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroCita dialog = new RegistroCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroCita() {
		setTitle("Citas Medicas\r\n");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 783, 515);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panelDatosCliente = new JPanel();
			panelDatosCliente.setBorder(new TitledBorder(null, "Datos del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosCliente.setBounds(10, 11, 747, 170);
			panel.add(panelDatosCliente);
			panelDatosCliente.setLayout(null);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(83, 31, 165, 23);
			panelDatosCliente.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscarCedula = new JButton("Buscar\r\n");
			btnBuscarCedula.setBounds(270, 31, 89, 23);
			panelDatosCliente.add(btnBuscarCedula);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(83, 82, 165, 23);
			panelDatosCliente.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(356, 82, 165, 23);
			panelDatosCliente.add(txtTelefono);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(83, 131, 438, 23);
			panelDatosCliente.add(txtDireccion);
			
			JLabel lblNewLabel = new JLabel("Cedula:");
			lblNewLabel.setBounds(10, 35, 63, 14);
			panelDatosCliente.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(10, 86, 63, 14);
			panelDatosCliente.add(lblNewLabel_1);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 135, 63, 14);
			panelDatosCliente.add(lblDireccion);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(283, 86, 63, 14);
			panelDatosCliente.add(lblTelefono);
			
			JPanel panelDatosConsulta = new JPanel();
			panelDatosConsulta.setBorder(new TitledBorder(null, "Datos de la consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosConsulta.setBounds(10, 192, 747, 224);
			panel.add(panelDatosConsulta);
			panelDatosConsulta.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Codigo:");
			lblNewLabel_2.setBounds(10, 34, 63, 14);
			panelDatosConsulta.add(lblNewLabel_2);
			
			txtCodigoCita = new JTextField();
			txtCodigoCita.setEditable(false);
			txtCodigoCita.setColumns(10);
			txtCodigoCita.setBounds(83, 31, 165, 23);
			panelDatosConsulta.add(txtCodigoCita);
			
			JLabel lblNewLabel_3 = new JLabel("Fecha:");
			lblNewLabel_3.setBounds(283, 34, 109, 14);
			panelDatosConsulta.add(lblNewLabel_3);
			
			txtFecha = new JTextField();
			txtFecha.setColumns(10);
			txtFecha.setBounds(356, 30, 165, 23);
			panelDatosConsulta.add(txtFecha);
			
			btnBuscarFecha = new JButton("Buscar\r\n");
			btnBuscarFecha.setBounds(544, 30, 89, 23);
			panelDatosConsulta.add(btnBuscarFecha);
			
			JPanel panelDoctoresDisponibles = new JPanel();
			panelDoctoresDisponibles.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDoctoresDisponibles.setBounds(10, 72, 727, 141);
			panelDatosConsulta.add(panelDoctoresDisponibles);
			panelDoctoresDisponibles.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelDoctoresDisponibles.add(scrollPane, BorderLayout.CENTER);
			
			tableDoctoresDisponibles = new JTable();
			scrollPane.setViewportView(tableDoctoresDisponibles);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
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