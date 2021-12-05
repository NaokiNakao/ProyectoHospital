package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdBuscado;
	private JTextField txtNombre;
	private JTextField txtLogin;
	private JTextField txtContrasenna;
	private JTextField txtTelefono;
	private JTextField txtId;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoUsuario dialog = new ListadoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoUsuario() {
		setModal(true);
		setResizable(false);
		setTitle("Listado de usuarios");
		setBounds(100, 100, 583, 534);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Buscador:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 542, 196);
			contentPanel.add(panel);
			{
				JLabel lblId_1 = new JLabel("ID:");
				lblId_1.setBounds(10, 24, 58, 14);
				panel.add(lblId_1);
			}
			{
				txtIdBuscado = new JTextField();
				txtIdBuscado.setColumns(10);
				txtIdBuscado.setBounds(41, 21, 93, 20);
				panel.add(txtIdBuscado);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(41, 49, 66, 14);
				panel.add(label);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(41, 74, 93, 20);
				panel.add(txtNombre);
			}
			{
				JLabel lblId = new JLabel("Login:");
				lblId.setBounds(175, 49, 66, 14);
				panel.add(lblId);
			}
			{
				txtLogin = new JTextField();
				txtLogin.setEditable(false);
				txtLogin.setColumns(10);
				txtLogin.setBounds(175, 74, 93, 20);
				panel.add(txtLogin);
			}
			{
				JLabel lblContrasea = new JLabel("ID:");
				lblContrasea.setBounds(309, 49, 113, 14);
				panel.add(lblContrasea);
			}
			{
				JLabel lblContrasea_1 = new JLabel("Contrase\u00F1a:");
				lblContrasea_1.setBounds(41, 112, 118, 14);
				panel.add(lblContrasea_1);
			}
			{
				txtContrasenna = new JTextField();
				txtContrasenna.setEditable(false);
				txtContrasenna.setColumns(10);
				txtContrasenna.setBounds(41, 137, 93, 20);
				panel.add(txtContrasenna);
			}
			{
				JLabel lblTelefono = new JLabel("Telefono:");
				lblTelefono.setBounds(175, 112, 164, 14);
				panel.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(175, 137, 93, 20);
				panel.add(txtTelefono);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.setBounds(175, 20, 89, 23);
				panel.add(btnBuscar);
			}
			{
				txtId = new JTextField();
				txtId.setEditable(false);
				txtId.setColumns(10);
				txtId.setBounds(309, 74, 93, 20);
				panel.add(txtId);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 542, 202);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Login", "ID", "Contraseña", "Telefono"
			}
		));
		scrollPane.setViewportView(table);
		{
			JPanel buttonpane = new JPanel();
			buttonpane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonpane.setBounds(0, 454, 577, 51);
			contentPanel.add(buttonpane);
			buttonpane.setLayout(null);
			{
				JButton btnNewButton = new JButton("Crear");
				btnNewButton.setBounds(211, 11, 89, 23);
				buttonpane.add(btnNewButton);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setBounds(325, 11, 89, 23);
				buttonpane.add(btnEliminar);
			}
			{
				JButton btnSaliar = new JButton("Salir");
				btnSaliar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSaliar.setBounds(441, 11, 89, 23);
				buttonpane.add(btnSaliar);
			}
		}
	}
}
