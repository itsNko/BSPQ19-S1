package es.deusto.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class VentanaLoginSencilla extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame ventanaQueMeLlama;

	/**
	 * NO ejecutar esta ventana directamente. Primero hay que ejecutar la ventana de registro y pulsar el botón "Ir a login" para llegar hasta esta.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLoginSencilla frame = new VentanaLoginSencilla(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana.
	 */
	public VentanaLoginSencilla(JFrame ventanaAnterior, final HashMap<String, String> registro) {
		ventanaQueMeLlama = ventanaAnterior;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Nombre de usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		contentPane.add(passwordField);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(30);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(30);
		contentPane.add(panel_1);

		final JLabel lblNewLabel_2 = new JLabel("");
		final JButton btnCerrarSesin = new JButton("CERRAR SESIÓN");

		final JButton btnIniciarSesin = new JButton("INICIAR SESIÓN");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = textField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				// Comprobaciónd de si hay algún campo vacio
				if(nombreUsuario.equals("") || pass.equals("") || nombreUsuario == null || pass == null) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos está vacio, por favor introduce un nombre de usuario y contraseña correctos.", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					// Comprobación de si existe un usuario registrado con el nombre introducido
					if(registro.containsKey(nombreUsuario)) {
						// Comprobación de si la contraseña coincide con la del nombre de usuario registrado
						if(registro.get(nombreUsuario).equals(pass)) {
							System.out.println("Has iniciado sesión correctamente, bienvenido!");
							lblNewLabel_2.setText("Bienvenido, " + nombreUsuario + "!");
							btnIniciarSesin.setVisible(false);
							btnCerrarSesin.setVisible(true);
							textField.setText(""); passwordField.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta, vuelve a intentarlo.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese nombre, por favor prueba con otro.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		panel_1.add(btnIniciarSesin);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setVgap(30);
		contentPane.add(panel_2);


		panel_2.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setVgap(30);
		contentPane.add(panel_3);

		btnCerrarSesin.setVisible(false);
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Gracias por la visita, vuelva pronto!");
				lblNewLabel_2.setText("");
				btnCerrarSesin.setVisible(false);
				btnIniciarSesin.setVisible(true);
			}
		});
		panel_3.add(btnCerrarSesin);

		// Botón para volver a la ventana de registro
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaLoginSencilla.this.dispose();
			}
		});
		panel.add(btnVolver);

	}

}
