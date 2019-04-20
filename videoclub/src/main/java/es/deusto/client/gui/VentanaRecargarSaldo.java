package es.deusto.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.deusto.client.controllers.ControllerAlquiler;
import es.deusto.client.controllers.ControllerRecargarSaldo;
import es.deusto.server.dto.SocioDTO;

public class VentanaRecargarSaldo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static double dinero = 0;
	private JFrame ventanaRecargar;
	JLabel lblSaldo = new JLabel(dinero+" €");
	private ControllerRecargarSaldo controllerRecargarSaldo;

	/**
	 * Launch the application.
	 * @return 
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaRecargarSaldo frame = new VentanaRecargarSaldo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	private void update()
	{
		lblSaldo.setText(dinero+" €");
	}
	private Image getScaledImage(Image srcImg, int w, int h) {
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
	public VentanaRecargarSaldo(JFrame ventanaAnterior, String nombreUsuario,final JLabel labelSaldo) {
		ventanaRecargar = ventanaAnterior;
		try {
			controllerRecargarSaldo = new ControllerRecargarSaldo();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		dinero = controllerRecargarSaldo.selectSocio(nombreUsuario).getMonedero();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		new JPanel();
		getContentPane().setLayout(new BorderLayout());
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"ventanaRecargarSaldo.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		//background.setBounds(100,100,450, 300);
		getContentPane().add(background);
		background.setLayout(null);
		
		lblSaldo.setFont(new Font("AppleGothic", Font.PLAIN, 30));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBounds(375, 138, 250, 60);
		update();
		background.add(lblSaldo);
		
		
		JButton botonVolver = new JButton();
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				ventanaRecargar.setVisible(true);
				VentanaRecargarSaldo.this.dispose();
				
			}
		});
		botonVolver.setBounds(80,460,145,50);
		background.add(botonVolver);
		botonVolver.setOpaque(false);
		botonVolver.setContentAreaFilled(false);
		botonVolver.setBorderPainted(false);
		
		JButton boton5euros = new JButton();
		boton5euros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("5");
				dinero = dinero + 5;
				update();
				
			}
		});
		boton5euros.setBounds(130,288,127,62);
		background.add(boton5euros);
		boton5euros.setOpaque(false);
		boton5euros.setContentAreaFilled(false);
		boton5euros.setBorderPainted(false);
		
		JButton boton10euros = new JButton();
		boton10euros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("10");
				dinero = dinero +10;
				update();
			}
		});
		boton10euros.setBounds(320,288,127,62);
		background.add(boton10euros);
		boton10euros.setOpaque(false);
		boton10euros.setContentAreaFilled(false);
		boton10euros.setBorderPainted(false);
		
		JButton boton20euros = new JButton();
		boton20euros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("20");
				dinero = dinero + 20;
				update();
			}
		});
		boton20euros.setBounds(510,288,127,62);
		background.add(boton20euros);
		boton20euros.setOpaque(false);
		boton20euros.setContentAreaFilled(false);
		boton20euros.setBorderPainted(false);
		
		JButton boton50euros = new JButton();
		boton50euros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("50");
				dinero = dinero + 50;
				update();
			}
		});
		boton50euros.setBounds(700,288,127,62);
		background.add(boton50euros);
		boton50euros.setOpaque(false);
		boton50euros.setContentAreaFilled(false);
		boton50euros.setBorderPainted(false);
		
		
		JButton botonConfirmar = new JButton();
		botonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Confirmar");
				
				
				iniciado.setMonedero(dinero);
				
				
				
				labelSaldo.setText("Tu saldo actual es de "+controllerRecargarSaldo.selectSocio(nombreUsuario).getMonedero()+" €");
				VentanaRecargarSaldo.this.dispose();
				ventanaRecargar.setVisible(true);
			}
		});
		botonConfirmar.setBounds(735,460,145,50);
		background.add(botonConfirmar);
		botonConfirmar.setOpaque(false);
		botonConfirmar.setContentAreaFilled(false);
		botonConfirmar.setBorderPainted(false);
		
	}

}
