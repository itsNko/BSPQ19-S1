package es.deusto.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaRegistroSencilla extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passField;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton;
	// Hasta que se implemente todo lo relacionado con la BDD utilizaremos colecciones de objetos para ir asegurando las funcionalidades 
	private HashMap<String, String> registro = new HashMap<String, String>();
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton button;
	private JButton button_1;

	/**
	 * Lanza la ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroSencilla frame = new VentanaRegistroSencilla();
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
	public VentanaRegistroSencilla() {
		// Datos de prueba precargados en el hashmap
		registro.put("prueba", "1234");
		registro.put("admin", "admin");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Nombre de usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);

		passField = new JPasswordField();
		contentPane.add(passField);
		passField.setColumns(10);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(30);
		contentPane.add(panel);

		// Botón para imprimir por pantalla los valores que haya dentro del hashmap
		btnNewButton = new JButton("PRINT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(registro.keySet());
				System.out.println(registro.values());
			}
		});
		panel.add(btnNewButton);

		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2);

		button = new JButton("REGISTRARSE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = textField.getText();
				String pass = String.valueOf(passField.getPassword());
				boolean mayus = false;

				// Comprobación de si la contraseña tiene al menos una mayúscula
				for(int i = 0; i < pass.length(); i++) {
					if(Character.isUpperCase(pass.charAt(i))) {
						mayus = true;
						break;
					}
				}

				// Comprobación de si algún campo está vacio
				if(nombreUsuario.equals("") || pass.equals("") || nombreUsuario == null || pass == null) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos está vacio, por favor introduce un nombre de usuario y contraseña correctos.", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					// Comprobación de si los campos tienen el tamaño adecuado
					if((nombreUsuario.length() >= 4 && nombreUsuario.length() <= 20) && (pass.length() >= 8 && pass.length() <= 16)) {
						// Comprobación de si el nombre de usuario es el mismo que la contraseña
						if(!nombreUsuario.equals(pass)) {
							// Comprobación de si la contraseña tiene al menos una letra mayúscula
							if(mayus) {
								// Comprobación de si la contraseña introducida contiene tanto letras como números
								if(contieneLetrasYNumeros(pass)) {
									// Comprobación de si existe un socio con el nombre de usuario introducido en el JTextField
									if(!registro.containsKey(nombreUsuario)) {
										registro.put(nombreUsuario, pass);
										JOptionPane.showMessageDialog(null, "Te has registrado correctamente :)", "Registro", JOptionPane.INFORMATION_MESSAGE);
										textField.setText(""); passField.setText("");
									} else {
										JOptionPane.showMessageDialog(null, "El nombre de usuario introducido ya existe, por favor introduzca otro nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "La contraseña debe contener tanto letras como números.", "Aviso", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una letra mayúscula", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "El nombre y la contraseña no pueden ser iguales, por favor introduce otro nombre o contraseña.", "Aviso", JOptionPane.WARNING_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario tiene que tener entre 4 y 20 carácteres, y la contraseña entre 8 y 16.", "Aviso", JOptionPane.WARNING_MESSAGE);
					}

				}

			}
		});
		panel_2.add(button);

		panel_3 = new JPanel();
		panel_1.add(panel_3);

		button_1 = new JButton("IR AL LOGIN");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLoginSencilla vLogin = new VentanaLoginSencilla(VentanaRegistroSencilla.this, registro);
				vLogin.setVisible(true);
				VentanaRegistroSencilla.this.setVisible(false);
			}
		});
		panel_3.add(button_1);
	}

	public boolean contieneLetrasYNumeros(String s) {
		String n = ".*[0-9].*";
		String l = ".*[A-Z].*";
		return s.matches(n) && s.matches(l);
	}

}


