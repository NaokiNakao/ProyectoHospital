package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import logico.Clinica;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class InicioSesion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigoUsuario;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InicioSesion dialog = new InicioSesion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InicioSesion() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setResizable(false);
		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 502, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(InicioSesion.class.getResource("/pictures/cuenta.jpg")));
		lblNewLabel_2.setBounds(310, 30, 166, 187);
		contentPanel.add(lblNewLabel_2);
		
		JPanel Datosnecesarios = new JPanel();
		Datosnecesarios.setBorder(new TitledBorder(null, "Datos a completar:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Datosnecesarios.setBounds(10, 30, 290, 220);
		contentPanel.add(Datosnecesarios);
		Datosnecesarios.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 39, 58, 14);
		Datosnecesarios.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 64, 82, 14);
		Datosnecesarios.add(lblContrasea);
		
		txtCodigoUsuario = new JTextField();
		txtCodigoUsuario.setBounds(93, 36, 112, 20);
		Datosnecesarios.add(txtCodigoUsuario);
		txtCodigoUsuario.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(93, 61, 112, 20);
		Datosnecesarios.add(pfPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Clinica.getInstance().validacionCredenciales(txtCodigoUsuario.getText(), pfPassword.getText())) {
					PanelUsuario frame = new PanelUsuario(Clinica.getLoginUser());
					dispose();
					frame.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Error en las credenciales.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEntrar.setBounds(10, 111, 89, 23);
		Datosnecesarios.add(btnEntrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
