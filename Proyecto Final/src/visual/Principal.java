package visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/pictures/istockphoto-1130389312-612x612.jpg")));
		setResizable(false);
		setTitle("Hospital Juan Jos\u00E9 Almanzar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-60);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel portada = new JLabel("");
		portada.setIcon(new ImageIcon(Principal.class.getResource("/pictures/portada.gif")));
		portada.setBounds(349,56, 578, 453);
		contentPane.add(portada);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO AL ");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(563, 21, 555, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Iniciar sesi\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioSesion ini = new InicioSesion();
				ini.setVisible(true);
			}
		});
		btnNewButton.setBounds(560, 470, 175, 45);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.setBounds(560,540, 175, 45);
		contentPane.add(btnCancelar);
		
		JLabel lblHospitalJuanJose = new JLabel("HOSPITAL JUAN JOSE ALMANZAR");
		lblHospitalJuanJose.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblHospitalJuanJose.setBounds(488, 56, 600, 43);
		contentPane.add(lblHospitalJuanJose);
		setLocationRelativeTo(null);
	}
}
