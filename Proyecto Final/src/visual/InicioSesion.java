package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class InicioSesion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtusucode;
	private JPasswordField pfusucontrase;

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
		
		txtusucode = new JTextField();
		txtusucode.setBounds(93, 36, 112, 20);
		Datosnecesarios.add(txtusucode);
		txtusucode.setColumns(10);
		
		pfusucontrase = new JPasswordField();
		pfusucontrase.setBounds(93, 61, 112, 20);
		Datosnecesarios.add(pfusucontrase);
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}