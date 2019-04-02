package es.deusto.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.data.Alquiler;
import es.deusto.data.Articulo;
import es.deusto.data.Pelicula;
import es.deusto.data.Socio;
import es.deusto.data.Videojuego;

import java.awt.Color;
import java.awt.Font;

public class MenuSocio extends JFrame {

	private static final long serialVersionUID = 1L;
	// private JFrame frame;
	private JFrame ventanaInicio;
	
	private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	private ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

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
		
		cargarArticulosPrueba();
		cargarAlquileresPrueba();
		setTitle("Menú principal");
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
				ListadoArticulos ls = new ListadoArticulos(MenuSocio.this, articulos, alquileres);
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
				VentanaAlquileres va = new VentanaAlquileres(MenuSocio.this, alquileres);
				va.setVisible(true);
				MenuSocio.this.setVisible(false);
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
				VentanaDevolucion vd = new VentanaDevolucion(MenuSocio.this, alquileres);
				vd.setVisible(true);
				MenuSocio.this.setVisible(false);
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
	
	private void cargarArticulosPrueba() {
		Videojuego v1 = new Videojuego("Sonic", "Descripcion de Sonic", "Plataformas","10/02/2004", 7, true, "sonic.JPG");
		Videojuego v2 = new Videojuego("Mario Bros", "Descripcion de Mario", "Aventura","31/03/2008", 8.5, true, "mario.jpg");
		Videojuego v3 = new Videojuego("GTA V", "Descripcion de GTA V", "Acción","20/03/2015", 9, true, "GTAV.jpg");
		
		Pelicula p1 = new Pelicula("Los vengadores", "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, true, "vengadores.jpg");
		Pelicula p2 = new Pelicula("Harry Potter", "Descripcion de Harry Potter", "Acción","29/01/2009", 9, true, "harryPotter.jpg");
		Pelicula p3 = new Pelicula("Star Wars I", "Descripcion de Star Wars I", "Ciencia ficción","13/06/2010", 9, true, "starWars.jpg");
		
		articulos.add(v1);
		articulos.add(v2);
		articulos.add(v3);
		articulos.add(p1);
		articulos.add(p2);
		articulos.add(p3);
		
	}
	
	private void cargarAlquileresPrueba() {
		Pelicula p1 = new Pelicula("Los vengadores", "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, true, "vengadores.jpg");
		Videojuego v1 = new Videojuego("Mario Bros", "Descripcion de Mario", "Aventura","31/03/2008", 8.5, true, "mario.jpg");
		

		Alquiler a1 = new Alquiler(p1, 6.25, "20/03/2019", "30/03/2019", true);
		Alquiler a2 = new Alquiler(v1, 5, "15/02/2019", "03/04/2019", false);
		Alquiler a3 = new Alquiler(v1, 5.5, "01/31/2018", "12/31/2018", true);
		Alquiler a4 = new Alquiler(p1, 25.15, "29/03/2019", "11/04/2019", false);
		Alquiler a5 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", false);
		Alquiler a6 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a7 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a8 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", false);
		Alquiler a9 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a10 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a11 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a12 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a13 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		Alquiler a14 = new Alquiler(v1, 10.50, "01/02/2019", "12/02/2019", true);
		
		alquileres.add(a1);
		alquileres.add(a2);
		alquileres.add(a3);
		alquileres.add(a4);
		alquileres.add(a5);
		alquileres.add(a6);
		alquileres.add(a7);
		alquileres.add(a8);
		alquileres.add(a9);
		alquileres.add(a10);
		alquileres.add(a11);
		alquileres.add(a12);
		alquileres.add(a13);
		alquileres.add(a14);
		
		
	}
}
