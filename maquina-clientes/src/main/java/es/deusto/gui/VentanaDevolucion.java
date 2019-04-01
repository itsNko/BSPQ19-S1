package es.deusto.gui;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Videojuego;

public class VentanaDevolucion extends JFrame {

	private static final long serialVersionUID = 1L;

	private ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDevolucion frame = new VentanaDevolucion();
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
	public VentanaDevolucion() {

		cargaArticulos();

		setTitle("Devolución de artículos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"historialAlquileres.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960, 540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(100,100,450, 300);
		getContentPane().add(background);
		background.setLayout(null);

		final JPanel panel = new JPanel(new GridLayout(0, 1));
		JScrollPane scrollEnCurso = new JScrollPane(panel);
		panel.setOpaque(false);
		background.add(scrollEnCurso);
		scrollEnCurso.setBounds(64, 124, 323, 360);
		scrollEnCurso.setOpaque(false);
		scrollEnCurso.getViewport().setOpaque(false);

		final JPanel panel2 = new JPanel(new GridLayout(0, 1));
		panel2.setOpaque(false);
		JScrollPane scrollDevueltos = new JScrollPane(panel2);
		background.add(scrollDevueltos);
		scrollDevueltos.setBounds(473, 124, 323, 360);
		scrollDevueltos.setOpaque(false);
		scrollDevueltos.getViewport().setOpaque(false);


		JButton dinamico = null;
		for(int i = 0; i < alquileres.size(); i++) {
			if(alquileres.get(i).isEnCurso()) {
				dinamico = new JButton(alquileres.get(i).getAlquilado().getNombre() + " [" +
						alquileres.get(i).getCoste() + " €]");
				
				panel.add(dinamico);
				
				
			} 
		}

		JButton botonVolver = new JButton();
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");

			}
		});
		botonVolver.setBounds(836, 453, 100, 50);
		botonVolver.setOpaque(false);
		botonVolver.setContentAreaFilled(false);
		botonVolver.setBorderPainted(false);
		background.add(botonVolver);
		
		
	}

	// Método que carga datos de prueba aleatoriamente en el ArrayList
	public void cargaArticulos() {
		Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2);
		Videojuego v1 = new Videojuego("GTA V", "Descripción GTA V", "Acción", "20/09/2014", 9);

		Alquiler a1 = new Alquiler(p1, 20.25 , "20/03/2019", "30/03/2019", true);
		Alquiler a2 = new Alquiler(v1, 50 , "15/02/2019", "03/04/2019", false);
		Alquiler a3 = new Alquiler(v1, 5.5 , "01/31/2018", "12/31/2018", true);
		Alquiler a4 = new Alquiler(p1, 25.15 , "29/03/2019", "11/04/2019", false);
		Alquiler a5 = new Alquiler(v1, 10.50 , "01/02/2019", "12/02/2019", false);

		Random random = new Random();
		int r;
		int j = 0;
		while(j < 75) {
			r = random.nextInt(5);
			if(r == 0) {
				alquileres.add(a1);
			} else if(r == 1) {
				alquileres.add(a2);
			} else if(r == 2) {
				alquileres.add(a3);
			} else if(r == 3) {
				alquileres.add(a4);
			} else {
				alquileres.add(a5);
			}
			j++;
		}

	}

}
