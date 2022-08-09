package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JFormattedTextField$AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatter;

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
	private JFormattedTextField TxtFechaCita;
	private JFormattedTextField txtHoraCita;
	private JFormattedTextField TxtFechaCita_1;
	
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
							
							txtNombre.setEditable(false);
							txtDireccion.setEditable(false);
							txtTelefono.setEditable(false);
							cbxSexoPersona.setEnabled(false);
							spnNacimiento.setEnabled(false);
							
							paciente = Clinica.getInstance().buscarPaciente(txtCedula.getText());
							txtNombre.setText(paciente.getNombre());
							txtDireccion.setText(paciente.getDireccion());
							txtTelefono.setText(paciente.getTelefono());
							
							if(paciente.getGenero().equalsIgnoreCase("M")) {
									String sexo = "Masculino";
									cbxSexoPersona.setSelectedItem(sexo);
								}else {
									String sexo = "Femenino";
									cbxSexoPersona.setSelectedItem(sexo);
								}
							
							spnNacimiento.setValue(paciente.getFechaNacimiento());
							
							
						}else if(Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())== null) {
							
							
							
							txtNombre.setText("");
							txtDireccion.setText("");
							txtTelefono.setText("");
							cbxSexoPersona.setSelectedItem(-1);
							spnNacimiento.setValue(new Date());
							
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
			spnNacimiento.setVisible(false);
			spnNacimiento.setEnabled(false);
			Date date = new Date();
			spnNacimiento.setModel(new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnNacimiento,"dd/MMM/yyyy");
			spnNacimiento.setEditor(de_spnFecha);
			spnNacimiento.setBounds(576, 131, 144, 23);
			panelDatosCliente.add(spnNacimiento);
			
			spnFechaCita = new JSpinner();
			spnFechaCita.setBounds(536, 31, 165, 23);
			panelDatosCliente.add(spnFechaCita);
		//	Date date2 = ;
			//spnFechaCita.setModel(new SpinnerDateModel(date2, null, null, Calendar.DAY_OF_MONTH));
			//JSpinner.DateEditor FechaCita = new JSpinner.DateEditor(spnFechaCita,"yyyy-MM-dd hh:mm:ss");
		//	spnFechaCita.setEditor(FechaCita);
			spnFechaCita.setEnabled(false);
			
			TxtFechaCita_1 = new JFormattedTextField((AbstractFormatter) null);
			TxtFechaCita_1.setColumns(10);
			TxtFechaCita_1.setBounds(576, 131, 144, 23);
			panelDatosCliente.add(TxtFechaCita_1);
			spnFechaCita.setVisible(false);
			
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
					
					//Date dateAux = (Date) spnFechaCita.getValue();
					
					Date fechaActual = new Date();
					String aux = TxtFechaCita.getText() + " " + txtHoraCita.getText();
					SimpleDateFormat formato = new SimpleDateFormat("yy/MM/dd");
					
					try {
						
						Date dataFormateada = formato.parse(TxtFechaCita.getText());
					
					if(Clinica.getInstance().citaByCedula(txtCedula.getText(), aux) != null) {
							JOptionPane.showMessageDialog(null, "Ya este paciente tiene una cita a esa hora.", "Error", JOptionPane.ERROR_MESSAGE);
							txtHoraCita.setText("");
							TxtFechaCita.setText("");
							
						}else {
							if(dataFormateada.compareTo(fechaActual)< 0) {
								
								JOptionPane.showMessageDialog(null, "Favor introducir una fecha valida.", "Error", JOptionPane.ERROR_MESSAGE);	
								txtHoraCita.setText("");
								TxtFechaCita.setText("");
								loadMedicos(null);
							}else {
								try {
									loadMedicos(aux);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					} catch (HeadlessException | SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
									
				}
			});
			btnBuscarFecha.setBounds(607, 30, 89, 23);
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
			//Date date2 = new Date();
			
			MaskFormatter formatoFecha = null;
			try {
				formatoFecha = new MaskFormatter("####/##/##");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			TxtFechaCita = new JFormattedTextField(formatoFecha);		
			TxtFechaCita.setBounds(313, 31, 116, 20);
			panelDatosConsulta.add(TxtFechaCita);
			TxtFechaCita.setColumns(10);
			
			MaskFormatter formatoHora = null;
			try {
				formatoHora = new MaskFormatter("##:##:##");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			txtHoraCita = new JFormattedTextField(formatoHora);
			txtHoraCita.setColumns(10);
			txtHoraCita.setBounds(460, 31, 116, 20);
			panelDatosConsulta.add(txtHoraCita);
			
			JLabel lblNewLabel_4 = new JLabel("Dia:");
			lblNewLabel_4.setBounds(348, 11, 46, 14);
			panelDatosConsulta.add(lblNewLabel_4);
			
			JLabel lblNewLabel_4_1 = new JLabel("Hora:");
			lblNewLabel_4_1.setBounds(495, 11, 46, 14);
			panelDatosConsulta.add(lblNewLabel_4_1);
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

						Date fechaActual = new Date();
						String aux = TxtFechaCita.getText() + " " + txtHoraCita.getText();
						SimpleDateFormat formato = new SimpleDateFormat("yy/MM/dd");
						Date fechaNacimiento = (Date) spnNacimiento.getValue();
						
						
					try {
						Date dataFormateada = formato.parse(TxtFechaCita.getText());
						
						if(Clinica.getInstance().citaByCedula(txtCedula.getText().toString(), aux) != null ) {
							JOptionPane.showMessageDialog(null, "Ya este paciente tiene un cita en esa fecha", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							
								if(espaciosVacios()) {
									JOptionPane.showMessageDialog(null, "Favor completar todos los datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
								}else {
									
									if( dataFormateada.compareTo(fechaActual)< 0) {
										JOptionPane.showMessageDialog(null, "Favor introducir una fecha valida.", "Error", JOptionPane.ERROR_MESSAGE);
									}else {
									
										if(fechaNacimiento.compareTo(fechaActual) > 0 ) {
											JOptionPane.showMessageDialog(null, "Favor introducir una fecha de nacimiento valida.", "Error", JOptionPane.ERROR_MESSAGE);
											spnNacimiento.setValue(null);
										}else {
											
											if(selectedMedico == null) {
												JOptionPane.showMessageDialog(null, "Favor seleccionar un medico.", "Error", JOptionPane.ERROR_MESSAGE);
											}else if(selectedMedico != null && Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())!= null) {
												CitaMedica cita;
												try {
													cita = new CitaMedica(txtCodigoCita.getText().toString(),aux,
															selectedMedico, Clinica.getInstance().buscarPaciente(txtCedula.getText().toString()), "pendiente");
													
													if(Clinica.getInstance().insertarCita(cita)) {
														JOptionPane.showMessageDialog(null, "Registro Exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
														vacearEspacios();
													}
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}else if (selectedMedico != null && Clinica.getInstance().buscarPaciente(txtCedula.getText().toString())== null) {
												
												Paciente paciente = null;
												
												if(cbxSexoPersona.getSelectedItem().toString().equalsIgnoreCase("Masculino")) {
													 paciente = new Paciente(txtCedula.getText().toString(), txtNombre.getText().toString(),
															"M", (Date) spnNacimiento.getValue(), txtDireccion.getText().toString(), txtTelefono.getText().toString());
												}else if (cbxSexoPersona.getSelectedItem().toString().equalsIgnoreCase("Femenino")) {
													 paciente = new Paciente(txtCedula.getText().toString(), txtNombre.getText().toString(),
															"F", (Date) spnNacimiento.getValue(), txtDireccion.getText().toString(), txtTelefono.getText().toString());
												
												}
												
												CitaMedica	cita = new CitaMedica(txtCodigoCita.getText().toString(),aux,
														selectedMedico, paciente, "pendiente");
												
												if(Clinica.getInstance().insertarCita(cita)) {
													JOptionPane.showMessageDialog(null, "Registro Exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
													vacearEspacios();
												}
												
											}
											
										}
									}
								}
							}
					} catch (HeadlessException | SQLException | ParseException e1) {
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
		
		if(fecha!= null) {
		String query = "select medico.*, especialidad.* from especialidad inner join medico "
				+ " on medico.cod_especialidad = especialidad.cod_especialidad ";
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
		}else {
			rowsMedicos[0]= null;
			rowsMedicos[1]= null;
			rowsMedicos[2] = null;
			modelMedicos.addRow(rowsMedicos);
		}
		
	}
	
	private boolean espaciosVacios() {
		
		boolean vacio = false;
		Date hoy = new Date();
		
		if(txtCedula.getText().toString().equalsIgnoreCase("") || txtNombre.getText().toString().equalsIgnoreCase("") 
				|| txtTelefono.getText().toString().equalsIgnoreCase("") ||
				txtDireccion.getText().toString().equalsIgnoreCase("") || txtCodigoCita.getText().toString().equalsIgnoreCase("") /*|| selectedMedico==null */
				|| cbxSexoPersona.getSelectedItem().toString().equalsIgnoreCase("<<Seleccione>>") || spnNacimiento.getValue().equals(hoy) 
				|| txtHoraCita.getText().equalsIgnoreCase("")|| TxtFechaCita.getText().equalsIgnoreCase("")) {
			
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
		//spnFechaCita.setValue(hoy);
		
		
	}
}
