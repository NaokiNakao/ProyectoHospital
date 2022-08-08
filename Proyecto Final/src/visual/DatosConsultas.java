package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.ConexionSQL;
import logico.Consulta;
import logico.Paciente;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosConsultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodConsulta;
	private JTextField txtDatosPaciente;
	private JTextField txtDatosMedico;
	private JTextField txtDiagnostico;
	private JTable tableEnfs;
	private JTextField txtSintomas;
	private JTable tableVacunas;
	
	private DefaultTableModel modelVacuna;
	private Object[] rowsVacuna;
	
	private DefaultTableModel modelEnfermades;
	private Object[] rowsEnfermades;
	
	private Consulta consulta = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosConsultas dialog = new DatosConsultas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosConsultas() {
		setTitle("Consultas");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 835, 565);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 799, 111);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo Consulta:");
		lblNewLabel.setBounds(10, 13, 104, 14);
		panel.add(lblNewLabel);
		
		txtCodConsulta = new JTextField();
		txtCodConsulta.setBounds(129, 10, 191, 20);
		panel.add(txtCodConsulta);
		txtCodConsulta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Paciente");
		lblNewLabel_1.setBounds(10, 64, 86, 14);
		panel.add(lblNewLabel_1);
		
		txtDatosPaciente = new JTextField();
		txtDatosPaciente.setEditable(false);
		txtDatosPaciente.setBounds(129, 61, 191, 20);
		panel.add(txtDatosPaciente);
		txtDatosPaciente.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Medico:");
		lblNewLabel_2.setBounds(359, 64, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtDatosMedico = new JTextField();
		txtDatosMedico.setEditable(false);
		txtDatosMedico.setColumns(10);
		txtDatosMedico.setBounds(415, 61, 191, 20);
		panel.add(txtDatosMedico);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {				
					if(Clinica.getInstance().buscarConsultaByCod(txtCodConsulta.getText().toString()) != null) {
						
						consulta = Clinica.getInstance().buscarConsultaByCod(txtCodConsulta.getText().toString());
						txtDatosPaciente.setText(cargarPaciente(consulta).getNombre());
						txtDatosMedico.setText(consulta.getMiMedico().getNombre()+" "+consulta.getMiMedico().getApellido());
						
						txtDiagnostico.setText(consulta.getDiagnostico());
						txtSintomas.setText(consulta.getSintomas());
						
						loadEnfermedades(txtCodConsulta.getText().toString());
						loadVacunas(cargarPaciente(consulta).getCedula());
						
					}else {
						JOptionPane.showMessageDialog(null, "Esta consulta no existe", "Error", JOptionPane.ERROR_MESSAGE);
						txtDatosPaciente.setText(" ");
						txtDatosMedico.setText(" ");
						txtCodConsulta.setText(" ");
						txtDiagnostico.setText(" ");
						txtSintomas.setText(" ");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(338, 9, 89, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 133, 799, 349);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Diagnostico:");
		lblNewLabel_3.setBounds(10, 11, 104, 14);
		panel_1.add(lblNewLabel_3);
		
		JPanel panelTextFieldDiagnostico = new JPanel();
		panelTextFieldDiagnostico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTextFieldDiagnostico.setBounds(10, 36, 172, 302);
		panel_1.add(panelTextFieldDiagnostico);
		panelTextFieldDiagnostico.setLayout(null);
		
		txtDiagnostico = new JTextField();
		txtDiagnostico.setEditable(false);
		txtDiagnostico.setBounds(10, 11, 152, 280);
		panelTextFieldDiagnostico.add(txtDiagnostico);
		txtDiagnostico.setColumns(10);
		
		JPanel panelEnfs = new JPanel();
		panelEnfs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEnfs.setBounds(192, 210, 397, 128);
		panel_1.add(panelEnfs);
		panelEnfs.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneEnf = new JScrollPane();
		scrollPaneEnf.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelEnfs.add(scrollPaneEnf, BorderLayout.CENTER);
		

		tableEnfs = new JTable();
		tableEnfs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heardersEnf= {"Codigo","Nombre","Tipo"};
		modelEnfermades = new DefaultTableModel();
		tableEnfs.setModel(modelEnfermades);
		modelEnfermades.setColumnIdentifiers(heardersEnf);
		scrollPaneEnf.setViewportView(tableEnfs);
		
		JPanel panelVacuna = new JPanel();
		panelVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVacuna.setBounds(192, 36, 397, 128);
		panel_1.add(panelVacuna);
		panelVacuna.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneVacuna = new JScrollPane();
		scrollPaneVacuna.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelVacuna.add(scrollPaneVacuna, BorderLayout.CENTER);
		
		tableVacunas = new JTable();
		tableVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heardersVac = {"Codigo","Nombre","Fabricante"};
		modelVacuna = new DefaultTableModel();
		//tableVacunas.setModel(modelVacuna);
		modelVacuna.setColumnIdentifiers(heardersVac);
		tableVacunas.setModel(modelVacuna);
		scrollPaneVacuna.setViewportView(tableVacunas);
		
		JLabel lblNewLabel_4 = new JLabel("Sintomas");
		lblNewLabel_4.setBounds(606, 11, 138, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("Enfermedades diagnosticadas:");
		lblNewLabel_3_1.setBounds(196, 185, 299, 14);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_5 = new JLabel("Vacunas:");
		lblNewLabel_5.setBounds(192, 11, 138, 14);
		panel_1.add(lblNewLabel_5);
		
		JPanel panelSintomas = new JPanel();
		panelSintomas.setBounds(606, 36, 172, 302);
		panel_1.add(panelSintomas);
		panelSintomas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSintomas.setLayout(null);
		
		txtSintomas = new JTextField();
		txtSintomas.setEditable(false);
		txtSintomas.setBounds(10, 11, 152, 280);
		panelSintomas.add(txtSintomas);
		txtSintomas.setColumns(10);
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
	
	public Paciente cargarPaciente(Consulta consul) throws SQLException {
		
		Paciente p = null;
		String query = "select * from consulta where cod_consulta = ?";
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		stament.setString(1, consul.getCodigo());
		
		ResultSet resul = stament.executeQuery();
		
		while(resul.next()) {
			p = Clinica.getInstance().buscarPacienteByCodHistorial(resul.getString("cod_historia"));
		}
		
		stament.close();
		resul.close();
		
		return p;
	}
	
	
	
	public void loadEnfermedades(String cod_consulta) throws SQLException {
		
		modelEnfermades.setRowCount(0);
		rowsEnfermades = new Object[modelEnfermades.getColumnCount()];
		
		
		String queryTipoEnf = "select enfermedad_diagnosticada_consulta.*,enfermedad.* "
				+ "from enfermedad_diagnosticada_consulta "
				+ "inner join enfermedad "
				+ "on enfermedad.cod_enf = enfermedad_diagnosticada_consulta.cod_enf "
				+ "and enfermedad_diagnosticada_consulta.cod_consulta = ? "
				+ "order by nombre_enf asc";
		
		PreparedStatement stamentTipo = ConexionSQL.getInstance().getConexion().prepareStatement(queryTipoEnf);
		stamentTipo.setString(1, cod_consulta);
		ResultSet res2 = stamentTipo.executeQuery();
		
		while(res2.next()) {
			rowsEnfermades[0]=res2.getString("cod_enf");
			rowsEnfermades[1]=res2.getString("nombre_enf");
			rowsEnfermades[2]=res2.getString("cod_tipo");
			modelEnfermades.addRow(rowsEnfermades);
		}
		
		stamentTipo.close();
		res2.close();
		
	}
	
	public void loadVacunas(String cod_paciente) throws SQLException {
		
		modelVacuna.setRowCount(0);
		rowsVacuna = new Object[modelVacuna.getColumnCount()];
		
		String queryVacFab = "select vacuna.*,fabricante.nombre_fab from vacuna,historia_clinica,vacuna_contenida_historia,fabricante "
				+ "where historia_clinica.cod_historia = vacuna_contenida_historia.cod_historia "
				+ "and vacuna.cod_vacuna = vacuna_contenida_historia.cod_vacuna and vacuna.cod_fab = fabricante.cod_fab "
				+ " and historia_clinica.ced_paciente = ?";
		
		PreparedStatement stamentFab = ConexionSQL.getInstance().getConexion().prepareStatement(queryVacFab);
		stamentFab.setString(1, cod_paciente);
		ResultSet res2 = stamentFab.executeQuery();
		
		while( res2.next()) {
			rowsVacuna[0]=res2.getString("cod_vacuna");
			rowsVacuna[1]=res2.getString("nombre_vacuna");
			rowsVacuna[2]=res2.getString("nombre_fab");
			modelVacuna.addRow(rowsVacuna);
		}
		

		stamentFab.close();
		res2.close();
		
		}
	
	
}
