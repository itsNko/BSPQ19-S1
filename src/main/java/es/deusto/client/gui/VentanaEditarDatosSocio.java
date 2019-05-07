package es.deusto.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.client.controllers.ControllerAlquiler;
import es.deusto.client.controllers.ControllerEditarDatosSocio;
import es.deusto.server.dto.SocioDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;

public class VentanaEditarDatosSocio extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JFrame ventanaQueMeLlama;

	private ControllerEditarDatosSocio controllerEditarDatosSocio;
	private boolean modificacionCorrecta = false;
	private JPasswordField passwordField;

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					VentanaEditarDatosSocio frame = new VentanaEditarDatosSocio(null, null);
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public VentanaEditarDatosSocio(JFrame ventanaAnterior, String nombreIniciado, ControllerAlquiler controllerAlquileres) {
		ventanaQueMeLlama = ventanaAnterior;

		SocioDTO iniciado = controllerAlquileres.selectSocio(nombreIniciado);
		
		try {
			this.controllerEditarDatosSocio = new ControllerEditarDatosSocio();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(94, 131, 62, 20);
		contentPane.add(lblPassword);

		JLabel lblNewLabel = new JLabel("Nombre completo:");
		lblNewLabel.setBounds(51, 70, 108, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(166, 67, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(166, 100, 143, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(166, 131, 143, 20);
		contentPane.add(passwordField);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaEditarDatosSocio.this.dispose();
			}
		});
		btnVolver.setBounds(33, 227, 89, 23);
		contentPane.add(btnVolver);

		JButton btnNewButton = new JButton("GUARDAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreCompleto = textField.getText();
				if(nombreCompleto.equals("")) {
					nombreCompleto = " ";
				}
				String apellidos = textField_1.getText();
				if(apellidos.equals("")) {
					apellidos = " ";
				}
				String pass = String.valueOf(passwordField.getPassword());
				if(pass.equals("")) {
					pass = " ";
				}
				String direccion = textField_3.getText();
				if(direccion.equals("")) {
					direccion = " ";
				}

				boolean mayus = false;

				// Comprobación de si la contraseña tiene al menos una mayúscula
				for(int i = 0; i < pass.length(); i++) {
					if(Character.isUpperCase(pass.charAt(i))) {
						mayus = true;
						break;
					}
				}

				if(!pass.equals(" ")) {
					// Comprobacion de si la contraseña tiene entre 8 y 16 caracteres
					if(pass.length() >= 8 && pass.length() <= 16) {
						// Comprobación de si el nombre de usuario es el mismo que la contraseña
						if(!nombreIniciado.equals(pass)) {
							// Comprobación de si la contraseña tiene al menos una letra mayúscula
							if(mayus) {
								// Comprobación de si la contraseña introducida contiene tanto letras como números
								if(contieneLetrasYNumeros(pass)) {
									String datosNuevos = nombreCompleto + ";" + apellidos + ";" + pass + ";" + direccion;

									modificacionCorrecta = controllerEditarDatosSocio.updateDatosSocio(nombreIniciado, datosNuevos);
									if(modificacionCorrecta) {
										JOptionPane.showMessageDialog(null, "Tus datos se han actualizado correctamente.", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
										ventanaQueMeLlama.setVisible(true);
										VentanaEditarDatosSocio.this.dispose();
									} else {
										JOptionPane.showMessageDialog(null, "No es posible actualizar tus datos en estos momentos, prueba en otro momento.", "Error", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "La contraseña debe contener tanto letras como números.", "Aviso", JOptionPane.WARNING_MESSAGE);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "La contraseña no puede ser igual que el nombre.", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 8 y 16 caracteres.", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					String datosNuevos = nombreCompleto + ";" + apellidos + ";" + pass + ";" + direccion;
					modificacionCorrecta = controllerEditarDatosSocio.updateDatosSocio(iniciado.getNombre(), datosNuevos);

					if(modificacionCorrecta) {
						JOptionPane.showMessageDialog(null, "Tus datos se han actualizado correctamente.", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
						ventanaQueMeLlama.setVisible(true);
						VentanaEditarDatosSocio.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "No es posible actualizar tus datos en estos momentos, prueba en otro momento.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnNewButton.setBounds(418, 227, 94, 23);
		contentPane.add(btnNewButton);

		JLabel lblModificacinDeDatos = new JLabel("MODIFICACIÓN DE DATOS DE USUARIO");
		lblModificacinDeDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModificacinDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacinDeDatos.setBounds(118, 11, 333, 14);
		contentPane.add(lblModificacinDeDatos);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(94, 100, 62, 20);
		contentPane.add(lblApellidos);

		JLabel lblDireccin = new JLabel("Direccion:");
		lblDireccin.setBounds(94, 162, 70, 14);
		contentPane.add(lblDireccin);

		textField_3 = new JTextField();
		textField_3.setBounds(166, 159, 143, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblDatosAntiguos = new JLabel("DATOS ACTUALES:");
		lblDatosAntiguos.setBounds(308, 45, 143, 14);
		contentPane.add(lblDatosAntiguos);

		JLabel lblNewLabel_1 = new JLabel(iniciado.getNombreCompleto());
		lblNewLabel_1.setBounds(353, 70, 169, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(iniciado.getApellidos());
		lblNewLabel_2.setBounds(353, 103, 169, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(iniciado.getPassword());
		lblNewLabel_3.setBounds(353, 134, 169, 14);
		contentPane.add(lblNewLabel_3);

		JLabel label = new JLabel(iniciado.getDireccion());
		label.setBounds(353, 162, 169, 14);
		contentPane.add(label);
	}

	public boolean contieneLetrasYNumeros(String s) {
		String n = ".*[0-9].*";
		String l = ".*[A-Z].*";
		return s.matches(n) && s.matches(l);
	}
}
