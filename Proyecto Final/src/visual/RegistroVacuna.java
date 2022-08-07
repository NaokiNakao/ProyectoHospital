package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.ConexionSQL;
import logico.Enfermedad;
import logico.Vacuna;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.JToolBar;

public class RegistroVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtFabricante;
	private JComboBox cbxTipo;
	private JTable tableEnfermedades;
	private static DefaultTableModel modelEnfermedades;
	private static Object[] rowsEnfermedades;
	private Enfermedad selectedEnfermedad;
	private JTable tableProteccion;
	private static DefaultTableModel modelProteccion;
	private static Object[] rowsProteccion;
	private Enfermedad selectedProteccion;
	private JButton btnPasarDerecha;
	private JButton btnPasarIzquierda;
	private static Vacuna vacuna;
	private JComboBox cbxAdministracion;

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
	 * @throws SQLException 
	 */
	public RegistroVacuna(Vacuna vac) throws SQLException {
		selectedEnfermedad = null;
		selectedProteccion = null;
		vacuna = vac;
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
		
		JLabel label = new JLabel("Código:");
		label.setBounds(10, 12, 46, 14);
		contentPanel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(66, 8, 155, 23);
		if (vacuna == null || vacuna != null) {
			txtCodigo.setEditable(false);
			String codigo;
				codigo = "V-" + Clinica.getInstance().generadorCodigo(4);
				txtCodigo.setText(codigo);
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
		
		cbxAdministracion = new JComboBox();
		cbxAdministracion.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Intramuscular", "Intradermica", "Subcutanea", "Endovenosa", "Oral"}));
		cbxAdministracion.setBounds(360, 87, 155, 23);
		contentPanel.add(cbxAdministracion);
		
		JLabel lblAdministracin = new JLabel("Administraci\u00F3n:");
		lblAdministracin.setBounds(267, 91, 83, 14);
		contentPanel.add(lblAdministracin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Inmunizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 139, 505, 300);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelEnfermedades = new JPanel();
		panelEnfermedades.setBounds(20, 45, 173, 231);
		panel.add(panelEnfermedades);
		panelEnfermedades.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelEnfermedades.add(scrollPane, BorderLayout.CENTER);
		
		tableEnfermedades = new JTable();
		tableEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEnfermedades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableEnfermedades.getSelectedRow();
				if (index != -1) {
					
					
					vacuna = new Vacuna(txtCodigo.getText().toString(), txtNombre.getText().toString(), txtFabricante.getText().toString(),
							null, cbxTipo.getSelectedItem().toString(), cbxAdministracion.getSelectedItem().toString());
					
					
					Clinica.getInstance().agregarVacuna(vacuna);
					try {
						cargarProteccion(vacuna);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					String aux = (String) tableEnfermedades.getValueAt(index, 0);
					String codigoEnfermedad = aux.substring(0, 5);
					try {
						selectedEnfermedad = Clinica.getInstance().buscarEnfermedadByCodigo(codigoEnfermedad);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					btnPasarDerecha.setEnabled(true);
				}
			}
		});
		String headerEnfermedad[] = {"Enfermedad"};
		modelEnfermedades = new DefaultTableModel();
		modelEnfermedades.setColumnIdentifiers(headerEnfermedad);
		tableEnfermedades.setModel(modelEnfermedades);
		scrollPane.setViewportView(tableEnfermedades);
		
		JPanel panelProteccion = new JPanel();
		panelProteccion.setBounds(311, 45, 173, 231);
		panel.add(panelProteccion);
		panelProteccion.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelProteccion.add(scrollPane_1, BorderLayout.CENTER);
		
		tableProteccion = new JTable();
		tableProteccion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProteccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableProteccion.getSelectedRow();
				if (index != -1) {
					String aux = (String) tableProteccion.getValueAt(index, 0);
					String codigoProteccion = aux.substring(0, 5);
					try {
						selectedProteccion = Clinica.getInstance().buscarEnfermedadByCodigo(codigoProteccion);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnPasarIzquierda.setEnabled(true);
				}
			}
		});
		String headerProteccion[] = {"Proteccion"};
		modelProteccion = new DefaultTableModel();
		modelProteccion.setColumnIdentifiers(headerProteccion);
		tableProteccion.setModel(modelProteccion);
		scrollPane_1.setViewportView(tableProteccion);
		
		btnPasarDerecha = new JButton(">>");
		btnPasarDerecha.setEnabled(false);
		btnPasarDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//vacuna.getProteccion().add(selectedEnfermedad);//////////////////////////////////////////////////////////////////////
				try {
					InsertarVacProtec(vacuna,selectedEnfermedad);
					cargarProteccion(vacuna);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "No puede duplicar esta enfermedad", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				btnPasarDerecha.setEnabled(false);
			}
		});
		btnPasarDerecha.setBounds(216, 114, 77, 23);
		panel.add(btnPasarDerecha);
		
		btnPasarIzquierda = new JButton("<<");
		btnPasarIzquierda.setEnabled(false);
		btnPasarIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*int index = tableProteccion.getSelectedRow();
				if (index != -1) {*/
					try {
						EliminarVacProtec(vacuna,selectedProteccion);
						cargarProteccion(vacuna);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnPasarIzquierda.setEnabled(false);
				//}
			}
		});
		btnPasarIzquierda.setBounds(216, 161, 77, 23);
		panel.add(btnPasarIzquierda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				if (vacuna == null) {
					okButton.setText("Agregar");
				}
				else {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						if(okButton.getText().toString().equalsIgnoreCase("Modificar")) {
							
							Vacuna vex = new Vacuna(txtCodigo.getText(), txtNombre.getText(), txtFabricante.getText(), null,
									cbxTipo.getSelectedItem().toString(), cbxAdministracion.getSelectedItem().toString());
							
							if(Clinica.getInstance().modificarVacuna(vex)) {
								JOptionPane.showMessageDialog(null, "Datos modificados.", "Información", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							
						}
						
						
						if (vacuna == null){
							
							if(espaciosVacios()) {
								JOptionPane.showMessageDialog(null, "Favor completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
							}else {
							ArrayList<Enfermedad> proteccion = new ArrayList<Enfermedad>();
							
							Vacuna nuevaVacuna = new Vacuna(txtCodigo.getText(), txtNombre.getText(), txtFabricante.getText(), proteccion,
									cbxTipo.getSelectedItem().toString(), cbxAdministracion.getSelectedItem().toString());
							
							if(txtFabricante.getText() != null) {
								try {
									insertIntoFabricantes(txtFabricante.getText());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							try {
								if (Clinica.getInstance().agregarVacuna(nuevaVacuna)) {
									JOptionPane.showMessageDialog(null, "La vacuna se ha agregado.", "Registro satisfactorio", JOptionPane.INFORMATION_MESSAGE);
									limpiarCampos();
								}
								else {
									JOptionPane.showMessageDialog(null, "Datos no validos.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (HeadlessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						}
						try {
							ListadoVacuna.cargarVacunas(null);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (vacuna != null) {
			cargarDatosCampos();
		}
		cargarEnfermedades();
		cargarProteccion(vacuna);
	}
	
	private boolean espaciosVacios() {
		
		boolean p = false;
		
		if(txtCodigo.getText().equalsIgnoreCase(" ") || txtFabricante.getText().equalsIgnoreCase(" ") || txtNombre.getText().equalsIgnoreCase(" ")) {
			p = true;
		}
		
		return p;
	}
	
	private void insertIntoFabricantes(String nombre) throws SQLException {
		
		String query = "insert into fabricante values(?)";
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		stament.setString(1, nombre);
		
		stament.executeUpdate();
		
		stament.close();
		
	}
	
	
	private void cargarEnfermedades() throws SQLException {
		modelEnfermedades.setRowCount(0);
		rowsEnfermedades = new Object[modelEnfermedades.getColumnCount()];
		
		String queryTipoEnf = "select enfermedad.*, tipo_enfermedad.nombre_tipo from enfermedad, tipo_enfermedad "
							+ "where enfermedad.cod_tipo = tipo_enfermedad.cod_tipo";
		
		PreparedStatement stamentTipo = ConexionSQL.getInstance().getConexion().prepareStatement(queryTipoEnf);
		ResultSet res2 = stamentTipo.executeQuery();
		
		while(res2.next()) {
			rowsEnfermedades[0]=res2.getString("cod_enf") + " : " + res2.getString("nombre_enf");
			modelEnfermedades.addRow(rowsEnfermedades);
			
		}
		
		
		
	}
	
	
	
	
	private void cargarProteccion(Vacuna vacuna) throws SQLException {
		modelProteccion.setRowCount(0);
		rowsProteccion = new Object[modelProteccion.getColumnCount()];
		
		if(vacuna!= null) {
		String queryTipoEnf = "select distinct enfermedad.* "
				+ " from vacuna,vacuna_proteccion_enfermedad,enfermedad"
				+ " where enfermedad.cod_enf = vacuna_proteccion_enfermedad.cod_enf "
				+ " and vacuna.cod_vacuna = ? and vacuna.cod_vacuna = vacuna_proteccion_enfermedad.cod_vacuna";

		PreparedStatement stamentTipo = ConexionSQL.getInstance().getConexion().prepareStatement(queryTipoEnf);
		stamentTipo.setString(1, vacuna.getCodigo());
		ResultSet res2 = stamentTipo.executeQuery();

		while(res2.next()) {
			rowsProteccion[0]=res2.getString("cod_enf") + " : " + res2.getString("nombre_enf");
			modelProteccion.addRow(rowsProteccion);

			}
		}
	}
	
	private void cargarDatosCampos() {
		if (vacuna != null) {
			txtCodigo.setText(vacuna.getCodigo());
			txtNombre.setText(vacuna.getNombreVacuna());
			txtFabricante.setText(vacuna.getFabricante());
			cbxTipo.setSelectedItem(vacuna.getTipoVacuna());
			cbxAdministracion.setSelectedItem(vacuna.getFormaAdministracion());
		}
	}
	
	private void limpiarCampos() {
		String codigo;
		
		codigo = "V-" + Clinica.getInstance().generadorCodigo(4);
		txtCodigo.setText(codigo);
		txtNombre.setText("");
		txtFabricante.setText("");
		cbxTipo.setSelectedIndex(0);
		cbxAdministracion.setSelectedIndex(0);
	}
	
	public void InsertarVacProtec(Vacuna vacuna, Enfermedad enf) throws SQLException {
		
		String query = null;
		PreparedStatement stament = null;
		int resul = 0;
		
		if(vacuna ==null) {
			
		}else if (vacuna != null && enf != null) {
			
			query = " insert into vacuna_proteccion_enfermedad values(?,?)"; 
			stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			stament.setString(1, vacuna.getCodigo());
			stament.setString(2, enf.getCodigo());
			resul = stament.executeUpdate();
		}
		
		
	}
	
	
	public void EliminarVacProtec(Vacuna vacuna, Enfermedad enf) throws SQLException {
		
		String query = null;
		PreparedStatement stament = null;
		int resul = 0;
		
		if(vacuna != null && enf != null) {
			
			query = "Delete vacuna_proteccion_enfermedad "
					+ "from vacuna_proteccion_enfermedad"
					+ " where vacuna_proteccion_enfermedad.cod_vacuna = ? and vacuna_proteccion_enfermedad.cod_enf = ?";
			
			stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			stament.setString(1, vacuna.getCodigo());
			stament.setString(2, enf.getCodigo());
			
			resul = stament.executeUpdate();
		}
		
		
	}
	
	
}














