package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.CitaMedica;
import logico.Clinica;
import logico.ConexionSQL;
import logico.Medico;
import logico.Usuario;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanelUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTipo;
	private JLabel lblHolder;
	private JPanel panelAgenda;
	private JButton btnPacientes;
	private JButton btnEnfermedades;
	private JButton btnVacunas;
	private JButton btnUsuarios;
	private JTable tableAgenda;
	private static DefaultTableModel modelAgenda;
	private static Object[] rowsAgenda;
	private CitaMedica siguienteCita;
	private JButton btnConsulta;
	private JButton btnNuevaCitas;
	private JButton btnMisConsultas;
	private JButton btnNewButton;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public PanelUsuario(Usuario user) throws SQLException {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 743, 470);
		setLocationRelativeTo(null);
		if (user.getId().contains("A") && user!= null) {
			setTitle("Administrador");
		}
		else if (user.getId().contains("M") && user!= null) {
			setTitle("Medico");
			//loadAgenda(user);
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelMenu = new JPanel();
			panelMenu.setForeground(new Color(0, 0, 0));
			panelMenu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMenu.setBounds(573, 0, 154, 393);
			contentPanel.add(panelMenu);
			panelMenu.setLayout(null);
			
			btnPacientes = new JButton("Pacientes");
			btnPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ListadoPaciente listado;
					try {
						listado = new ListadoPaciente(user);
						listado.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			btnPacientes.setBounds(10, 11, 134, 25);
			panelMenu.add(btnPacientes);
			
			btnEnfermedades = new JButton("Enfermedades");
			btnEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EstadisticaEnfermedad frame;
					try {
						frame = new EstadisticaEnfermedad(user);
						frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnEnfermedades.setBounds(10, 47, 134, 25);
			panelMenu.add(btnEnfermedades);
			
			btnVacunas = new JButton("Vacunas");
			btnVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListadoVacuna listado;
					try {
						listado = new ListadoVacuna();
						listado.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnVacunas.setBounds(10, 83, 134, 25);
			panelMenu.add(btnVacunas);
			
			btnUsuarios = new JButton("Usuarios");
			btnUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ListadoUsuario listado;
					try {
						listado = new ListadoUsuario();
						listado.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnUsuarios.setBounds(10, 155, 134, 25);
			if (user.getId().contains("M") && user!= null) {
				btnUsuarios.setVisible(false);
			}
			panelMenu.add(btnUsuarios);
			
			btnConsulta = new JButton("Sgte Consulta");
			btnConsulta.setBounds(10, 155, 134, 25);
			if (user.getId().contains("A") && user!= null) {
				btnConsulta.setVisible(false);
			}
			panelMenu.add(btnConsulta);
			
			btnNuevaCitas = new JButton("Nueva cita");
			btnNuevaCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					RegistroCita r = new RegistroCita();
					r.setVisible(true);
				}
			});
			btnNuevaCitas.setBounds(10, 120, 134, 25);
			if (user.getId().contains("M") && user!= null ) {
				btnNuevaCitas.setVisible(false);
			}
			panelMenu.add(btnNuevaCitas);
			
			btnMisConsultas = new JButton("Mis Consultas");
			btnMisConsultas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListadoConsulta t;
					try {
						t = new ListadoConsulta();
						t.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnMisConsultas.setBounds(10, 121, 134, 23);
			panelMenu.add(btnMisConsultas);
			
			btnNewButton = new JButton("Historial");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HistorialPaciente t = new HistorialPaciente();
					t.setVisible(true);
				}
			});
			btnNewButton.setBounds(10, 194, 134, 23);
			if (user.getId().contains("A") && user!= null ) {
				btnNewButton.setVisible(false);
			}
			panelMenu.add(btnConsulta);
			
			panelMenu.add(btnNewButton);
			btnConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					 Medico med = new Medico(user.getId(), user.getLogin(), user.getPassword(), user.getNombre(), user.getApellido(), user.getApellido());
					
					try {
						if(CitasPendientes(med)==0) {			
							JOptionPane.showMessageDialog(null, "Hasta ahora no tiene ninguna cita pendiente", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							dispose();
							siguienteCita = siguienteCita(med);
							ConsultasVisual rc;
							try {
								rc = new ConsultasVisual(siguienteCita, med);
								rc.setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		
		lblHolder = new JLabel("");
		String imgPath = null;
		if (user.getId().contains("A") && user!= null) {
			imgPath = "/pictures/admin.png";
		}
		else if (user.getId().contains("A") && user!= null ) {
			imgPath = "/pictures/medicoperfil.png";
		}
		//lblHolder.setIcon(new ImageIcon(PanelUsuario.class.getResource(imgPath)));
		lblHolder.setBounds(10, 24, 190, 213);
		contentPanel.add(lblHolder);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 249, 190, 23);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		if (user.getId().contains("A") && user!= null ) {
			txtNombre.setText(user.getNombre() + " " + user.getApellido());
		}
		else if (user.getId().contains("M") && user!= null ) {
			txtNombre.setText("Dr. " + user.getNombre() + " " + user.getApellido());
		}
		
		txtTipo = new JTextField();
		txtTipo.setBorder(null);
		txtTipo.setEditable(false);
		txtTipo.setBounds(10, 283, 190, 23);
		contentPanel.add(txtTipo);
		txtTipo.setColumns(10);
		if (user.getId().contains("A") && user!= null ) {
			txtTipo.setText(((Administrador) user).getPuestoLaboral());
		}
		else if (user.getId().contains("M") && user!= null) {
			txtTipo.setText(Clinica.getInstance().buscarEspecialidadCodByCodMedico(user.getId()));
		}
		
		panelAgenda = new JPanel();
		panelAgenda.setBorder(new TitledBorder(null, "Agenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgenda.setBounds(220, 11, 324, 352);
		if (user.getId().contains("A") && user!= null) {
			panelAgenda.setVisible(false);
		}
		contentPanel.add(panelAgenda);
		panelAgenda.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelAgenda.add(scrollPane, BorderLayout.CENTER);
		
		tableAgenda = new JTable();
		String[] heardersAgenda = {"Codigo","Paciente","Fecha"};
		modelAgenda = new DefaultTableModel();
		tableAgenda.setModel(modelAgenda);
		modelAgenda.setColumnIdentifiers(heardersAgenda);
		tableAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableAgenda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						InicioSesion r = new InicioSesion();
						r.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (user.getId().contains("M") && user!= null) {
			loadAgenda(user);
		}
	}
	
	private void loadAgenda(Usuario medico) throws SQLException {
		modelAgenda.setRowCount(0);
		rowsAgenda = new Object[modelAgenda.getColumnCount()];
				
		String query = "select cita_medica.*,paciente.nombre,paciente.apellido from cita_medica,paciente "
				+ "where "
				+ " cod_medico = ? and estado = ? and "
				+ "cita_medica.ced_paciente = paciente.ced_paciente order by fecha_hora_cita";
		
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareCall(query);
		stament.setString(1, medico.getId());
		stament.setString(2, "Pendiente");
		
		ResultSet resul = stament.executeQuery();
		
		while (resul.next()){
			rowsAgenda[0]=  resul.getString("cod_cita");
			rowsAgenda[1]=  resul.getString("nombre")+" "+ resul.getString("apellido");
			rowsAgenda[2]=  resul.getDate("fecha_hora_cita");
			modelAgenda.addRow(rowsAgenda);
		}
	}
	
	
	public int CitasPendientes(Medico med) throws SQLException {
		
		int citas = 0;
		
		String query = "select medico.nombre+' '+medico.apellido as medico,count(cita_medica.estado) as citas_pendientes from cita_medica,medico "
				+ "where estado = 'pendiente' and medico.cod_medico = cita_medica.cod_medico "
				+ "and medico.cod_medico = ? "
				+ "group by medico.nombre,medico.apellido";
		
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		stament.setString(1, med.getId());
		ResultSet resul =stament.executeQuery();
		
		while(resul.next()) {
			
			citas = resul.getInt("citas_pendientes");
		}
		return citas;
	}
	
	public CitaMedica siguienteCita(Medico med) throws SQLException {
		
		CitaMedica sgtCita = null;
		
		
		String query = "select TOP 1 cita_medica.*,paciente.nombre,paciente.apellido from cita_medica,paciente "
				+ "where "
				+ " cod_medico = ? and estado = ? and "
				+ "cita_medica.ced_paciente = paciente.ced_paciente order by fecha_hora_cita";
		
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareCall(query);
		stament.setString(1, med.getId());
		stament.setString(2, "Pendiente");
		
		ResultSet resul = stament.executeQuery();
		
		while(resul.next()) {
			
			sgtCita = new CitaMedica(resul.getString("cod_cita"),
					Clinica.getInstance().formatoFechaHora(resul.getString("fecha_hora_cita")), med, 
					Clinica.getInstance().buscarPaciente(resul.getString("ced_paciente")),resul.getString("estado"));
		}
		
		
		return sgtCita;
		
	}
	
	
}
