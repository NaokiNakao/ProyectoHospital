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
import java.awt.HeadlessException;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.ConexionSQL;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistoriaClinica;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
		setModal(true);
		setTitle("Historial Medico");
		setResizable(false);
		setBounds(100, 100, 809, 539);
		setLocationRelativeTo(null); 	
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
				
				if(txtCedula.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Favor completar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
						try {
							if(Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())!= null) {
								paciente = Clinica.getInstance().buscarPaciente(txtCedula.getText().toString());
								txtNombre.setText(paciente.getNombre());
								txtTelefono.setText(paciente.getTelefono());
							if(rdbtnEnfermedades.isSelected()) {	
								loadEnfermedades(paciente);
							}else if(rdbtnVacunas.isSelected()) {
									loadVacunas(paciente);
								}else if(rdbtnDatosDeConsultas.isSelected()) {
									loadConsultas(paciente);
								}
							
							}else {
								JOptionPane.showMessageDialog(null, "Este paciente no existe o la cedula esta incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
								txtNombre.setText("");
								txtTelefono.setText("");
								txtCedula.setText("");
								paciente = null;
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(paciente==null){
							try {
								loadEnfermedades(paciente);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								loadVacunas(paciente);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								loadConsultas(paciente);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
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
						try {
							loadEnfermedades(paciente);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			});
			rdbtnEnfermedades.setBounds(110, 15, 109, 23);
			panel_1.add(rdbtnEnfermedades);
			
			rdbtnVacunas = new JRadioButton("Vacunas");
			rdbtnVacunas.setSelected(true);
			rdbtnVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnDatosDeConsultas.setSelected(false);
					rdbtnEnfermedades.setSelected(false);
					
					panelEnfermedades.setVisible(false);
					panelDatosConsulta.setVisible(false);
					panelVacunas.setVisible(true);
					try {
						loadVacunas(paciente);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						loadConsultas(paciente);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			rdbtnDatosDeConsultas.setBounds(548, 15, 159, 23);
			panel_1.add(rdbtnDatosDeConsultas);
			String[] heardersEnfermedades = {"Codigo","Nombre","Tipo"};
			modelEnfermedades = new DefaultTableModel();
			modelEnfermedades.setColumnIdentifiers(heardersEnfermedades);
			
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
			panelVacunas.setBounds(10, 271, 770, 180);
			panel.add(panelVacunas);
			panelVacunas.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPaneVacunas = new JScrollPane();
			panelVacunas.add(scrollPaneVacunas, BorderLayout.CENTER);
			
			tableVacunas = new JTable();
			String[] heardersVacunas = {"Codigo","Nombre","Fabricante","Tipo de Vacuna"};
			modelVacunas = new DefaultTableModel();
			tableVacunas.setModel(modelVacunas);
			modelVacunas.setColumnIdentifiers(heardersVacunas);
			scrollPaneVacunas.setViewportView(tableVacunas);
			
			panelDatosConsulta = new JPanel();
			panelDatosConsulta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosConsulta.setBounds(10, 271, 770, 180);
			panel.add(panelDatosConsulta);
			panelDatosConsulta.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPaneConsultas = new JScrollPane();
			scrollPaneConsultas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelDatosConsulta.add(scrollPaneConsultas, BorderLayout.CENTER);
			
			tableDatosConsulta = new JTable();
			String [] heardersConsultas = {"Codigo","Fecha","Doctor","Diagnostico"};
			modelConsultas = new DefaultTableModel();
			tableDatosConsulta.setModel(modelConsultas);
			modelConsultas.setColumnIdentifiers(heardersConsultas);
			scrollPaneConsultas.setViewportView(tableDatosConsulta);
			
			panelEnfermedades = new JPanel();
			panelEnfermedades.setBounds(10, 271, 770, 180);
			panel.add(panelEnfermedades);
			panelEnfermedades.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPaneEnfermedades = new JScrollPane();
			scrollPaneEnfermedades.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelEnfermedades.add(scrollPaneEnfermedades, BorderLayout.CENTER);
			
			tableEnfermedades = new JTable();
			tableEnfermedades.setRowSelectionAllowed(false);
			tableEnfermedades.setModel(modelEnfermedades);
			scrollPaneEnfermedades.setViewportView(tableEnfermedades);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	
	private void loadEnfermedades(Paciente paciente) throws SQLException {
		
		modelEnfermedades.setRowCount(0);
		rowsEnfermedades = new Object[modelEnfermedades.getColumnCount()];
	
		
	if(paciente!= null) {
		String query = "select enfermedad.cod_enf, enfermedad.nombre_enf,tipo_enfermedad.nombre_tipo from enfermedad,historia_clinica,enfermedad_contenida_historia,tipo_enfermedad "
				+ " where historia_clinica.cod_historia = enfermedad_contenida_historia.cod_historia "
				+ " and enfermedad_contenida_historia.cod_enf = enfermedad.cod_enf and enfermedad.cod_tipo = tipo_enfermedad.cod_tipo and historia_clinica.ced_paciente = ?";
		
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		stament.setString(1, paciente.getCedula());
		
		ResultSet res = stament.executeQuery();
		
		while(res.next()) {
			rowsEnfermedades[0]= res.getString("cod_enf");
			rowsEnfermedades[1]= res.getString("nombre_enf");
			rowsEnfermedades[2]= res.getString("nombre_tipo");	
			modelEnfermedades.addRow(rowsEnfermedades);	
		}
		
		stament.close();
		res.close();
		}
		
	}
	
	private void loadVacunas(Paciente paciente) throws SQLException {
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		
		if(paciente!= null) {
			String query = "select vacuna.*,fabricante.nombre_fab from vacuna,historia_clinica,vacuna_contenida_historia,fabricante "
					+ "where historia_clinica.cod_historia = vacuna_contenida_historia.cod_historia "
					+ "and vacuna.cod_vacuna = vacuna_contenida_historia.cod_vacuna and vacuna.cod_fab = fabricante.cod_fab "
					+ " and historia_clinica.ced_paciente = ?";
			
			PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			stament.setString(1, paciente.getCedula());
			
			ResultSet res = stament.executeQuery();
			
			while(res.next()) {
				rowsVacunas[0]= res.getString("cod_vacuna");
				rowsVacunas[1]= res.getString("nombre_vacuna");
				rowsVacunas[2]= res.getString("nombre_fab");
				rowsVacunas[3]= res.getString("tipo_vacuna");
				modelVacunas.addRow(rowsVacunas);
			}
			
			stament.close();
			res.close();
		
		}
	}
	
	private void loadConsultas(Paciente paciente) throws SQLException {

		
		modelConsultas.setRowCount(0);
		rowsConsultas = new Object[modelConsultas.getColumnCount()];
		
		if(paciente!= null) {
			String query = "select consulta.*,medico.nombre,medico.apellido "
					+ "from consulta,historia_clinica,medico "
					+ "where consulta.cod_historia = historia_clinica.cod_historia and consulta.cod_medico = medico.cod_medico "
					+ "and historia_clinica.ced_paciente = ?";
			
			PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			stament.setString(1, paciente.getCedula());
			
			ResultSet res = stament.executeQuery();
			
			while(res.next()) {
				rowsConsultas[0]= res.getString("cod_consulta");
				rowsConsultas[1] = res.getString("fecha_consulta");
				rowsConsultas[2] = res.getString("nombre") +" "+res.getString("apellido") ;
				rowsConsultas[3]= res.getString("diagnostico");
				modelConsultas.addRow(rowsConsultas);
				
			}
			stament.close();
			res.close();
		}
	}
}
