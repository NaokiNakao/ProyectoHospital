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
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTable tableEnfermedades;
	private JPanel panelEnfermedades;
	private JPanel panelVacunas;
	private JTable tableVacunas;
	private JTable tableDatosConsulta;
	private JPanel panelDatosConsulta;
	
	private DefaultTableModel modelEnfermedades;
	private Object[] rowsEnfermedades;

	private DefaultTableModel modelVacunas;
	private Object[] rowsVacunas;
	
	private DefaultTableModel modelConsultas;
	private Object[] rowsConsultas;
	
	private Paciente paciente;
	private JRadioButton rdbtnEnfermedades;
	private JRadioButton rdbtnVacunas;
	private JRadioButton rdbtnDatosDeConsultas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialPaciente dialog = new HistorialPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HistorialPaciente() {
		setBounds(100, 100, 816, 867);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Buscador:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBounds(34, 24, 81, 14);
			panel.add(lblNewLabel);
			
			JButton btnNewButton = new JButton("Buscar\r\n");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())!= null) {
						paciente = Clinica.getInstance().buscarPaciente(txtCedula.getText().toString());
						txtNombre.setText(paciente.getNombre());
						txtTelefono.setText(paciente.getTelefono());
						
						loadEnfermedades(paciente);
						loadVacunas(paciente);
						loadConsultas(paciente);
						
					}else {
						JOptionPane.showMessageDialog(null, "Este paciente no existe", "Error", JOptionPane.ERROR_MESSAGE);
						txtNombre.setEditable(true);
						txtTelefono.setEditable(true);
					}
				}
			});
			btnNewButton.setBounds(221, 72, 89, 23);
			panel.add(btnNewButton);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(34, 75, 158, 20);
			panel.add(txtCedula);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(34, 49, 81, 14);
			panel.add(lblCedula);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 215, 770, 45);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			rdbtnEnfermedades = new JRadioButton("Enfermedades");
			rdbtnEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnDatosDeConsultas.setSelected(false);
					rdbtnVacunas.setSelected(false);
					
					panelEnfermedades.setVisible(true);
					panelDatosConsulta.setVisible(false);
					panelVacunas.setVisible(false);
				}
			});
			rdbtnEnfermedades.setBounds(110, 15, 109, 23);
			panel_1.add(rdbtnEnfermedades);
			
			rdbtnVacunas = new JRadioButton("Vacunas");
			rdbtnVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnDatosDeConsultas.setSelected(false);
					rdbtnEnfermedades.setSelected(false);
					
					panelEnfermedades.setVisible(false);
					panelDatosConsulta.setVisible(false);
					panelVacunas.setVisible(true);
				}
			});
			rdbtnVacunas.setBounds(329, 15, 109, 23);
			panel_1.add(rdbtnVacunas);
			
			rdbtnDatosDeConsultas = new JRadioButton("Datos de consultas");
			rdbtnDatosDeConsultas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnVacunas.setSelected(false);
					rdbtnEnfermedades.setSelected(false);
					
					panelEnfermedades.setVisible(false);
					panelDatosConsulta.setVisible(true);
					panelVacunas.setVisible(false);
					
				}
			});
			rdbtnDatosDeConsultas.setBounds(548, 15, 159, 23);
			panel_1.add(rdbtnDatosDeConsultas);
			
			panelEnfermedades = new JPanel();
			panelEnfermedades.setBounds(10, 271, 770, 141);
			panel.add(panelEnfermedades);
			panelEnfermedades.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelEnfermedades.add(scrollPane, BorderLayout.CENTER);
			
			tableEnfermedades = new JTable();
			tableEnfermedades.setRowSelectionAllowed(false);
			String[] heardersEnfermedades = {"Codigo","Nombre","Tipo"};
			modelEnfermedades = new DefaultTableModel();
			tableEnfermedades.setModel(modelEnfermedades);
			modelEnfermedades.setColumnIdentifiers(heardersEnfermedades);
			scrollPane.setViewportView(tableEnfermedades);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setBounds(10, 11, 770, 194);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Datos del Paciente:");
			lblNewLabel_1.setBounds(24, 104, 175, 14);
			panel_3.add(lblNewLabel_1);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setBounds(24, 140, 81, 14);
			panel_3.add(lblNewLabel_2);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(24, 160, 158, 20);
			panel_3.add(txtNombre);
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(228, 140, 81, 14);
			panel_3.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(228, 160, 158, 20);
			panel_3.add(txtTelefono);
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			
			panelVacunas = new JPanel();
			panelVacunas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelVacunas.setBounds(10, 423, 770, 141);
			panel.add(panelVacunas);
			panelVacunas.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panelVacunas.add(scrollPane_1, BorderLayout.CENTER);
			
			tableVacunas = new JTable();
			String[] heardersVacunas = {"Codigo","Nombre","Fabricante","Tipo de Vacuna"};
			modelVacunas = new DefaultTableModel();
			tableVacunas.setModel(modelVacunas);
			modelVacunas.setColumnIdentifiers(heardersVacunas);
			scrollPane_1.setViewportView(tableVacunas);
			
			panelDatosConsulta = new JPanel();
			panelDatosConsulta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosConsulta.setBounds(10, 573, 770, 117);
			panel.add(panelDatosConsulta);
			panelDatosConsulta.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			panelDatosConsulta.add(scrollPane_2, BorderLayout.CENTER);
			
			tableDatosConsulta = new JTable();
			String [] heardersConsultas = {"Codigo","Fecha","Doctor"};
			modelConsultas = new DefaultTableModel();
			tableDatosConsulta.setModel(modelConsultas);
			modelConsultas.setColumnIdentifiers(heardersConsultas);
			scrollPane_2.setViewportView(tableDatosConsulta);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	
	private void loadEnfermedades(Paciente paciente) {
		
		modelEnfermedades.setRowCount(0);
		rowsEnfermedades = new Object[modelEnfermedades.getColumnCount()];
		
		if(paciente!= null) {
				
				for (int i = 0; i < paciente.getHistorial().getPadecimientos().size(); i++) {
					rowsEnfermedades[0]= paciente.getHistorial().getPadecimientos().get(i).getCodigo();
					rowsEnfermedades[1]= paciente.getHistorial().getPadecimientos().get(i).getNombreEnfermedad();
					rowsEnfermedades[2]= paciente.getHistorial().getPadecimientos().get(i).getTipoEnfermedad();
				}
		}
		
	}
	
	private void loadVacunas(Paciente paciente) {
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		
		if(paciente!= null) {
			for (int i = 0; i < rowsVacunas.length; i++) {
				rowsVacunas[0]= paciente.getHistorial().getMisVacunas().get(i).getCodigo();
				rowsVacunas[1]=  paciente.getHistorial().getMisVacunas().get(i).getNombreVacuna();
				rowsVacunas[2]= paciente.getHistorial().getMisVacunas().get(i).getFabricante();
				rowsVacunas[3]= paciente.getHistorial().getMisVacunas().get(i).getTipoVacuna();
			}
		
		}
	}
	
	private void loadConsultas(Paciente paciente) {
		modelConsultas.setRowCount(0);
		rowsConsultas = new Object[modelConsultas.getColumnCount()];
		
		if(paciente!= null) {
			for (int i = 0; i < rowsConsultas.length; i++) {
				rowsConsultas[0] = paciente.getMisConsultas().get(i).getCodigo();
				rowsConsultas[1] = paciente.getMisConsultas().get(i).getFechaConsulta();
				rowsConsultas[2] = paciente.getMisConsultas().get(i).getMiMedico().getNombre();
				
			}
		}
	}
	
	
}
