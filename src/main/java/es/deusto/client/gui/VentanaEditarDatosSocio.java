package es.deusto.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaEditarDatosSocio extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarDatosSocio frame = new VentanaEditarDatosSocio();
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
	public VentanaEditarDatosSocio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(70, 128, 62, 20);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("Nombre completo:");
		lblNewLabel.setBounds(27, 67, 108, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(142, 64, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 97, 143, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(142, 128, 143, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(24, 227, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnNewButton = new JButton("GUARDAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(358, 227, 94, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblModificacinDeDatos = new JLabel("MODIFICACIÓN DE DATOS DE USUARIO");
		lblModificacinDeDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModificacinDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacinDeDatos.setBounds(70, 11, 333, 14);
		contentPane.add(lblModificacinDeDatos);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(70, 97, 62, 20);
		contentPane.add(lblApellidos);
		
		JLabel lblDireccin = new JLabel("Direccion:");
		lblDireccin.setBounds(70, 159, 55, 14);
		contentPane.add(lblDireccin);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 156, 143, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDatosAntiguos = new JLabel("Datos antiguos");
		lblDatosAntiguos.setBounds(313, 36, 143, 14);
		contentPane.add(lblDatosAntiguos);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(313, 67, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(313, 100, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(313, 131, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel label = new JLabel("");
		label.setBounds(313, 159, 46, 14);
		contentPane.add(label);
	}
}
