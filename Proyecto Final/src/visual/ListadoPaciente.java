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

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class ListadoPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelpac;
	private JTable tablepac;
	private DefaultTableModel modelpac;
	private Object[] rowpac;
	private JScrollPane scrollpac;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPaciente dialog = new ListadoPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPaciente() {
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
		
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setBounds(701, 11, 89, 23);
		buttonpane.add(btnNewButton_1);
		
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
		panelpac.setBounds(10, 69, 933, 345);
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
	private void loadPacientes()
	{
		modelpac.setRowCount(0);
		rowpac = new Object[modelpac.getColumnCount()];
		
		for (int i = 0; i < Clinica.getInstance().getMisPacientes().size(); i++) {
			rowpac[0] = Clinica.getInstance().getMisPacientes().get(i).getCedula();
			rowpac[1] = Clinica.getInstance().getMisPacientes().get(i).getNombre();
			rowpac[2] = Clinica.getInstance().getMisPacientes().get(i).getGenero();
			rowpac[3] = Clinica.getInstance().getMisPacientes().get(i).getFechaNacimiento();
			rowpac[4] = Clinica.getInstance().getMisPacientes().get(i).getDireccion();
			rowpac[5] = Clinica.getInstance().getMisPacientes().get(i).getTelefono();
			
			modelpac.addRow(rowpac);
		}
		
	}
}
