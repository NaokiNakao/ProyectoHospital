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
import java.awt.event.ActionEvent;

import logico.Clinica;
import logico.Consulta;

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
	 */
	public ListadoConsulta() {
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
			String[] heardersconsultas = {"Codigo","Fecha de consulta","Paciente","Enfermedad","Medico"};
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
		loadConsultas();
	}
	private void loadConsultas()
	{
		modelconsulta.setRowCount(0);
		rowsconsulta = new Object[modelconsulta.getColumnCount()];
		
		for (int i = 0; i < Clinica.getInstance().getCitasMedicas().size(); i++) {
			rowsconsulta[0] = Clinica.getInstance().getCitasMedicas().get(i).getCodigo();
			rowsconsulta[1] = Clinica.getInstance().getCitasMedicas().get(i).getFechaCita();
			rowsconsulta[2] = Clinica.getInstance().getCitasMedicas().get(i).getNombrePersona();
			rowsconsulta[3] = Clinica.getInstance().getCitasMedicas().get(i).getMedico();
			modelconsulta.addRow(rowsconsulta);
		}
		
	}
}



