package es.deusto.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.deusto.client.controllers.ControllerPayPal;
import es.deusto.client.controllers.ControllerRecargarSaldo;

public class VentanaInicioSesionPayPal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame ventanaQueMeLlama;
	private ControllerPayPal controllerPaypal;

	private boolean cambiarVentana = false;

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					VentanaInicioSesionPayPal frame = new VentanaInicioSesionPayPal();
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
	public VentanaInicioSesionPayPal(JFrame ventanaAnterior, JFrame menuSocio, JFrame ventanaRecargarSaldo, String nombreSocio, double cantidad, double cantidadQueSeRecarga, JLabel lblSaldo, ControllerRecargarSaldo crs) {
		ventanaQueMeLlama = ventanaAnterior;

		try {
			controllerPaypal = new ControllerPayPal();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		this.setTitle("PayPal Login");
		this.setResizable(false);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblbienvenido = new JLabel("\u00A1Bienvenido a PayPal!");
		lblbienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenido.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		panel_1.add(lblbienvenido);

		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JLabel lblUsuario = new JLabel("Nombre:      ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblUsuario);

		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);

		JLabel lblContrasea = new JLabel("Password:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblContrasea);

		passwordField = new JPasswordField(10);
		panel_4.add(passwordField);

		JPanel panel_3 = new JPanel();
		this.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnInicioSesin = new JButton("CANCELAR");
		btnInicioSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaInicioSesionPayPal.this.dispose();
			}
		});
		panel_3.add(btnInicioSesin);

		JButton btnRegistro = new JButton("PAGAR");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty() && !(String.valueOf(passwordField.getPassword())).isEmpty()) {
					cambiarVentana = controllerPaypal.pagarPayPal(textField.getText(), String.valueOf(passwordField.getPassword()), cantidadQueSeRecarga);
					if(cambiarVentana) {
						JOptionPane.showMessageDialog(null, "Nombre de usuario o password correctos. Se ha realizado el pago correctamente.", "Datos de usuario y pago correctos", JOptionPane.INFORMATION_MESSAGE);
						boolean t = crs.updateMonedero(nombreSocio, cantidad);
						if(t) {
							lblSaldo.setText("Tu saldo actual es de "+crs.selectSocio(nombreSocio).getMonedero()+" €");
							VentanaInicioSesionPayPal.this.dispose();
							ventanaQueMeLlama.dispose();
							ventanaRecargarSaldo.dispose();
							menuSocio.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Error al recargar saldo, por favor intenelo mas tarde.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Nombre de usuario o password no válidos o cantidad de dinero insuficiente, por favor vuelve a intentarlo o introduzca mas dinero en su cuenta", "Datos de usuario y pago incorrectos", JOptionPane.INFORMATION_MESSAGE);
					}
					cambiarVentana = false;
					textField.setText(""); passwordField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Introduce un usuario y password validos", "Error: Campos vacios", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel_3.add(btnRegistro);
	}

}
