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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtenfercodregis;
	private JTextField txtenfernomb;
	private JTextField txtenfertipo;
	private JTextField txtenferdescrip;
	private JTextField txtenfercod;

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
		setBounds(100, 100, 521, 533);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 195, 468, 240);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Nombre", "Codigo", "Tipo", "Descripcion"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscador:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 11, 468, 173);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 24, 58, 14);
		panel.add(lblNewLabel);
		
		txtenfercodregis = new JTextField();
		txtenfercodregis.setBounds(66, 21, 93, 20);
		panel.add(txtenfercodregis);
		txtenfercodregis.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(41, 49, 66, 14);
		panel.add(lblNewLabel_1);
		
		txtenfernomb = new JTextField();
		txtenfernomb.setEditable(false);
		txtenfernomb.setColumns(10);
		txtenfernomb.setBounds(41, 74, 93, 20);
		panel.add(txtenfernomb);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(175, 49, 66, 14);
		panel.add(lblTipo);
		
		txtenfertipo = new JTextField();
		txtenfertipo.setEditable(false);
		txtenfertipo.setBounds(175, 74, 93, 20);
		panel.add(txtenfertipo);
		txtenfertipo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(41, 112, 93, 14);
		panel.add(lblDescripcion);
		
		txtenferdescrip = new JTextField();
		txtenferdescrip.setEditable(false);
		txtenferdescrip.setColumns(10);
		txtenferdescrip.setBounds(41, 133, 227, 20);
		panel.add(txtenferdescrip);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(290, 49, 66, 14);
		panel.add(lblCodigo);
		
		txtenfercod = new JTextField();
		txtenfercod.setEditable(false);
		txtenfercod.setColumns(10);
		txtenfercod.setBounds(290, 74, 93, 20);
		panel.add(txtenfercod);
		
		JPanel buttonpane = new JPanel();
		buttonpane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonpane.setBounds(0, 446, 515, 58);
		contentPanel.add(buttonpane);
		buttonpane.setLayout(null);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.setBounds(166, 11, 89, 23);
		buttonpane.add(btnNewButton);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(284, 11, 89, 23);
		buttonpane.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(400, 11, 89, 23);
		buttonpane.add(btnSalir);
	}
}
