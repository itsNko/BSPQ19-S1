package es.deusto.client.gui;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.deusto.client.controllers.ControllerListadoArticulos;
import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.PeliculaDTO;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.dto.VideojuegoDTO;

public class VentanaDescuento extends JFrame {

	private JFrame MenuEmpleado;
	private JPanel panel; 
	private JList<Articulo> listaArticulos;
	private DefaultListModel<Articulo> modelo;
	
	public VentanaDescuento(final JFrame VentanaAnterior , List<ArticuloDTO> articulos) {
		MenuEmpleado = VentanaAnterior;
		
		setTitle("Lista de articulos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 960, 540);
		final JLabel background;

		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"articulosAAplicarDescuento.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);
		
		
		int distancia = 128;
		
		for (int i = 0; i < articulos.size(); i++) {
			JButton btnJuego = new JButton();
			final Articulo a1 = getArticuloDeDTO(articulos.get(i));
			btnJuego.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					VentanaConfigurarDescuento confirm = new VentanaConfigurarDescuento(VentanaAnterior, VentanaDescuento.this, a1);
					confirm.setVisible(true);
					setVisible(false);
				}
			});
			btnJuego.setBounds(distancia, 200, 87, 120);
			background.add(btnJuego);
			ImageIcon img1 = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+articulos.get(i).getCaratula());

			Image image = img1.getImage();
			image = getScaledImage(image, 87, 120);
			ImageIcon finalImage = new ImageIcon(image);

			btnJuego.setIcon(finalImage);
			distancia = distancia+120;


		}
		

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				MenuEmpleado.setVisible(true);
				VentanaDescuento.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		btnVolver.setOpaque(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		background.add(btnVolver);

	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	public Articulo getArticuloDeDTO(ArticuloDTO artDTO) {
		if(artDTO.getClassName().equals("VideojuegoDTO")) {
			VideojuegoDTO juego = (VideojuegoDTO) artDTO;
			return new Videojuego(juego.getNombre(), juego.getPrecio(), juego.getDescripcion(),
					juego.getCategoria(), juego.getFecha_lan(), juego.getPuntuacion(), juego.getCaratula(), juego.getDescuento());
		} else {
			PeliculaDTO peli = (PeliculaDTO) artDTO;
			return new Pelicula(peli.getNombre(), peli.getPrecio(), peli.getSinopsis(), peli.getGenero(), 
					peli.getFecha_estr(), peli.getPuntuacion(), peli.getCaratula(), peli.getDescuento());
		}
	}	
}
