package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class DatosConsultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodConsulta;
	private JTextField txtDatosPaciente;
	private JTextField txtDatosMedico;
	private JTextField textDiagnostico;
	private JTable tableEnfs;
	private JTextField textField;
	private JTable tableVacunas;
	
	private DefaultTableModel modelVacuna;
	private Object[] rowsVacuna;
	
	private DefaultTableModel modelEnfermades;
	private Object[] rowsEnfermades;
	

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
		lblNewLabel.setBounds(10, 16, 104, 14);
		panel.add(lblNewLabel);
		
		txtCodConsulta = new JTextField();
		txtCodConsulta.setBounds(106, 10, 191, 20);
		panel.add(txtCodConsulta);
		txtCodConsulta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Paciente");
		lblNewLabel_1.setBounds(10, 60, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtDatosPaciente = new JTextField();
		txtDatosPaciente.setBounds(106, 57, 191, 20);
		panel.add(txtDatosPaciente);
		txtDatosPaciente.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Medico:");
		lblNewLabel_2.setBounds(336, 60, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtDatosMedico = new JTextField();
		txtDatosMedico.setColumns(10);
		txtDatosMedico.setBounds(392, 57, 191, 20);
		panel.add(txtDatosMedico);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(315, 7, 89, 23);
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
		
		textDiagnostico = new JTextField();
		textDiagnostico.setBounds(10, 11, 152, 280);
		panelTextFieldDiagnostico.add(textDiagnostico);
		textDiagnostico.setColumns(10);
		
		JPanel panelSintomas = new JPanel();
		panelSintomas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSintomas.setBounds(192, 36, 397, 128);
		panel_1.add(panelSintomas);
		panelSintomas.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 377, 106);
		panelSintomas.add(textField);
		textField.setColumns(10);
		
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
		String[] heardersEnf = {"Codigo","Fecha de consulta","Paciente","Diagnostico","Medico"};
		modelEnfermades = new DefaultTableModel();
		tableEnfs.setModel(modelEnfermades);
		modelEnfermades.setColumnIdentifiers(heardersEnf);
		scrollPaneEnf.setViewportView(tableEnfs);
		
		JPanel panelVacuna = new JPanel();
		panelVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVacuna.setBounds(617, 36, 172, 302);
		panel_1.add(panelVacuna);
		panelVacuna.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneVacuna = new JScrollPane();
		scrollPaneVacuna.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelVacuna.add(scrollPaneVacuna, BorderLayout.CENTER);
		
		tableVacunas = new JTable();
		tableVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] heardersVac = {"Codigo","Fecha de consulta","Paciente","Diagnostico","Medico"};
		modelEnfermades = new DefaultTableModel();
		tableVacunas.setModel(modelVacuna);
		modelVacuna.setColumnIdentifiers(heardersVac);
		scrollPaneVacuna.setViewportView(tableVacunas);
		
		JLabel lblNewLabel_4 = new JLabel("Sintomas");
		lblNewLabel_4.setBounds(196, 11, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("Enfermedades diagnosticadas:");
		lblNewLabel_3_1.setBounds(196, 185, 172, 14);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_5 = new JLabel("Vacunas:");
		lblNewLabel_5.setBounds(617, 11, 46, 14);
		panel_1.add(lblNewLabel_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	void loadEnf() {
		
	}
	
	
}
