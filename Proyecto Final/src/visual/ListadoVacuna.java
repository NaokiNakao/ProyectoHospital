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
import logico.ConexionSQL;
import logico.Medico;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigoVacuna;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private Vacuna selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVacuna dialog = new ListadoVacuna();
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
	public ListadoVacuna() throws SQLException {
		selected = null;
		setTitle("Vacunas");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 620, 456);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 584, 67);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 20, 46, 14);
		panel.add(lblNewLabel);
		
		txtCodigoVacuna = new JTextField();
		txtCodigoVacuna.setBounds(66, 16, 121, 23);
		panel.add(txtCodigoVacuna);
		txtCodigoVacuna.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vacuna vacuna;
				try {
					vacuna = Clinica.getInstance().buscarVacunaByCodigo(txtCodigoVacuna.getText());
					
					if (vacuna != null) {
						RegistroVacuna frame = new RegistroVacuna(vacuna);
						frame.setVisible(true);
						txtCodigoVacuna.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "La vacuna no se encuentra registrada.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(197, 16, 89, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 89, 584, 278);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int aux = table.getSelectedRow();
				if (aux != -1) {
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					String codigoVacuna = (String) table.getValueAt(aux, 0);
					try {
						selected = Clinica.getInstance().buscarVacunaByCodigo(codigoVacuna);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String header[] = {"Código", "Nombre", "Administración", "Tipo", "Fabricante"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							RegistroVacuna frame;
							try {
								frame = new RegistroVacuna(selected);
								frame.setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}
				});
				
				JButton btnNueva = new JButton("Nueva");
				btnNueva.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistroVacuna registro;
						try {
							registro = new RegistroVacuna(null);
							registro.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				if (Clinica.getLoginUser() instanceof Medico) {
					btnNueva.setVisible(false);
				}
				buttonPane.add(btnNueva);
				btnModificar.setEnabled(false);
				if (Clinica.getLoginUser() instanceof Medico) {
					btnModificar.setEnabled(false);
				}
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar la vacuna?", "Confirmación", JOptionPane.WARNING_MESSAGE);
						if (opcion == JOptionPane.YES_OPTION) {
							//int index = Clinica.getInstance().indexByCodigoVacuna(selected.getCodigo());
							try {
								Clinica.getInstance().eliminarVacuna(selected.getCodigo());
								cargarVacunas();												
								btnModificar.setEnabled(false);
								btnEliminar.setEnabled(false);
								//selected = null;
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							selected = null;
						}
					}
				});
				btnEliminar.setEnabled(false);
				if (Clinica.getLoginUser() instanceof Medico) {
					btnEliminar.setVisible(false);
				}
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		
		cargarVacunas();
	}
	
	public static void cargarVacunas() throws SQLException {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		String queryVacFab = "select vacuna.*,fabricante.nombre_fab from vacuna,fabricante where vacuna.cod_fab = fabricante.cod_fab";
		PreparedStatement stamentFab = ConexionSQL.getInstance().getConexion().prepareStatement(queryVacFab);
		ResultSet res2 = stamentFab.executeQuery();
		
		while( res2.next()) {
			rows[0]=res2.getString("cod_vacuna");
			rows[1]=res2.getString("nombre_vacuna");
			rows[2]=res2.getString("forma_admin");
			rows[3]=res2.getString("tipo_vacuna");
			rows[4]=res2.getString("nombre_fab");
			model.addRow(rows);
		}

		stamentFab.close();
		res2.close();
		
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	
}






