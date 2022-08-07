package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.CitaMedica;
import logico.Clinica;
import logico.ConexionSQL;
import logico.Medico;
import logico.Paciente;
import logico.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistroCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnBuscarCedula;
	private JTextField txtCodigoCita;
	private JTable tableDoctoresDisponibles;
	private JButton btnBuscarFecha;

	private Paciente paciente;
	private Medico selectedMedico;
	private ArrayList<Medico> medicos;
	
	private DefaultTableModel modelMedicos;
	private Object[] rowsMedicos;
	private JSpinner spnFechaCita;
	private String fecha;
	private JComboBox cbxSexoPersona;
	private JSpinner spnNacimiento;
	
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
		
		medicos = new ArrayList<>();
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
			txtCedula.setBounds(83, 31, 143, 23);
			panelDatosCliente.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscarCedula = new JButton("Buscar\r\n");
			btnBuscarCedula.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				if(txtCedula.getText().toString().equalsIgnoreCase("")) {	
					JOptionPane.showMessageDialog(null, "Favor completar todas los campos", "Error", JOptionPane.ERROR_MESSAGE);
	
				}else {
					try {
						if(Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())!= null){
							
							
							txtCedula.setEditable(false);
							txtNombre.setEditable(false);
							txtDireccion.setEditable(false);
							txtTelefono.setEditable(false);
							cbxSexoPersona.setEnabled(false);
							spnNacimiento.setEnabled(false);
							
							paciente = Clinica.getInstance().buscarPaciente(txtCedula.getText());
							txtNombre.setText(paciente.getNombre());
							txtDireccion.setText(paciente.getDireccion());
							txtTelefono.setText(paciente.getTelefono());
							
							if(paciente.getGenero().equalsIgnoreCase("Masculino")) {
									String sexo = "Masculino";
									cbxSexoPersona.setSelectedItem(sexo);
								}else {
									String sexo = "Femenino";
									cbxSexoPersona.setSelectedItem(sexo);
								}
							
							spnNacimiento.setValue(paciente.getFechaNacimiento());
							
							
						}else {
							txtCedula.setEditable(true);
							txtNombre.setEditable(true);
							txtDireccion.setEditable(true);
							txtTelefono.setEditable(true);
							cbxSexoPersona.setEnabled(true);
							spnNacimiento.setEnabled(true);
							cbxSexoPersona.setSelectedItem(-1);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
			});
			btnBuscarCedula.setBounds(270, 31, 89, 23);
			panelDatosCliente.add(btnBuscarCedula);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(83, 82, 143, 23);
			panelDatosCliente.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(327, 82, 143, 23);
			panelDatosCliente.add(txtTelefono);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(83, 131, 387, 23);
			panelDatosCliente.add(txtDireccion);
			
			JLabel lblNewLabel = new JLabel("Cedula:");
			lblNewLabel.setBounds(10, 35, 63, 14);
			panelDatosCliente.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(9, 86, 63, 14);
			panelDatosCliente.add(lblNewLabel_1);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 135, 63, 14);
			panelDatosCliente.add(lblDireccion);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(250, 86, 63, 14);
			panelDatosCliente.add(lblTelefono);
			
			JLabel labelSexo = new JLabel("Sexo:");
			labelSexo.setBounds(503, 86, 63, 14);
			panelDatosCliente.add(labelSexo);
			
			cbxSexoPersona = new JComboBox();
			cbxSexoPersona.setEnabled(false);
			cbxSexoPersona.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Masculino", "Femenino"}));
			cbxSexoPersona.setBounds(576, 82, 144, 23);
			panelDatosCliente.add(cbxSexoPersona);
			
			JLabel lblNacimiento = new JLabel("Nacimiento:");
			lblNacimiento.setBounds(503, 135, 73, 14);
			panelDatosCliente.add(lblNacimiento);
			
			spnNacimiento = new JSpinner();
			spnNacimiento.setEnabled(false);
			Date date = new Date();
			spnNacimiento.setModel(new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnNacimiento,"dd/MMM/yyyy");
			spnNacimiento.setEditor(de_spnFecha);
			spnNacimiento.setBounds(576, 131, 144, 23);
			panelDatosCliente.add(spnNacimiento);
			
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
			lblNewLabel_3.setBounds(267, 34, 63, 14);
			panelDatosConsulta.add(lblNewLabel_3);
			
			btnBuscarFecha = new JButton("Buscar\r\n");
			btnBuscarFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Date dateAux = (Date) spnFechaCita.getValue();
					Date fechaActual = new Date();
					
					try {
						if(Clinica.getInstance().citaByCedula(txtCedula.getText().toString(), dateAux.toString()) != null) {
							JOptionPane.showMessageDialog(null, "Ya este paciente tiene una cita a esa hora.", "Error", JOptionPane.ERROR_MESSAGE);
							
						}else {
							if(dateAux.compareTo(fechaActual) < 0) {
								JOptionPane.showMessageDialog(null, "Favor introducir una fecha valida.", "Error", JOptionPane.ERROR_MESSAGE);	
							}else {
								String dateAux2 = (String) spnFechaCita.getValue();
								SimpleDateFormat de = new SimpleDateFormat("MM/DD/YYY HH:mm");
								fecha = de.format(dateAux2);
								try {
									loadMedicos(dateAux2);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								}
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
									
				}
			});
			btnBuscarFecha.setBounds(544, 31, 89, 23);
			panelDatosConsulta.add(btnBuscarFecha);
			
			JPanel panelDoctoresDisponibles = new JPanel();
			panelDoctoresDisponibles.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDoctoresDisponibles.setBounds(10, 72, 727, 141);
			panelDatosConsulta.add(panelDoctoresDisponibles);
			panelDoctoresDisponibles.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelDoctoresDisponibles.add(scrollPane, BorderLayout.CENTER);
			
			tableDoctoresDisponibles = new JTable();
			tableDoctoresDisponibles.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = tableDoctoresDisponibles.getSelectedRow();
					if(aux!=-1) {
						
						String cod = (String) modelMedicos.getValueAt(aux, 0);
						try {
							selectedMedico=Clinica.getInstance().buscarMedicoByCodigo(cod);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			String[] heardersMedicos = {"Codigo","Nombre","Especialidad"};
			modelMedicos = new DefaultTableModel();
			tableDoctoresDisponibles.setModel(modelMedicos);
			modelMedicos.setColumnIdentifiers(heardersMedicos);
			tableDoctoresDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tableDoctoresDisponibles);
			Date date2 = new Date();
			
			spnFechaCita = new JSpinner();
			spnFechaCita.setModel(new SpinnerDateModel(date2, null, null, Calendar.DAY_OF_MONTH));
			JSpinner.DateEditor FechaCita = new JSpinner.DateEditor(spnFechaCita,"dd/MMM/yyyy  HH:mm");
			spnFechaCita.setEditor(FechaCita);
			spnFechaCita.setBounds(327, 31, 165, 23);
			panelDatosConsulta.add(spnFechaCita);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Date fechaCita = (Date) spnFechaCita.getValue();
						Date fechaActual = new Date();
						
						SimpleDateFormat r = new SimpleDateFormat("MM/DD/YYY");
						String	fecha2 = r.format(fechaCita);
						
						Date date1 = null;
						try {
							date1 = new SimpleDateFormat("MM/DD/YYY").parse(fecha2);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						Date fechaNacimiento = (Date) spnNacimiento.getValue();
						
					try {
						if(Clinica.getInstance().citaByCedula(txtCedula.getText().toString(), fechaCita.toString()) != null) {
							JOptionPane.showMessageDialog(null, "Ya este paciente tiene una cita a esa hora.", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							
								if(espaciosVacios()) {
									JOptionPane.showMessageDialog(null, "Favor completar todos los datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
								}else {
									
									if(fechaCita.compareTo(fechaActual) < 0) {
										JOptionPane.showMessageDialog(null, "Favor introducir una fecha valida.", "Error", JOptionPane.ERROR_MESSAGE);
									}else {
									
										if(fechaNacimiento.compareTo(fechaActual) > 0 ) {
											JOptionPane.showMessageDialog(null, "Favor introducir una fecha de nacimiento valida.", "Error", JOptionPane.ERROR_MESSAGE);
										}else {
											
											if(selectedMedico == null) {
												JOptionPane.showMessageDialog(null, "Favor seleccionar un medico.", "Error", JOptionPane.ERROR_MESSAGE);
											}else {
												CitaMedica cita;
												try {
													cita = new CitaMedica(txtCodigoCita.getText().toString(),fechaCita.toString(),
															selectedMedico, Clinica.getInstance().buscarPaciente(txtCedula.getText().toString()), "pendiente");
													
													Clinica.getInstance().insertarCita(cita);
													JOptionPane.showMessageDialog(null, "Registro Exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
													vacearEspacios();
												
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
																
												
											}
											
										}
									}
								}
							}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		
		// Genera codigo para la cita
		txtCodigoCita.setText("CT-"+Clinica.getInstance().generadorCodigo(4));
	}
	

	private void loadMedicos(String fecha) throws SQLException {
		
		modelMedicos.setRowCount(0);
		rowsMedicos = new Object[modelMedicos.getColumnCount()];
		
		String query = "select medico.*, especialidad.* from especialidad,medico,medico_especialidad "
				+ "where medico_especialidad.cod_medico = medico.cod_medico "
				+ "and medico_especialidad.cod_especialidad = especialidad.cod_especialidad";
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		ResultSet resul = stament.executeQuery();
		
		while(resul.next()) {
			
			if(Clinica.getInstance().medicoDisponible(fecha, resul.getString("cod_medico"))) {
				rowsMedicos[0]= resul.getString("cod_medico");
				rowsMedicos[1]= resul.getString("nombre")+" "+resul.getString("apellido");
				rowsMedicos[2] = resul.getString("nombre_especialidad");
				modelMedicos.addRow(rowsMedicos);
			}
			
			
		}
		
	}
	
	private boolean espaciosVacios() {
		
		boolean vacio = false;
		Date hoy = new Date();
		
		if(txtCedula.getText().toString().equalsIgnoreCase("") || txtNombre.getText().toString().equalsIgnoreCase("") 
				|| txtTelefono.getText().toString().equalsIgnoreCase("") ||
				txtDireccion.getText().toString().equalsIgnoreCase("") || txtCodigoCita.getText().toString().equalsIgnoreCase("") /*|| selectedMedico==null */
				|| cbxSexoPersona.getSelectedItem().toString().equalsIgnoreCase("<<Seleccione>>") || spnNacimiento.getValue().equals(hoy)) {
			
			vacio = true;
			
		}
		
		return vacio;
	}
	
	private void vacearEspacios() {
		
		Date hoy = new Date();
		
		txtCedula.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCodigoCita.setText("Cita-"+Clinica.getInstance().generadorCodigo(4));
		cbxSexoPersona.setSelectedItem(-1);
		spnNacimiento.setValue(hoy);
		spnFechaCita.setValue(hoy);
		
		
	}
	
}
