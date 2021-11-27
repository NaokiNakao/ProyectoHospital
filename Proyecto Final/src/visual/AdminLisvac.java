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
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class AdminLisvac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtvaccodregis;
	private JTextField txtvacnomb;
	private JTextField txtvaccod;
	private JTextField txtvactipo;
	private JTextField txtformadminis;
	private JTextField txtvacfabri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminLisvac dialog = new AdminLisvac();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminLisvac() {
		setResizable(false);
		setTitle("Listado de vacunas");
		setBounds(100, 100, 578, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 542, 240);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Codigo", "Fabricante", "Proteccion", "Tipo", "Forma de Adminis."
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(99);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(97);
		table.getColumnModel().getColumn(5).setPreferredWidth(159);
		scrollPane.setViewportView(table);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Buscador:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 542, 196);
			contentPanel.add(panel);
			{
				JLabel lblCodigo = new JLabel("Codigo:");
				lblCodigo.setBounds(10, 24, 58, 14);
				panel.add(lblCodigo);
			}
			{
				txtvaccodregis = new JTextField();
				txtvaccodregis.setColumns(10);
				txtvaccodregis.setBounds(66, 21, 93, 20);
				panel.add(txtvaccodregis);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(41, 49, 66, 14);
				panel.add(label);
			}
			{
				txtvacnomb = new JTextField();
				txtvacnomb.setEditable(false);
				txtvacnomb.setColumns(10);
				txtvacnomb.setBounds(41, 74, 93, 20);
				panel.add(txtvacnomb);
			}
			{
				JLabel lblCodigo_1 = new JLabel("Codigo:");
				lblCodigo_1.setBounds(175, 49, 66, 14);
				panel.add(lblCodigo_1);
			}
			{
				txtvaccod = new JTextField();
				txtvaccod.setEditable(false);
				txtvaccod.setColumns(10);
				txtvaccod.setBounds(175, 74, 93, 20);
				panel.add(txtvaccod);
			}
			{
				JLabel lblFabricante = new JLabel("Fabricante:");
				lblFabricante.setBounds(309, 49, 113, 14);
				panel.add(lblFabricante);
			}
			{
				JLabel lblTipo = new JLabel("Tipo:");
				lblTipo.setBounds(41, 112, 118, 14);
				panel.add(lblTipo);
			}
			{
				txtvactipo = new JTextField();
				txtvactipo.setEditable(false);
				txtvactipo.setColumns(10);
				txtvactipo.setBounds(41, 137, 93, 20);
				panel.add(txtvactipo);
			}
			{
				JLabel lblFormaDeAdministracion = new JLabel("Forma de Administracion:");
				lblFormaDeAdministracion.setBounds(175, 112, 164, 14);
				panel.add(lblFormaDeAdministracion);
			}
			{
				txtformadminis = new JTextField();
				txtformadminis.setEditable(false);
				txtformadminis.setColumns(10);
				txtformadminis.setBounds(175, 137, 113, 20);
				panel.add(txtformadminis);
			}
			{
				JButton button = new JButton("Buscar");
				button.setBounds(179, 20, 89, 23);
				panel.add(button);
			}
			{
				txtvacfabri = new JTextField();
				txtvacfabri.setEditable(false);
				txtvacfabri.setColumns(10);
				txtvacfabri.setBounds(309, 74, 93, 20);
				panel.add(txtvacfabri);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Eliminar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
