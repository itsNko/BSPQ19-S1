package es.deusto.client.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import es.deusto.client.controllers.ControllerArticulos;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Clase de la ventana NuevoArticulo.
 */
public class NuevoArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private ControllerArticulos controllerArticulos;
	
	String nombre;
	String caratula;
	double precio;
	double puntuacion;
	String sinopsis;
	String fecha_estr;
	String genero;

	
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoArticulo window = new NuevoArticulo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	
	/**
	 * Constructor de la ventana NuevoArticulo.
	 * @param ventanaAnterior
	 */
	public NuevoArticulo(JFrame ventanaAnterior) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocation(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try {
			controllerArticulos = new ControllerArticulos();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Añadir nuevo artículo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"fondoAzul1.jpg");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0,0,960, 518);
		getContentPane().add(background);
		background.setLayout(null);
		
		JRadioButton rdbtnPelcula = new JRadioButton("Película");
		rdbtnPelcula.setBounds(95, 85, 141, 23);
		rdbtnPelcula.setForeground(Color.WHITE);
		background.add(rdbtnPelcula);
		
		JRadioButton rdbtnVideojuego = new JRadioButton("Videojuego");
		rdbtnVideojuego.setBounds(230, 85, 141, 23);
		rdbtnVideojuego.setForeground(Color.WHITE);
		background.add(rdbtnVideojuego);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(95, 156, 90, 16);
		background.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(220, 151, 130, 26);
		background.add(textField);
		textField.setColumns(10);
		
		JLabel lblCartula = new JLabel("Carátula:");
		lblCartula.setBounds(95, 198, 90, 16);
		lblCartula.setForeground(Color.WHITE);
		background.add(lblCartula);
		
		textField_1 = new JTextField();
		textField_1.setBounds(220, 193, 130, 26);
		background.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(95, 240, 90, 16);
		lblPrecio.setForeground(Color.WHITE);
		background.add(lblPrecio);
		
		textField_2 = new JTextField();
		textField_2.setBounds(220, 235, 130, 26);
		background.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPuntuacion = new JLabel("Puntuación:");
		lblPuntuacion.setBounds(95, 280, 90, 16);
		lblPuntuacion.setForeground(Color.WHITE);
		background.add(lblPuntuacion);
		
		textField_3 = new JTextField();
		textField_3.setBounds(220, 273, 130, 26);
		background.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setBounds(95, 348, 61, 16);
		lblSinopsis.setForeground(Color.WHITE);
		background.add(lblSinopsis);
	
		textField_4 = new JTextField();
		textField_4.setBounds(220, 343, 130, 26);
		background.add(textField_4);
		textField_4.setColumns(10);
	
		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setBounds(95, 400, 61, 16);
		lblGenero.setForeground(Color.WHITE);
		background.add(lblGenero);
	
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(220, 395, 140, 27);
		background.add(comboBox);
	
		comboBox.addItem("Terror");
		comboBox.addItem("Acción");
		comboBox.addItem("Aventuras");
		comboBox.addItem("El que sea");
	
		JLabel lblFechaEstreno = new JLabel("Fecha estreno:");
		lblFechaEstreno.setBounds(95, 445, 110, 16);
		lblFechaEstreno.setForeground(Color.WHITE);
		background.add(lblFechaEstreno);
	
		textField_5 = new JTextField();
		textField_5.setBounds(220, 440, 130, 26);
		background.add(textField_5);
		textField_5.setColumns(10);
		
		rdbtnPelcula.setSelected(true);
		

		
		rdbtnVideojuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			if (rdbtnVideojuego.isSelected()) {
			rdbtnPelcula.setSelected(false);
			rdbtnVideojuego.setSelected(true);
			lblSinopsis.setText("Descripción");
			lblGenero.setText("Categoría");
			lblFechaEstreno.setText("Fecha lanzamiento");
			
			}
			}
		});
		
		rdbtnPelcula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnPelcula.isSelected()) {
					rdbtnVideojuego.setSelected(false);
					lblGenero.setText("Género:");
					lblSinopsis.setText("Sinopsis");
					lblFechaEstreno.setText("Fecha de estreno:");
				}
				
			}
			
		});
		
		JButton btnAadir = new JButton("Añadir");
		background.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnPelcula.isSelected()) {
					nombre = textField.getText();
					caratula = textField_1.getText();
					precio = Double.parseDouble(textField_2.getText());
					puntuacion = Double.parseDouble(textField_3.getText());
					sinopsis = textField_4.getText();
					fecha_estr = textField_5.getText();
					genero = (String) comboBox.getSelectedItem();
					
					
					controllerArticulos.insertarPelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, 0);
					JOptionPane.showMessageDialog(null, "Película añadida correctamente",
							"OK", JOptionPane.OK_OPTION);
					
				} else if (rdbtnVideojuego.isSelected()){
					nombre = textField.getText();
					caratula = textField_1.getText();
					precio = Double.parseDouble(textField_2.getText());
					puntuacion = Double.parseDouble(textField_3.getText());
					sinopsis = textField_4.getText();
					fecha_estr = textField_5.getText();
					genero = (String) comboBox.getSelectedItem();
					
					
					controllerArticulos.insertarVideojuego(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, 0);
					JOptionPane.showMessageDialog(null, "Videojuego añadido correctamente",
							"OK", JOptionPane.OK_OPTION);
					}
				}
		});
		btnAadir.setBounds(442, 233, 117, 29);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(442, 294, 117, 29);
		background.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				ventanaAnterior.setVisible(true);
				NuevoArticulo.this.dispose();
			}
		});
		
		
		
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}
