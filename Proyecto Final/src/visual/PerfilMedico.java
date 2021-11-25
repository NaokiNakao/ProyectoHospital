package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class PerfilMedico extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList jlistagenda;
	private JTextField txtdocnomb;
	private JTextField txtdocespeci;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PerfilMedico dialog = new PerfilMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PerfilMedico() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setTitle("Perfil del m\u00E9dico");
		setResizable(false);
		setBounds(100, 100, 628, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(PerfilMedico.class.getResource("/pictures/medicoperfil.png")));
			lblNewLabel.setBounds(10, 48, 144, 177);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel Agenda = new JPanel();
			Agenda.setBorder(new TitledBorder(null, "Agenda:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Agenda.setBounds(188, 48, 177, 237);
			contentPanel.add(Agenda);
			Agenda.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				Agenda.add(scrollPane, BorderLayout.CENTER);
				
				jlistagenda = new JList();
				scrollPane.setViewportView(jlistagenda);
			}
		}
		{
			JPanel Menu = new JPanel();
			Menu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Men\u00FA:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			Menu.setBounds(375, 48, 204, 237);
			contentPanel.add(Menu);
			Menu.setLayout(null);
			
			JButton btnNewButton = new JButton("Listado de  pacientes");
			btnNewButton.setBounds(10, 42, 184, 34);
			Menu.add(btnNewButton);
			
			JButton btnListadoDeEnfermedades = new JButton("Listado de enfermedades");
			btnListadoDeEnfermedades.setBounds(10, 107, 184, 34);
			Menu.add(btnListadoDeEnfermedades);
			
			JButton btnListadoDeVacunas = new JButton("Listado de vacunas");
			btnListadoDeVacunas.setBounds(10, 172, 184, 34);
			Menu.add(btnListadoDeVacunas);
		}
		
		txtdocnomb = new JTextField();
		txtdocnomb.setEditable(false);
		txtdocnomb.setBounds(20, 246, 134, 20);
		contentPanel.add(txtdocnomb);
		txtdocnomb.setColumns(10);
		
		txtdocespeci = new JTextField();
		txtdocespeci.setEditable(false);
		txtdocespeci.setColumns(10);
		txtdocespeci.setBounds(20, 277, 134, 20);
		contentPanel.add(txtdocespeci);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
