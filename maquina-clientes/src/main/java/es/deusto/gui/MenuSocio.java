package es.deusto.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.data.Socio;

import java.awt.Color;
import java.awt.Font;

public class MenuSocio extends JFrame {

	private static final long serialVersionUID = 1L;
	// private JFrame frame;
	private JFrame ventanaInicio;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					MenuSocio frame = new MenuSocio();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 * @param iniciado 
	 */
	public MenuSocio(JFrame ventanaAnterior, Socio iniciado) {
		ventanaInicio = ventanaAnterior;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"menuprincipal.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0,0,960, 518);
		getContentPane().add(background);
		background.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Alquilar");
				ListadoArticulos ls = new ListadoArticulos(MenuSocio.this);
				ls.setVisible(true);
				MenuSocio.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(490, 159, 170, 50);
		background.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);


		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Consultar alquileres");
			}
		});
		background.add(btnConsultar);
		btnConsultar.setBounds(730, 159, 170, 50);
		btnConsultar.setOpaque(false);
		btnConsultar.setContentAreaFilled(false);
		btnConsultar.setBorderPainted(false);

		JButton btnRecargar = new JButton("");
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println("Recargar saldo");
			}
		});
		btnRecargar.setBounds(735, 240, 170, 50);
		background.add(btnRecargar);
		btnRecargar.setOpaque(false);
		btnRecargar.setContentAreaFilled(false);
		btnRecargar.setBorderPainted(false);

		JButton btnDevolver = new JButton("");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Devolver artículos");
			}
		});
		btnDevolver.setBounds(490, 240, 170, 50);
		background.add(btnDevolver);
		btnDevolver.setOpaque(false);
		btnDevolver.setContentAreaFilled(false);
		btnDevolver.setBorderPainted(false);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Salir");
				ventanaInicio.setVisible(true);
				MenuSocio.this.dispose();
			}
		});
		btnSalir.setBounds(590, 430, 180, 55);
		background.add(btnSalir);
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);

		final JLabel fotoPerfil;
		ImageIcon defaultProfile = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"default-profile.png");
		im = defaultProfile.getImage();
		im = getScaledImage(im,200,200);
		defaultProfile = new ImageIcon(im);

		fotoPerfil = new JLabel("", defaultProfile, JLabel.CENTER);
		fotoPerfil.setBounds(25,220,450, 300);
		background.add(fotoPerfil);

		JLabel lblHola = new JLabel("¡Hola "+iniciado.getNombre()+"!");
		lblHola.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblHola.setForeground(Color.WHITE);
		lblHola.setBounds(170, 159, 180, 60);
		background.add(lblHola);

		JLabel lblSaldo = new JLabel("Tu saldo actual es de "+iniciado.getMonedero()+" €");
		lblSaldo.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBounds(135, 200, 250, 60);
		background.add(lblSaldo);


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
