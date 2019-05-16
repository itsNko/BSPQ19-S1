package es.deusto.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.client.controllers.ControllerRecargarSaldo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase de la ventana VentanaMetodoPago.
 */
public class VentanaMetodoPago extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JFrame ventanaQueMeLlama;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaMetodoPago frame = new VentanaMetodoPago();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Constructor de la ventana VentanaMetodoPago.
	 * @param ventanaAnterior
	 * @param menuSocio
	 * @param nombreSocio
	 * @param cantidad
	 * @param cantidadQueSeRecarga
	 * @param lblSaldo
	 * @param crs
	 */
	public VentanaMetodoPago(JFrame ventanaAnterior, JFrame menuSocio, String nombreSocio, double cantidad, double cantidadQueSeRecarga, JLabel lblSaldo, ControllerRecargarSaldo crs) {
		ventanaQueMeLlama = ventanaAnterior;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEligeElMetodo = new JLabel("ELIJA EL METODO DE PAGO QUE DESEE");
		lblEligeElMetodo.setBounds(119, 29, 230, 14);
		contentPane.add(lblEligeElMetodo);
		
		JButton btnNewButton = new JButton("EFECTIVO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int eleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro?");
				if(eleccion == 0) {
					boolean t = crs.updateMonedero(nombreSocio, cantidad);
					if(t) {
						lblSaldo.setText("Tu saldo actual es de "+crs.selectSocio(nombreSocio).getMonedero()+" €");
						VentanaMetodoPago.this.dispose();
						ventanaQueMeLlama.dispose();
						menuSocio.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Error al recargar saldo, por favor intenelo mas tarde.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(29, 86, 155, 78);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("PayPal");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMetodoPago.this.setVisible(false);
				VentanaInicioSesionPayPal visp = new VentanaInicioSesionPayPal(VentanaMetodoPago.this, menuSocio, ventanaQueMeLlama, nombreSocio, cantidad, cantidadQueSeRecarga, lblSaldo, crs);
				visp.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(237, 86, 155, 78);
		contentPane.add(btnNewButton_1);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaMetodoPago.this.dispose();
			}
		});
		btnVolver.setBounds(29, 227, 89, 23);
		contentPane.add(btnVolver);
	}
}
