package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import logico.Clinica;
import logico.Enfermedad;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;

public class ListadoEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_enfermedades;
	private JTable tableenfer;
	private Enfermedad selectedenfer;
	private DefaultTableModel modelenfer;
	private Object[] rowsenfer;
	private JButton btnmodificar;
	private JButton btneliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEnfermedad dialog = new ListadoEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEnfermedad() {
		setResizable(false);
		setTitle("Listado de enfermedades");
		setBounds(100, 100, 539, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel buttonpane = new JPanel();
		buttonpane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonpane.setBounds(0, 283, 533, 58);
		contentPanel.add(buttonpane);
		buttonpane.setLayout(null);
		
		tableenfer = new JTable();
		tableenfer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = tableenfer.getSelectedRow();
				if (fila != -1) {
					btnmodificar.setEnabled(true);
					btneliminar.setEnabled(true);
					String codigoenfer = (String) tableenfer.getValueAt(fila,0);
					selectedenfer = Clinica.getInstance().buscarEnfermedadByCodigo(codigoenfer);
				}
			}
		});
		
		btneliminar = new JButton("Eliminar");
		btneliminar.setEnabled(false);
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarEnfermedad();
			}
		});
		btneliminar.setBounds(284, 11, 89, 23);
		buttonpane.add(btneliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(400, 11, 89, 23);
		buttonpane.add(btnSalir);
		
		btnmodificar = new JButton("Modificar");
		btnmodificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedenfer != null)
				{
					RegistroEnfermedad enfer = new RegistroEnfermedad(selectedenfer);
					enfer.setVisible(true);
				}
			}
		});
		btnmodificar.setEnabled(false);
		btnmodificar.setBounds(169, 11, 89, 23);
		buttonpane.add(btnmodificar);
		
		panel_enfermedades = new JPanel();
		panel_enfermedades.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de enfermedades:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_enfermedades.setBounds(10, 25, 495, 239);
		contentPanel.add(panel_enfermedades);
		panel_enfermedades.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_enfermedades.add(scrollPane, BorderLayout.CENTER);
		{
			tableenfer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String[] heardersenfer = {"Codigo","Nombre","Tipo","Descripcion"};
			modelenfer = new DefaultTableModel();
			tableenfer.setModel(modelenfer);
			modelenfer.setColumnIdentifiers(heardersenfer);
			scrollPane.setViewportView(tableenfer);
		}
		loadEnfermedades();
	}
	private void loadEnfermedades()
	{
		modelenfer.setRowCount(0);
		rowsenfer = new Object[modelenfer.getColumnCount()];
		
		for (int i = 0; i < Clinica.getInstance().getMisEnfermedades().size(); i++) {
			rowsenfer[0] = Clinica.getInstance().getMisEnfermedades().get(i).getCodigo();
			rowsenfer[1] = Clinica.getInstance().getMisEnfermedades().get(i).getNombreEnfermedad();
			rowsenfer[2] = Clinica.getInstance().getMisEnfermedades().get(i).getTipoEnfermedad();
			rowsenfer[3] = Clinica.getInstance().getMisEnfermedades().get(i).getDescripcionEnfermedad();
			modelenfer.addRow(rowsenfer);
		}
		
	}
	private void eliminarEnfermedad()
	{
		int fila = tableenfer.getSelectedRow();
		if(fila >= 0)
		{
			modelenfer.removeRow(fila);
			Clinica.getInstance().getMisEnfermedades().remove(fila);
			
		}
	}
}

