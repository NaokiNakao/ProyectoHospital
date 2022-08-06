package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Checkbox;
import javax.swing.JLabel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.CitaMedica;
import logico.Clinica;
import logico.ConexionSQL;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistoriaClinica;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class ConsultasVisual extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPaciente;
	private JButton btnHistorial;
	private JButton btnReceta;
	private JTextPane textPaneSintomas;
	private JTextPane textPaneDiagnostico;
	private JTable tableEnfermedades;
	private static DefaultTableModel modelEnfermedades;
	private static Object[] rowsEnfermedades;
	private JPanel panelReceta;
	private JTable tableVacunas;
	private static DefaultTableModel modelVacunas;
	private static Object[] rowsVacunas;
	private Enfermedad selectedEnfermedad;
	private Vacuna selectedVacuna;
	private JTextPane textPaneReceta;
	private JButton btnFinalizar;
	private JLabel lblCodigoConsulta;
	
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultasVisual dialog = new ConsultasVisual(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ConsultasVisual(CitaMedica cita,Medico medico) throws SQLException {
		setModal(true);
		setTitle("Consulta");
		setResizable(false);
		setBounds(100, 100, 920, 598);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			final Panel panelSintomas = new Panel();
			panelSintomas.setBounds(20, 38, 459, 456);
			panel.add(panelSintomas);
			panelSintomas.setLayout(null);
			
			
			JButton btnSiguiente = new JButton("Siguiente");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				
					if(textPaneSintomas.getText().toString().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Favor colocar al menos un sintoma", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						panelSintomas.setVisible(false);
						try {
							loadEnfermedades();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			btnSiguiente.setBounds(173, 415, 112, 23);
			panelSintomas.add(btnSiguiente);
			
			JLabel lblSintomas = new JLabel("Sintomas");
			lblSintomas.setBounds(184, 11, 90, 25);
			panelSintomas.add(lblSintomas);
			lblSintomas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			{
				textPaneSintomas = new JTextPane();
				textPaneSintomas.setBounds(33, 47, 392, 343);
				panelSintomas.add(textPaneSintomas);
			}
			
			JPanel panelPaciente = new JPanel();
			panelPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPaciente.setBounds(519, 71, 334, 371);
			panel.add(panelPaciente);
			panelPaciente.setLayout(null);
			
			txtPaciente = new JTextField();
			txtPaciente.setText(cita.getNombrePersona());
			txtPaciente.setEditable(false);
			txtPaciente.setBounds(10, 342, 204, 20);
			panelPaciente.add(txtPaciente);
			txtPaciente.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setIcon(new ImageIcon(ConsultasVisual.class.getResource("/pictures/paciente hablando.gif")));
			lblNewLabel_2.setBounds(10, 27, 308, 304);
			panelPaciente.add(lblNewLabel_2);
			
			Panel panelDiagnostico = new Panel();
			panelDiagnostico.setBounds(20, 38, 459, 456);
			panel.add(panelDiagnostico);
			panelDiagnostico.setLayout(null);
			
			JLabel lblDiagnostico = new JLabel("Diagnostico");
			lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDiagnostico.setBounds(184, 11, 90, 25);
			panelDiagnostico.add(lblDiagnostico);
			
			textPaneDiagnostico = new JTextPane();
			textPaneDiagnostico.setBounds(33, 216, 392, 174);
			panelDiagnostico.add(textPaneDiagnostico);
			
			JButton btnVolver = new JButton("Sintomas");
			btnVolver.setBounds(67, 415, 128, 23);
			panelDiagnostico.add(btnVolver);
			
			btnReceta = new JButton("Receta");
			btnReceta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(textPaneDiagnostico.getText().toString().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Favor colocar una despripcion del diagnostico", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						panelSintomas.setVisible(false);
						panelDiagnostico.setVisible(false);
						panelReceta.setVisible(true);
					}
				}
			});
			btnReceta.setBounds(262, 415, 128, 23);
			panelDiagnostico.add(btnReceta);
			
			JPanel panelEnfer = new JPanel();
			panelEnfer.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEnfer.setBounds(33, 47, 392, 158);
			panelDiagnostico.add(panelEnfer);
			panelEnfer.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Seleccione la enfermedad a diagnosticar:");
			lblNewLabel.setBounds(72, 11, 247, 14);
			panelEnfer.add(lblNewLabel);
			
			JPanel panelTablaEnfermedades = new JPanel();
			panelTablaEnfermedades.setBounds(21, 43, 342, 104);
			panelEnfer.add(panelTablaEnfermedades);
			panelTablaEnfermedades.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelTablaEnfermedades.add(scrollPane, BorderLayout.CENTER);
			
			tableEnfermedades = new JTable();
			tableEnfermedades.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = tableEnfermedades.getSelectedRow();
					if(aux!=-1) {
						String cod = (String) modelEnfermedades.getValueAt(aux, 0);
						try {
							selectedEnfermedad=Clinica.getInstance().buscarEnfermedadByCodigo(cod);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
			String[] heardersEnfermedades= {"Codigo","Nombre","Tipo"};
			modelEnfermedades = new DefaultTableModel();
			tableEnfermedades.setModel(modelEnfermedades);
			modelEnfermedades.setColumnIdentifiers(heardersEnfermedades);
			tableEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tableEnfermedades);
			
			btnHistorial = new JButton("Historial");
			btnHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HistorialPaciente p = new HistorialPaciente();
					p.setVisible(true);
				}
			});
			btnHistorial.setBounds(672, 453, 112, 23);
			panel.add(btnHistorial);
			
			panelReceta = new JPanel();
			panelReceta.setBounds(20, 38, 459, 456);
			panel.add(panelReceta);
			panelReceta.setLayout(null);
			
			JLabel lblReceta = new JLabel("Receta");
			lblReceta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblReceta.setBounds(184, 11, 90, 25);
			panelReceta.add(lblReceta);
			
			textPaneReceta = new JTextPane();
			textPaneReceta.setBounds(33, 216, 392, 174);
			panelReceta.add(textPaneReceta);
			String[] heardersVacunas = {"Codigo","Nombre","Fabricante"};
			modelVacunas = new DefaultTableModel();
			modelVacunas.setColumnIdentifiers(heardersVacunas);
			
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setBounds(262, 415, 128, 23);
			panelReceta.add(btnFinalizar);
			
			JButton btnDiagnostico = new JButton("Diagnostico");
			btnDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelDiagnostico.setVisible(true);
				}
			});
			btnDiagnostico.setBounds(67, 415, 128, 23);
			panelReceta.add(btnDiagnostico);
			
			JPanel panelVacu = new JPanel();
			panelVacu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelVacu.setBounds(33, 47, 392, 158);
			panelReceta.add(panelVacu);
			panelVacu.setLayout(null);
			
			JPanel panelVacunas = new JPanel();
			panelVacunas.setBounds(21, 43, 342, 104);
			panelVacu.add(panelVacunas);
			panelVacunas.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelVacunas.add(scrollPane_1, BorderLayout.CENTER);
			
			tableVacunas = new JTable();
			tableVacunas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = tableVacunas.getSelectedRow();
					if(aux!=-1) {
						String cod = (String) modelVacunas.getValueAt(aux, 0);
						try {
							selectedVacuna=Clinica.getInstance().buscarVacunaByCodigo(cod);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			tableVacunas.setModel(modelVacunas);
			scrollPane_1.setViewportView(tableVacunas);
			
			JLabel lblNewLabel_1 = new JLabel("Seleccione la vacuna a recetar:");
			lblNewLabel_1.setBounds(72, 11, 247, 14);
			panelVacu.add(lblNewLabel_1);
			
			lblCodigoConsulta = new JLabel("New label");
			lblCodigoConsulta.setBounds(10, 11, 121, 14);
			// Se toman los ultimos 8 digitos CitaMedica para formar en codigo de Consulta
			String codigo = cita.getCodigo().substring(3);
			lblCodigoConsulta.setText("CS-"+codigo);
			panel.add(lblCodigoConsulta);
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (textPaneReceta.getText().toString().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Favor colocar una receta", "Error", JOptionPane.ERROR_MESSAGE);
					}else { 
						
						
						try {
							if(Clinica.getInstance().buscarPaciente(cita.getCedulaPersona())!= null) {
								
								Paciente p = Clinica.getInstance().buscarPaciente(cita.getCedulaPersona());
								
								if(p.getHistorial()!= null) {
									
								HistoriaClinica h = p.getHistorial();

								
								Consulta n = new Consulta(lblCodigoConsulta.getText().toString(), cita.getFechaCita(), textPaneSintomas.getText().toString(), textPaneDiagnostico.getText().toString(),
										medico, textPaneReceta.getText().toString(),cita.getFechaN());
								
								if(selectedEnfermedad!=null) {
									n.setEnfermedad(selectedEnfermedad);
									h.getPadecimientos().add(selectedEnfermedad);
								}
								
								if(selectedVacuna != null) {
									n.setMisVacunas(selectedVacuna);
									h.getMisVacunas().add(selectedVacuna);
								}
								
								Clinica.getInstance().insertarConsultaV2(n, medico, cita, p, h);
								JOptionPane.showMessageDialog(null, "Consulta Completada", "Exito", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								PanelUsuario u = new PanelUsuario(medico);
								u.setVisible(true);
								}
							}else {
								HistoriaClinica h = new HistoriaClinica("Historial-"+cita.getCedulaPersona());
								
								Paciente p = new Paciente(cita.getCedulaPersona(), cita.getNombrePersona(), cita.getGeneroPersona(), cita.getFechaNacimientoPersona(), 
											cita.getDireccionPersona(), cita.getTelefono());
								
								p.setHistorial(h);
								
								Consulta n = new Consulta(lblCodigoConsulta.getText().toString(), cita.getFechaCita(), textPaneSintomas.getText().toString(), textPaneDiagnostico.getText().toString(),
										medico, textPaneReceta.getText().toString(),cita.getFechaN());
								
								if(selectedEnfermedad!=null) {
									n.setEnfermedad(selectedEnfermedad);
									h.getPadecimientos().add(selectedEnfermedad);
								}
								
								if(selectedVacuna != null) {
									n.setMisVacunas(selectedVacuna);
									h.getMisVacunas().add(selectedVacuna);
								}
								
								
								Clinica.getInstance().insertarConsulta(n, medico, cita, p, h);
								//dispose();
								JOptionPane.showMessageDialog(null, "Consulta Completada", "Exito", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								
								PanelUsuario u = new PanelUsuario(medico);
								u.setVisible(true);
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelSintomas.setVisible(true);
				}
			});
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
						PanelUsuario u = new PanelUsuario(medico);
						u.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadVacunas();
		}
		loadVacunas();
	}
	private void loadEnfermedades() throws SQLException {
		
		modelEnfermedades.setRowCount(0);
		rowsEnfermedades = new Object[modelEnfermedades.getColumnCount()];
		
		
		String queryTipoEnf = "select enfermedad.*, tipo_enfermedad.nombre_tipo from enfermedad, tipo_enfermedad "
							+ "where enfermedad.cod_tipo = tipo_enfermedad.cod_tipo";
		
		PreparedStatement stamentTipo = ConexionSQL.getInstance().getConexion().prepareStatement(queryTipoEnf);
		ResultSet res2 = stamentTipo.executeQuery();
		
		while(res2.next()) {
			rowsEnfermedades[0]=res2.getString("cod_enf");
			rowsEnfermedades[1]=res2.getString("nombre_enf");
			rowsEnfermedades[2]=res2.getString("nombre_tipo");
			modelEnfermedades.addRow(rowsEnfermedades);
		}
		
		stamentTipo.close();
		res2.close();
		
	}
	
	private void loadVacunas() throws SQLException {
		
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		
		
		String queryVacFab = "select vacuna.*,fabricante.nombre_fab from vacuna,fabricante where vacuna.cod_fab = fabricante.cod_fab";
		PreparedStatement stamentFab = ConexionSQL.getInstance().getConexion().prepareStatement(queryVacFab);
		ResultSet res2 = stamentFab.executeQuery();
		
		while( res2.next()) {
			rowsVacunas[0]=res2.getString("cod_vacuna");
			rowsVacunas[1]=res2.getString("nombre_vacuna");
			rowsVacunas[2]=res2.getString("nombre_fab");
			modelVacunas.addRow(rowsVacunas);
		}
		

		stamentFab.close();
		res2.close();
		
		}
	}

