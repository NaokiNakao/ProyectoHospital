package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.CitaMedica;
import logico.Clinica;
import logico.Medico;
import logico.Usuario;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTipo;
	private JLabel lblHolder;
	private JPanel panelAgenda;
	private JButton btnPacientes;
	private JButton btnEnfermedades;
	private JButton btnVacunas;
	private JButton btnUsuarios;
	private JTable tableAgenda;
	private static DefaultTableModel modelAgenda;
	private static Object[] rowsAgenda;
	private CitaMedica siguienteCita;
	private JButton btnConsulta;
	private JButton btnCitas;
	private JButton btnRegistroEnfermedades;

	/**
	 * Create the dialog.
	 */
	public PanelUsuario(Usuario user) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 743, 470);
		setLocationRelativeTo(null);
		if (user instanceof Administrador) {
			setTitle("Administrador");
		}
		else if (user instanceof Medico) {
			setTitle("Medico");
			loadAgenda((Medico) user);
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelMenu = new JPanel();
			panelMenu.setForeground(new Color(0, 0, 0));
			panelMenu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMenu.setBounds(573, 0, 154, 393);
			contentPanel.add(panelMenu);
			panelMenu.setLayout(null);
			
			btnPacientes = new JButton("Pacientes");
			btnPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ListadoPaciente listado = new ListadoPaciente();
					listado.setVisible(true);
				}
			});
			btnPacientes.setBounds(10, 11, 134, 25);
			panelMenu.add(btnPacientes);
			
			btnEnfermedades = new JButton("Enfermedades");
			btnEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EstadisticaEnfermedad frame = new EstadisticaEnfermedad();
					frame.setVisible(true);
				}
			});
			btnEnfermedades.setBounds(10, 47, 134, 25);
			panelMenu.add(btnEnfermedades);
			
			btnVacunas = new JButton("Vacunas");
			btnVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListadoVacuna listado = new ListadoVacuna();
					listado.setVisible(true);
				}
			});
			btnVacunas.setBounds(10, 119, 134, 25);
			panelMenu.add(btnVacunas);
			
			btnUsuarios = new JButton("Usuarios");
			btnUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ListadoUsuario listado = new ListadoUsuario();
					listado.setVisible(true);
				}
			});
			btnUsuarios.setBounds(10, 189, 134, 25);
			if (user instanceof Medico) {
				btnUsuarios.setVisible(false);
			}
			panelMenu.add(btnUsuarios);
			
			btnConsulta = new JButton("Consulta");
			btnConsulta.setBounds(10, 83, 134, 25);
			if (user instanceof Administrador) {
				btnConsulta.setVisible(false);
			}
			panelMenu.add(btnConsulta);
			
			btnCitas = new JButton("Nueva cita");
			btnCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					RegistroCita r = new RegistroCita();
					r.setVisible(true);
				}
			});
			btnCitas.setBounds(10, 155, 134, 25);
			panelMenu.add(btnCitas);
			
			btnRegistroEnfermedades = new JButton("Nueva Enfermedad");
			btnRegistroEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					RegistroEnfermedad r = new RegistroEnfermedad();
					r.setVisible(true);
				}
			});
			btnRegistroEnfermedades.setBounds(10, 233, 134, 25);
			panelMenu.add(btnRegistroEnfermedades);
			btnConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Medico med = (Medico) user;	
					
					if(med.getMisCitas().size() == 0) {			
						JOptionPane.showMessageDialog(null, "Hasta ahora no tiene ninguna cita pendiente", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						dispose();
						siguienteCita = med.getMisCitas().get(0);
						ConsultasVisual rc =new ConsultasVisual(siguienteCita,(Medico) user);
						rc.setVisible(true);
					}
				}
			});
		}
		
		lblHolder = new JLabel("");
		String imgPath = null;
		if (user instanceof Administrador) {
			imgPath = "/pictures/admin.png";
		}
		else if (user instanceof Medico) {
			imgPath = "/pictures/medicoperfil.png";
		}
		lblHolder.setIcon(new ImageIcon(PanelUsuario.class.getResource(imgPath)));
		lblHolder.setBounds(10, 24, 190, 213);
		contentPanel.add(lblHolder);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 249, 190, 23);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		if (user instanceof Administrador) {
			txtNombre.setText(user.getNombre() + " " + user.getApellido());
		}
		else if (user instanceof Medico) {
			txtNombre.setText("Dr. " + user.getNombre() + " " + user.getApellido());
		}
		
		txtTipo = new JTextField();
		txtTipo.setBorder(null);
		txtTipo.setEditable(false);
		txtTipo.setBounds(10, 283, 190, 23);
		contentPanel.add(txtTipo);
		txtTipo.setColumns(10);
		if (user instanceof Administrador) {
			txtTipo.setText(((Administrador) user).getPuestoLaboral());
		}
		else if (user instanceof Medico) {
			txtTipo.setText(((Medico) user).getEspecialidad());
		}
		
		panelAgenda = new JPanel();
		panelAgenda.setBorder(new TitledBorder(null, "Agenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgenda.setBounds(220, 11, 324, 352);
		if (user instanceof Administrador) {
			panelAgenda.setVisible(false);
		}
		contentPanel.add(panelAgenda);
		panelAgenda.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelAgenda.add(scrollPane, BorderLayout.CENTER);
		
		tableAgenda = new JTable();
		String[] heardersAgenda = {"Codigo","Paciente","Fecha"};
		modelAgenda = new DefaultTableModel();
		tableAgenda.setModel(modelAgenda);
		modelAgenda.setColumnIdentifiers(heardersAgenda);
		tableAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableAgenda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void loadAgenda(Medico medico) {
		modelAgenda.setRowCount(0);
		rowsAgenda = new Object[modelAgenda.getColumnCount()];
				
		for (int i = 0; i < Clinica.getInstance().getMisEnfermedades().size(); i++) {
			rowsAgenda[0]=  medico.getMisCitas().get(i).getCodigo();
			rowsAgenda[1]=  medico.getMisCitas().get(i).getNombrePersona();
			rowsAgenda[2]=  medico.getMisCitas().get(i).getFechaCita();
			modelAgenda.addRow(rowsAgenda);
		}
	}
}
