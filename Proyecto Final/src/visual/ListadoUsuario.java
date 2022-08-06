package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Administrador;
import logico.Clinica;
import logico.ConexionSQL;
import logico.Enfermedad;
import logico.Medico;
import logico.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListadoUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Usuario selectedUser;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private JComboBox cbxTipo;

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
	 * @throws SQLException 
	 */
	public ListadoUsuario() throws SQLException {
		setModal(true);
		setResizable(false);
		setTitle("Listado de usuarios");
		setBounds(100, 100, 590, 534);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		
		
		{
			JPanel buttonpane = new JPanel();
			buttonpane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonpane.setBounds(0, 454, 584, 51);
			contentPanel.add(buttonpane);
			buttonpane.setLayout(null);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						RegistroUsuario r = new RegistroUsuario(selectedUser);
						r.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setBounds(276, 11, 89, 23);
				buttonpane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarUsuario();
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setBounds(375, 11, 89, 23);
				buttonpane.add(btnEliminar);
			}
			{
				JButton btnSaliar = new JButton("Salir");
				btnSaliar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSaliar.setBounds(474, 11, 89, 23);
				buttonpane.add(btnSaliar);
			}
			
			JButton btnNuevo = new JButton("Nuevo");
			btnNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					
					Usuario r = null;
					RegistroUsuario registro = new RegistroUsuario(r);
					registro.setVisible(true);
				}
			});
			btnNuevo.setBounds(177, 11, 89, 23);
			buttonpane.add(btnNuevo);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 557, 72);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 25, 46, 14);
		panel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(39, 22, 126, 23);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario user = Clinica.getInstance().buscarUsuarioById(txtId.getText().toString());
					
					if(user.getId().contains("A")) {
						cargarUsuarios(3,user);
					}else if(user.getId().contains("M")) {
						cargarUsuarios(4,user);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(175, 21, 89, 23);
		panel.add(btnBuscar);
		
		cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = cbxTipo.getSelectedIndex();
				try {
					cargarUsuarios(selection,null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Administradores", "M\u00E9dicos"}));
		cbxTipo.setBounds(387, 22, 160, 23);
		panel.add(cbxTipo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 105, 557, 325);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = table.getSelectedRow();
				if (fila != -1) {
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					
					String idUsuario = (String) table.getValueAt(fila,0);
					try {
						selectedUser = Clinica.getInstance().buscarUsuarioById(idUsuario);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] headers = {"Id", "Nombre", "Apellido", "Teléfono"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		cargarUsuarios(0,null);
	}
	
	public static void cargarUsuarios(int select,Usuario user) throws SQLException {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		String query = null;
		PreparedStatement stament = null;
		ResultSet resul = null;
		
		switch (select) 
		{
			case 0:
				
					query = "select medico.cod_medico as cod,medico.nombre as nombre,medico.apellido as apellido ,medico.telefono as telefono "
								+ "from  medico "
								+ "union  "
								+ "select administrador.cod_admin, administrador.nombre,administrador.apellido,administrador.telefono "
								+ "from administrador";
				
				 stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
				 resul = stament.executeQuery();
				
				
				while(resul.next()) {
					rows[0] = resul.getString("cod");
					rows[1] = resul.getString("nombre");
					rows[2] = resul.getString("apellido");
					rows[3] = resul.getString("telefono");
					model.addRow(rows);
				}
				break;
			
			case 1:
				query = "select administrador.cod_admin,administrador.nombre,administrador.apellido,administrador.telefono from administrador";
				 stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
				 resul = stament.executeQuery();
				
				while(resul.next()) {
					if (resul.getString("cod_admin").contains("A")) {
						rows[0] = resul.getString("cod_admin");
						rows[1] = resul.getString("nombre");
						rows[2] = resul.getString("apellido");
						rows[3] = resul.getString("telefono");
						model.addRow(rows);
					}
				}
				
				stament.close();
				resul.close();
				break;
				
			case 2:
				
				 query = "select medico.cod_medico,medico.nombre,medico.apellido,medico.telefono from medico";
				 stament = ConexionSQL.getInstance().getConexion().prepareStatement(query);
				 resul = stament.executeQuery();
				
				while(resul.next()) {
					if (resul.getString("cod_medico").contains("M")) {
						rows[0] = resul.getString("cod_medico");
						rows[1] = resul.getString("nombre");
						rows[2] = resul.getString("apellido");
						rows[3] = resul.getString("telefono");
						model.addRow(rows);
					}
				}
				
				stament.close();
				resul.close();
				break;	
				
			case 3 :
				
				rows[0] = user.getId();
				rows[1] = user.getNombre();
				rows[2] = user.getApellido();
				rows[3] = user.getTelefono();
				model.addRow(rows);
			break;
				
				
			case 4 :
				
				rows[0] = user.getId();
				rows[1] = user.getNombre();
				rows[2] = user.getApellido();
				rows[3] = user.getTelefono();
				model.addRow(rows);
			
			break;
					
		}
		
		
		
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	private void eliminarUsuario()
	{
		int fila = table.getSelectedRow();
		if(fila >= 0)
		{
			model.removeRow(fila);
			Clinica.getInstance().getMisUsuarios().remove(fila);
			
		}
	}
	
     
}







