package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.ConexionSQL;
import logico.Medico;
import logico.Paciente;
import logico.Usuario;
import logico.CitaMedica;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class ListadoPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelpac;
	private JTable tablepac;
	private DefaultTableModel modelpac;
	private Object[] rowpac;
	private JScrollPane scrollpac;
	private JButton btnNewButton_2;
	private Medico med;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPaciente dialog = new ListadoPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create the dialog.
	 */
	public ListadoPaciente(Usuario user) throws SQLException {
		if(user instanceof Medico)
		{
			med = (Medico) user;
		}
		setResizable(false);
		setModal(true);
		setTitle("Listado de pacientes");
		setBounds(100, 100, 965, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel buttonpane = new JPanel();
		buttonpane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonpane.setBounds(0, 435, 963, 57);
		contentPanel.add(buttonpane);
		buttonpane.setLayout(null);
		
		btnNewButton_2 = new JButton("Salir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(831, 11, 89, 23);
		buttonpane.add(btnNewButton_2);
		
		panelpac = new JPanel();
		panelpac.setBorder(new TitledBorder(null, "Listado de pacientes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelpac.setBounds(10, 11, 933, 403);
		contentPanel.add(panelpac);
		panelpac.setLayout(new BorderLayout(0, 0));
		
		scrollpac = new JScrollPane();
		scrollpac.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelpac.add(scrollpac, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		{
			tablepac = new JTable();
			tablepac.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String[] hearderspac = {"Cedula","Nombre","Genero","Fecha de nacimiento","Direccion","Telefono"};
			modelpac = new DefaultTableModel();
			tablepac.setModel(modelpac);
			modelpac.setColumnIdentifiers(hearderspac);
			scrollpac.setViewportView(tablepac);
		}
		loadPacientes();
	}
	private void loadPacientes() throws SQLException{
		modelpac.setRowCount(0);
		rowpac = new Object[modelpac.getColumnCount()];
		
		if(med != null){
			
		String query = "select  paciente.*,ciudad.nombre_ciudad,provincia.nombre_provincia "
				+ "from paciente,ciudad,provincia, cita_medica "
				+ "where cita_medica.cod_medico = ? and paciente.cod_ciudad = ciudad.cod_ciudad "
				+ "and ciudad.cod_provincia = provincia.cod_provincia and cita_medica.ced_paciente = paciente.ced_paciente";
		
		PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
		stament.setString(1, med.getId());
		
		ResultSet resul = stament.executeQuery();
		
		
		while(resul.next()) {
			
				rowpac[0] = resul.getString("ced_paciente");
				rowpac[1] =resul.getString("nombre");
				rowpac[2] = resul.getString("genero");
				rowpac[3] = resul.getDate("fecha_nac");
				rowpac[4] = resul.getString("nombre_ciudad")+", "+resul.getString("nombre_provincia");
				rowpac[5] = resul.getString("telefono");

					modelpac.addRow(rowpac);
				}
			}
		else{
			
			String query = "select  paciente.*,ciudad.nombre_ciudad,provincia.nombre_provincia "
					+ "from paciente,ciudad,provincia, cita_medica "
					+ "where paciente.cod_ciudad = ciudad.cod_ciudad and ciudad.cod_provincia = provincia.cod_provincia and cita_medica.ced_paciente = paciente.ced_paciente";
			PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			ResultSet resul = stament.executeQuery();
			
			
			while(resul.next()) {
			
						rowpac[0] = resul.getString("ced_paciente");
						rowpac[1] =resul.getString("nombre");
						rowpac[2] = resul.getString("genero");
						rowpac[3] = resul.getDate("fecha_nac");
						rowpac[4] = resul.getString("nombre_ciudad")+", "+resul.getString("nombre_provincia");
						rowpac[5] = resul.getString("telefono");

						modelpac.addRow(rowpac);
					}
				}
	}
}

