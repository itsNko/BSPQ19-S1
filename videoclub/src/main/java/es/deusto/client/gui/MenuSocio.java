package es.deusto.client.gui;

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
import javax.swing.JOptionPane;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

import java.awt.Color;
import java.awt.Font;

public class MenuSocio extends JFrame {

	private static final long serialVersionUID = 1L;
	// private JFrame frame;
	private JFrame ventanaInicio;

	private ArrayList<Articulo> articulos = new ArrayList<Articulo>();


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
	public MenuSocio(JFrame ventanaAnterior, final Socio iniciado) {

		cargarArticulosPrueba();

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
		
		final JLabel lblSaldo = new JLabel("Tu saldo actual es de "+iniciado.getMonedero()+" €");
		lblSaldo.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBounds(125, 200, 300, 60);
		background.add(lblSaldo);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Alquilar");
				ListadoArticulos ls = new ListadoArticulos(MenuSocio.this, articulos, iniciado, lblSaldo);
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
				VentanaAlquileres va = new VentanaAlquileres(MenuSocio.this, iniciado.getAlquileres());
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
				VentanaRecargarSaldo saldo = new VentanaRecargarSaldo(MenuSocio.this, iniciado, lblSaldo);
				saldo.setVisible(true);
				MenuSocio.this.setVisible(false);

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
				if(!alquileresEnCurso(iniciado)) {
					JOptionPane.showMessageDialog(null, "No tienes alquileres en curso :(", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					VentanaDevolucion vd = new VentanaDevolucion(MenuSocio.this, iniciado);
					vd.setVisible(true);
					MenuSocio.this.setVisible(false);
				}

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
		Videojuego v1 = new Videojuego("Sonic", 5, "Descripcion de Sonic", "Plataformas","10/02/2004", 7, "sonic.JPG");
		Videojuego v2 = new Videojuego("Mario Bros", 4.75, "Descripcion de Mario", "Aventura","31/03/2008", 8.5, "mario.jpg");
		Videojuego v3 = new Videojuego("GTA V", 7, "Descripcion de GTA V", "Acción","20/03/2015", 9, "GTAV.jpg");

		Pelicula p1 = new Pelicula("Los vengadores", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg");
		Pelicula p2 = new Pelicula("Harry Potter",5, "Descripcion de Harry Potter", "Acción","29/01/2009", 9, "harryPotter.jpg");
		Pelicula p3 = new Pelicula("Star Wars I", 6.25, "Descripcion de Star Wars I", "Ciencia ficción","13/06/2010", 9, "starWars.jpg");

		articulos.add(v1);
		articulos.add(v2);
		articulos.add(v3);
		articulos.add(p1);
		articulos.add(p2);
		articulos.add(p3);

	}

	private boolean alquileresEnCurso(final Socio iniciado) {
		boolean result = false;

		for(int i = 0; i < iniciado.getAlquileres().size(); i++) {
			if(iniciado.getAlquileres().get(i).isEnCurso()) {
				result = true;
			} 
		}


		return result;

	}

}