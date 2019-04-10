package es.deusto.client.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import es.deusto.client.data.Alquiler;

import java.awt.Graphics2D;

public class VentanaAlquileres extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame ventanaQueMeLlama;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaAlquileres frame = new VentanaAlquileres(null, null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
	public VentanaAlquileres(JFrame ventanaAnterior, ArrayList<Alquiler> alquileres) {
		ventanaQueMeLlama = ventanaAnterior;

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
				
				JPanel jp = new JPanel(new GridLayout(0, 1));
				jp.setOpaque(false);
				JPanel caratula = new JPanel(new FlowLayout());
				caratula.setOpaque(false);
				JPanel info = new JPanel(new FlowLayout());
				info.setOpaque(false);

				JLabel label = new JLabel(titulo, JLabel.CENTER);
				label.setFont(fuente);
				label.setForeground(Color.WHITE);

				JLabel label2 = new JLabel(": " + fecha, JLabel.CENTER);
				label2.setFont(fuente2);
				label2.setForeground(Color.WHITE);

				JLabel caratulaLabel;
				ImageIcon img1 = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+alquileres.get(i).getAlquilado().getCaratula());

				Image image = img1.getImage();
				image = getScaledImage(image, 87, 120);
				ImageIcon finalImage = new ImageIcon(image);
				caratulaLabel = new JLabel(finalImage);
				
				caratula.add(caratulaLabel);
				info.add(label); info.add(label2);

				jp.add(caratula); jp.add(info);
				panel.add(jp);

			} else {
				titulo = alquileres.get(i).getAlquilado().getNombre();
				fecha = "[" + alquileres.get(i).getFecha_inicio() + " - " + alquileres.get(i).getFecha_fin() + "]";
				
				JPanel jp = new JPanel(new GridLayout(0, 1));
				jp.setOpaque(false);
				JPanel caratula = new JPanel(new FlowLayout());
				caratula.setOpaque(false);
				JPanel info = new JPanel(new FlowLayout());
				info.setOpaque(false);

				JLabel label = new JLabel(titulo, JLabel.CENTER);
				label.setFont(fuente);
				label.setForeground(Color.WHITE);

				JLabel label2 = new JLabel(": " + fecha, JLabel.CENTER);
				label2.setFont(fuente2);
				label2.setForeground(Color.WHITE);

				JLabel caratulaLabel;
				ImageIcon img1 = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+alquileres.get(i).getAlquilado().getCaratula());

				Image image = img1.getImage();
				image = getScaledImage(image, 87, 120);
				ImageIcon finalImage = new ImageIcon(image);
				caratulaLabel = new JLabel(finalImage);
				
				caratula.add(caratulaLabel);
				info.add(label); info.add(label2);

				jp.add(caratula); jp.add(info);
				panel2.add(jp);
			}
		}

		JButton botonVolver = new JButton();
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaAlquileres.this.dispose();
			}
		});
		botonVolver.setBounds(836, 453, 100, 50);
		botonVolver.setOpaque(false);
		botonVolver.setContentAreaFilled(false);
		botonVolver.setBorderPainted(false);
		background.add(botonVolver);

	}

}
