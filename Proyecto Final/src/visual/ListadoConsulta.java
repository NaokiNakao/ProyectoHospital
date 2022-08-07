package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import logico.Clinica;
import logico.ConexionSQL;
import logico.Consulta;
import logico.Paciente;

public class ListadoConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_consultas;
	private JTable tableconsulta;
	private DefaultTableModel modelconsulta;
	private Object[] rowsconsulta;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoConsulta dialog = new ListadoConsulta();
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
	public ListadoConsulta() throws SQLException {
		setTitle("Listado de consultas");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1064, 437);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 28, 1038, 325);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de consultas:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 1018, 303);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_consultas = new JPanel();
		panel_consultas.setBounds(10, 27, 998, 265);
		panel_1.add(panel_consultas);
		panel_consultas.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_consultas.add(scrollPane, BorderLayout.CENTER);
		{
			tableconsulta = new JTable();
			tableconsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String[] heardersconsultas = {"Codigo","Fecha de consulta","Paciente","Diagnostico","Medico"};
			modelconsulta = new DefaultTableModel();
			tableconsulta.setModel(modelconsulta);
			modelconsulta.setColumnIdentifiers(heardersconsultas);
			scrollPane.setViewportView(tableconsulta);
		}
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setForeground(Color.BLACK);
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
		loadConsultas1();
	}

private void loadConsultas1() throws SQLException {

		
	modelconsulta.setRowCount(0);
	rowsconsulta = new Object[modelconsulta.getColumnCount()];
		
			String query = "select consulta.*,medico.nombre as nombre_doctor ,medico.apellido as apellido_doctor, paciente.* "
					+ "					from consulta,historia_clinica,medico, paciente "
					+ "					where consulta.cod_historia = historia_clinica.cod_historia and consulta.cod_medico = medico.cod_medico "
					+ "					and historia_clinica.ced_paciente = paciente.ced_paciente ";
			
			PreparedStatement stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
			
			ResultSet res = stament.executeQuery();
			
			while(res.next()) {
				
				rowsconsulta[0]= res.getString("cod_consulta");
				rowsconsulta[1] = res.getString("fecha_consulta");
				rowsconsulta[2] = res.getString("nombre")+" "+res.getString("apellido");
				rowsconsulta[3] = res.getString("diagnostico") ;
				rowsconsulta[4] = res.getString("nombre_doctor") +" "+res.getString("apellido_doctor") ;
				modelconsulta.addRow(rowsconsulta);
				
			}
			stament.close();
			res.close();
		
	}
	
	
	
	
	
}



