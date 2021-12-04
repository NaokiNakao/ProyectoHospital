package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Checkbox;
import javax.swing.JLabel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class ConsultasVisual extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPaciente;
	private JButton btnHistorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultasVisual dialog = new ConsultasVisual();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultasVisual() {
		setModal(true);
		setTitle("Consulta");
		setResizable(false);
		setBounds(100, 100, 815, 553);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			Panel panelSintomas = new Panel();
			panelSintomas.setBounds(28, 10, 386, 456);
			panel.add(panelSintomas);
			panelSintomas.setLayout(null);
			
			JButton btnTerminarSintomas = new JButton("Diagnosticar");
			btnTerminarSintomas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelSintomas.setVisible(false);
				}
			});
			btnTerminarSintomas.setBounds(129, 415, 112, 23);
			panelSintomas.add(btnTerminarSintomas);
			
			JLabel lblSintomas = new JLabel("Sintomas");
			lblSintomas.setBounds(148, 22, 90, 14);
			panelSintomas.add(lblSintomas);
			lblSintomas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			{
				JTextPane textPaneSintomas = new JTextPane();
				textPaneSintomas.setBounds(33, 47, 304, 343);
				panelSintomas.add(textPaneSintomas);
			}
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(553, 43, 224, 371);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			txtPaciente = new JTextField();
			txtPaciente.setEditable(false);
			txtPaciente.setBounds(10, 342, 204, 20);
			panel_2.add(txtPaciente);
			txtPaciente.setColumns(10);
			
			Panel panel_1 = new Panel();
			panel_1.setBounds(28, 10, 386, 456);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblDiagnostico = new JLabel("Diagnostico");
			lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDiagnostico.setBounds(148, 7, 90, 29);
			panel_1.add(lblDiagnostico);
			
			JTextPane textPane = new JTextPane();
			textPane.setBounds(33, 47, 304, 343);
			panel_1.add(textPane);
			
			JButton btnDiagnostico = new JButton("Diagnostico");
			btnDiagnostico.setBounds(211, 415, 112, 23);
			panel_1.add(btnDiagnostico);
			
			JButton btnVolver = new JButton("Sintomas");
			btnVolver.setBounds(61, 415, 89, 23);
			panel_1.add(btnVolver);
			
			btnHistorial = new JButton("Historial");
			btnHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HistorialPaciente p = new HistorialPaciente();
					p.setVisible(true);
				}
			});
			btnHistorial.setBounds(621, 425, 89, 23);
			panel.add(btnHistorial);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				panelSintomas.setVisible(true);
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
