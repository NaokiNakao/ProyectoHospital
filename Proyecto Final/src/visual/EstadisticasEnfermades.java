package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

public class EstadisticasEnfermades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableEnfermedades;
	private JTable tableVacunas;
	private Enfermedad selectedEnfermedad;
	
	private DefaultTableModel modelEnfermedades;
	private Object[] rowsEnfermedades;
	
	private DefaultTableModel modelVacunas;
	private Object [] rowsVacunas;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EstadisticasEnfermades dialog = new EstadisticasEnfermades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EstadisticasEnfermades() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 855, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			
			
			
			Enfermedad covid = new Enfermedad("1021", "covid", "Respiratoria", "Malisima compai");
			Clinica.getInstance().insertarEnfermedad(covid);
			
			Enfermedad covid2 = new Enfermedad("1028", "covid2", "Respiratoria", "Malisima compai");
			Clinica.getInstance().insertarEnfermedad(covid2);
			
			ArrayList<Enfermedad> r = new ArrayList<>();
			
			r.add(covid);
			
			
			Vacuna sinovac = new Vacuna("620", "Sinovac", "Yo", r, "P", "P");
			Vacuna rv = new Vacuna("8952", "tula", "Tambien yo", r, "Ayh", "p");
			
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
						loadVacunas(selectedEnfermedad);
						//System.out.println(Clinica.getInstance().buscarEnfermedadByCodigo(cod).getNombreEnfermedad());
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
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(286, 87, 267, 135);
			panel.add(panel_2);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setBounds(286, 252, 267, 135);
			panel.add(panel_3);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_4.setBounds(571, 21, 249, 442);
			panel.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_4.add(scrollPane_1, BorderLayout.CENTER);
			
			tableVacunas = new JTable();
			String[] heardersVacunas = {"Codigo","Nombre","Fabricante"};
			modelVacunas = new DefaultTableModel();
			tableVacunas.setModel(modelVacunas);
			modelVacunas.setColumnIdentifiers(heardersVacunas);
			scrollPane_1.setViewportView(tableVacunas);
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
			
			loadEnfermedades();
			
		}
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
	
	
	private void loadVacunas(Enfermedad enfermedad) {
		
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		
		ArrayList<Vacuna> misVacunas = new ArrayList<>();
		if(enfermedad!= null) {

			misVacunas =  Clinica.getInstance().vacunasParaEnfermedad(enfermedad.getCodigo());
		
			for (int i = 0; i < misVacunas.size(); i++) {
				rowsVacunas[0]= misVacunas.get(i).getCodigo();
				rowsVacunas[1]=  misVacunas.get(i).getNombreVacuna();
				rowsVacunas[2]= misVacunas.get(i).getFabricante();
				modelVacunas.addRow(rowsVacunas);
			}
		
		}
	}
	
}
