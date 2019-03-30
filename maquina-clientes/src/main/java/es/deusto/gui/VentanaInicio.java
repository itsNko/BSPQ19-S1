	package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		
		getContentPane().setLayout(new BorderLayout());
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"inicioSesion.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(100,100,450, 300);
		getContentPane().add(background);
		background.setLayout(null);
		
		JTextField textfield = new JTextField(10);
		textfield.setBounds(400,100,250,40);
		background.add(textfield);
		
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setBounds(400,200,250,40);
		background.add(passwordField);
		
		final JLabel candadoNegro;
		ImageIcon imagenCandadoN = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"candadoNegro.png");
		im = imagenCandadoN.getImage();
		im = getScaledImage(im,200,200);
		imagenCandadoN = new ImageIcon(im);
		
		final JLabel candadoVerde;
		ImageIcon imagenCandadoV = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"candadoVerde.png");
		im = imagenCandadoV.getImage();
		im = getScaledImage(im,200,200);
		imagenCandadoV = new ImageIcon(im);
		
		final JLabel candadoRojo;
		ImageIcon imagenCandadoR = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"candadoRojo.png");
		im = imagenCandadoR.getImage();
		im = getScaledImage(im,200,200);
		imagenCandadoR = new ImageIcon(im);
		
		candadoNegro = new JLabel("", imagenCandadoN, JLabel.CENTER);
		candadoNegro.setBounds(25,220,450, 300);
		
		candadoVerde = new JLabel("", imagenCandadoV, JLabel.CENTER);
		candadoVerde.setBounds(25,220,450, 300);
		
		candadoRojo = new JLabel("", imagenCandadoR, JLabel.CENTER);
		candadoRojo.setBounds(25,220,450, 300);
		
		
		background.add(candadoNegro);
		background.add(candadoVerde);
		background.add(candadoRojo);
		candadoVerde.setVisible(false);
		candadoRojo.setVisible(false);
		
		ImageIcon botonInicioSesion = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"botonLogin.png");
		im = botonInicioSesion.getImage();
		im = getScaledImage(im,208,62);
		botonInicioSesion = new ImageIcon(im);
		JButton boton = new JButton("Login");
		boton.setIcon(botonInicioSesion);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				candadoNegro.setVisible(false);
				candadoRojo.setVisible(false);
				candadoVerde.setVisible(true);
				background.revalidate();
				background.repaint();
				
				
			}
		});
		boton.setBounds(400,400,200,65);
		background.add(boton);
		
		
		ImageIcon botonRegistro = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"botonRegister.png");
		im = botonRegistro.getImage();
		im = getScaledImage(im,227,63);
		botonRegistro = new ImageIcon(im);
		
		JButton boton2 = new JButton("Register");
		boton2.setIcon(botonRegistro);
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				candadoNegro.setVisible(false);
				candadoRojo.setVisible(true);
				candadoVerde.setVisible(false);
				background.revalidate();
				background.repaint();
				
				
			}
		});
		boton2.setBounds(630,403,220,55);
		background.add(boton2);
		
		
		
	}

	
}
