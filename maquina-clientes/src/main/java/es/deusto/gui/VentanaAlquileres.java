package es.deusto.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Videojuego;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JScrollPane;

import java.awt.Graphics2D;

public class VentanaAlquileres extends JFrame {

	private static final long serialVersionUID = 1L;

	private ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

	/**
	 * Lanza la ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlquileres frame = new VentanaAlquileres();
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
	 * Crea la ventana.
	 */
	public VentanaAlquileres() {

		cargaArticulos();

		setTitle("Tu historial de artículos alquilados/devueltos");
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

		JPanel panel = new JPanel(new GridLayout(0, 1));
		JScrollPane scrollEnCurso = new JScrollPane(panel);
		panel.setOpaque(false);
		background.add(scrollEnCurso);
		scrollEnCurso.setBounds(64, 124, 323, 360);
		scrollEnCurso.setOpaque(false);
		scrollEnCurso.getViewport().setOpaque(false);

		JPanel panel2 = new JPanel(new GridLayout(0, 1));
		panel2.setOpaque(false);
		JScrollPane scrollDevueltos = new JScrollPane(panel2);
		background.add(scrollDevueltos);
		scrollDevueltos.setBounds(473, 124, 323, 360);
		scrollDevueltos.setOpaque(false);
		scrollDevueltos.getViewport().setOpaque(false);
		
		
		String titulo = "";
		String fecha = "";
		Font fuente = new Font("Times New Roman", Font.BOLD, 16);
		Font fuente2 = new Font("Times New Roman", Font.PLAIN, 14);
		
		for(int i = 0; i < alquileres.size(); i++) {
			if(alquileres.get(i).isEnCurso()) {
				titulo = alquileres.get(i).getAlquilado().getNombre();
				fecha = "[" + alquileres.get(i).getFecha_inicio() + " - " + alquileres.get(i).getFecha_fin() + "]";
				JPanel jp = new JPanel(new FlowLayout());
				jp.setOpaque(false);
				
				JLabel label = new JLabel(titulo, JLabel.CENTER);
				label.setFont(fuente);
				label.setForeground(Color.WHITE);
				
				JLabel label2 = new JLabel(": " + fecha, JLabel.CENTER);
				label2.setFont(fuente2);
				label2.setForeground(Color.WHITE);
				
				jp.add(label);
				jp.add(label2);
				panel.add(jp);
			} else {
				titulo = alquileres.get(i).getAlquilado().getNombre();
				fecha = "[" + alquileres.get(i).getFecha_inicio() + " - " + alquileres.get(i).getFecha_fin() + "]";
				JPanel jp = new JPanel(new FlowLayout());
				jp.setOpaque(false);
				
				JLabel label = new JLabel(titulo, JLabel.CENTER);
				label.setFont(fuente);
				label.setForeground(Color.WHITE);
				
				JLabel label2 = new JLabel(": " + fecha, JLabel.CENTER);
				label2.setFont(fuente2);
				label2.setForeground(Color.WHITE);
				
				label2.setOpaque(false);
				
				
				jp.add(label);
				jp.add(label2);
				panel2.add(jp);
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
