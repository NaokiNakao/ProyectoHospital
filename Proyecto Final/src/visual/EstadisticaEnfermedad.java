package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistoriaClinica;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;

public class EstadisticaEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableEnfermedades;
	private JTable tableVacunas;
	private Enfermedad selectedEnfermedad;
	
	private static DefaultTableModel modelEnfermedades;
	private static Object[] rowsEnfermedades;
	
	private static DefaultTableModel modelVacunas;
	private static Object [] rowsVacunas;
	private JTextField textField;
	private JTextField txtCasosTotales;
	private JTextField txtCasosHombres;
	private JTextField txtCasosMujeres;
	private JTextField txtCasosPorFecha;
	private JSpinner spnFecha;
	private JButton btnBuscarFecha;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EstadisticaEnfermedad dialog = new EstadisticaEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EstadisticaEnfermedad() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 855, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			
			
			
			Enfermedad covid = new Enfermedad("10211111", "covid", "Respiratoria", "Malisima compai");
			Clinica.getInstance().insertarEnfermedad(covid);
			
			Enfermedad covid2 = new Enfermedad("10281111", "covid2", "Respiratoria", "Malisima compai");
			Clinica.getInstance().insertarEnfermedad(covid2);
			
			ArrayList<Enfermedad> r = new ArrayList<>();
			ArrayList<Enfermedad>t = new ArrayList<>();
			
			r.add(covid);
			r.add(covid2);
			t.add(covid2);
			
			Vacuna sinovac = new Vacuna("620", "Sinovac", "Yo", r, "P", "P");
			Vacuna rv = new Vacuna("8952", "tula", "Tambien yo", t, "Ayh", "p");
			
			Clinica.getInstance().agregarVacuna(rv);
			Clinica.getInstance().agregarVacuna(sinovac);
			
			
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 249, 442);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			tableEnfermedades = new JTable();
			tableEnfermedades.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int aux = tableEnfermedades.getSelectedRow();
					if(aux!=-1) {
						String cod = (String) modelEnfermedades.getValueAt(aux, 0);
						selectedEnfermedad=Clinica.getInstance().buscarEnfermedadByCodigo(cod);
						loadVacunas();
							
						float[] h = Clinica.getInstance().porcentajeEnfermedadPorGenero(selectedEnfermedad.getCodigo());
							
						txtCasosHombres.setText(String.valueOf(h[0])+"%");
						txtCasosMujeres.setText(String.valueOf(h[1])+"%");
						txtCasosTotales.setText(String.valueOf(Clinica.getInstance().casosEnfermedadTotal(selectedEnfermedad.getCodigo())));
							//System.out.println(Clinica.getInstance().buscarEnfermedadByCodigo(cod).getNombreEnfermedad());
						btnBuscarFecha.setEnabled(true);
						
					}else {
						txtCasosHombres.setText("");
						txtCasosMujeres.setText("");
					}
					
				}
			});
			String[] heardersEnfermedades= {"Codigo","Nombre","Tipo"};
			modelEnfermedades = new DefaultTableModel();
			tableEnfermedades.setModel(modelEnfermedades);
			modelEnfermedades.setColumnIdentifiers(heardersEnfermedades);
			tableEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tableEnfermedades);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(286, 87, 267, 135);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(132, 0, 2, 135);
			panel_2.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Casos Totales:");
			lblNewLabel.setBounds(144, 11, 86, 14);
			panel_2.add(lblNewLabel);
			
			txtCasosTotales = new JTextField();
			txtCasosTotales.setEditable(false);
			txtCasosTotales.setBounds(144, 57, 86, 20);
			panel_2.add(txtCasosTotales);
			txtCasosTotales.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Hombres:");
			lblNewLabel_2.setBounds(10, 11, 71, 14);
			panel_2.add(lblNewLabel_2);
			
			txtCasosHombres = new JTextField();
			txtCasosHombres.setEditable(false);
			txtCasosHombres.setColumns(10);
			txtCasosHombres.setBounds(10, 36, 86, 20);
			panel_2.add(txtCasosHombres);
			
			JLabel lblNewLabel_3 = new JLabel("Mujeres:");
			lblNewLabel_3.setBounds(10, 74, 71, 14);
			panel_2.add(lblNewLabel_3);
			
			txtCasosMujeres = new JTextField();
			txtCasosMujeres.setEditable(false);
			txtCasosMujeres.setColumns(10);
			txtCasosMujeres.setBounds(10, 99, 86, 20);
			panel_2.add(txtCasosMujeres);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setBounds(286, 294, 267, 135);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Fecha:");
			lblNewLabel_4.setBounds(10, 11, 46, 14);
			panel_3.add(lblNewLabel_4);
			
			txtCasosPorFecha = new JTextField();
			txtCasosPorFecha.setEditable(false);
			txtCasosPorFecha.setBounds(10, 90, 129, 20);
			panel_3.add(txtCasosPorFecha);
			txtCasosPorFecha.setColumns(10);
			
			btnBuscarFecha = new JButton("Buscar");
			btnBuscarFecha.setEnabled(false);
			btnBuscarFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Date f = (Date) spnFecha.getValue(); 
					
					txtCasosTotales.setText(String.valueOf((Clinica.getInstance().casosEnfermedadPorFecha(selectedEnfermedad.getCodigo(),f))));
				}
			});
			btnBuscarFecha.setBounds(168, 36, 89, 23);
			panel_3.add(btnBuscarFecha);
			
			JLabel lblNewLabel_5 = new JLabel("casos");
			lblNewLabel_5.setBounds(149, 93, 46, 14);
			panel_3.add(lblNewLabel_5);
			
			spnFecha = new JSpinner();
			Date date = new Date();
			spnFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spnFecha.setModel(new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha,"dd/MMM/yyyy");
			spnFecha.setEditor(de_spnFecha);
			spnFecha.setBounds(10, 37, 129, 20);
			panel_3.add(spnFecha);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_4.setBounds(571, 21, 249, 442);
			panel.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_4.add(scrollPane_1, BorderLayout.CENTER);
			
			tableVacunas = new JTable();
			tableVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String[] heardersVacunas = {"Codigo","Nombre","Fabricante"};
			modelVacunas = new DefaultTableModel();
			modelVacunas.setColumnIdentifiers(heardersVacunas);
			tableVacunas.setModel(modelVacunas);
			scrollPane_1.setViewportView(tableVacunas);
			
			JLabel lblNewLabel_1 = new JLabel("Casos");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(399, 62, 82, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblCasosPorFecha = new JLabel("Casos por fecha");
			lblCasosPorFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblCasosPorFecha.setBounds(373, 269, 128, 25);
			panel.add(lblCasosPorFecha);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}	
		}
		loadEnfermedades();
	}
	
	private void loadEnfermedades() {
		modelEnfermedades.setRowCount(0);
		rowsEnfermedades = new Object[modelEnfermedades.getColumnCount()];
				
		for (int i = 0; i < Clinica.getInstance().getMisEnfermedades().size(); i++) {
			rowsEnfermedades[0]=  Clinica.getInstance().getMisEnfermedades().get(i).getCodigo();
			rowsEnfermedades[1]=  Clinica.getInstance().getMisEnfermedades().get(i).getNombreEnfermedad();
			rowsEnfermedades[2]=  Clinica.getInstance().getMisEnfermedades().get(i).getTipoEnfermedad();
			modelEnfermedades.addRow(rowsEnfermedades);
		}
	}
	
	
	private void loadVacunas() {
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		
		ArrayList<Vacuna> misVacunas = null;
		if(selectedEnfermedad != null) {
			misVacunas =  Clinica.getInstance().vacunasParaEnfermedad(selectedEnfermedad.getCodigo());
			
			for (Vacuna vacuna : misVacunas) {
				rowsVacunas[0]= vacuna.getCodigo();
				rowsVacunas[1]=  vacuna.getNombreVacuna();
				rowsVacunas[2]= vacuna.getFabricante();
				modelVacunas.addRow(rowsVacunas);
			}
		}
	}
}
