package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.Medico;
import logico.Usuario;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PanelUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PanelUsuario dialog = new PanelUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PanelUsuario(Usuario user) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 743, 470);
		setLocationRelativeTo(null);
		if (user instanceof Administrador) {
			setTitle("Médico");
		}
		else if (user instanceof Medico) {
			setTitle("Administrador");
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelMenu = new JPanel();
			panelMenu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMenu.setBounds(573, 0, 154, 393);
			contentPanel.add(panelMenu);
			panelMenu.setLayout(null);
			
			JButton btnPacientes = new JButton("Pacientes");
			btnPacientes.setBounds(10, 11, 134, 25);
			panelMenu.add(btnPacientes);
			
			JButton btnEnfermedades = new JButton("Enfermedades");
			btnEnfermedades.setBounds(10, 47, 134, 25);
			panelMenu.add(btnEnfermedades);
			
			JButton btnVacunas = new JButton("Vacunas");
			btnVacunas.setBounds(10, 83, 134, 25);
			panelMenu.add(btnVacunas);
			
			JButton btnUsuarios = new JButton("Usuarios");
			btnUsuarios.setBounds(10, 119, 134, 25);
			if (user instanceof Medico) {
				btnUsuarios.setVisible(false);
			}
			panelMenu.add(btnUsuarios);
		}
		
		JLabel lblHolder = new JLabel("New label");
		String imgPath = "/pictures/admin.png";
		if (user instanceof Medico) {
			imgPath = "/pictures/medicoperfil.png";
		}
		lblHolder.setIcon(new ImageIcon(PanelUsuario.class.getResource(imgPath)));
		lblHolder.setBounds(10, 24, 190, 213);
		contentPanel.add(lblHolder);
		
		txtNombre = new JTextField();
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
		
		JPanel panelAgenda = new JPanel();
		panelAgenda.setBorder(new TitledBorder(null, "Agenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgenda.setBounds(220, 11, 324, 371);
		if (user instanceof Administrador) {
			panelAgenda.setVisible(false);
		}
		contentPanel.add(panelAgenda);
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
}
