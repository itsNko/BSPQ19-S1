package es.deusto.client.gui;

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

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Socio;


public class VentanaDevolucion extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame ventanaQueMeLlama;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					VentanaDevolucion frame = new VentanaDevolucion(null, null);
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
	public VentanaDevolucion(JFrame ventanaAnterior, final Socio s) {
		ventanaQueMeLlama = ventanaAnterior;

		setTitle("Devolución de artículos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"articulosAlquilados.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960, 540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(100,100,450, 300);
		getContentPane().add(background);
		background.setLayout(null);

		JButton botonVolver = new JButton();
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				ventanaQueMeLlama.setVisible(true);
				VentanaDevolucion.this.dispose();
			}
		});
		botonVolver.setBounds(780, 455, 140, 50);
		botonVolver.setOpaque(false);
		botonVolver.setContentAreaFilled(false);
		botonVolver.setBorderPainted(false);
		background.add(botonVolver);

		//		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT) {
		//
		//			private static final long serialVersionUID = 1L;
		//
		//			public Dimension preferredLayoutSize(Container target) {
		//				Dimension sd=super.preferredLayoutSize(target);
		//
		//				sd.width=Math.min(6, sd.width);
		//
		//				return sd;
		//			}
		//		});

		//		JScrollPane scrollEnCurso = new JScrollPane(panel);
		//		panel.setOpaque(false);
		//		background.add(scrollEnCurso);
		//		scrollEnCurso.setBounds(83, 118, 787, 294);
		//		scrollEnCurso.setOpaque(false);
		//		scrollEnCurso.getViewport().setOpaque(false);

		//		for (int i = 0; i < alquileres.size(); i++) {
		//			if(alquileres.get(i).isEnCurso()) {
		//				JButton btnArticulo = new JButton();
		//				btnArticulo.setPreferredSize(new Dimension(87, 120));
		//				ImageIcon img1 = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+alquileres.get(i).getAlquilado().getCaratula());
		//				
		//				Image image = img1.getImage();
		//				image = getScaledImage(image, 87, 120);
		//				ImageIcon finalImage = new ImageIcon(image);
		//				
		//				btnArticulo.setIcon(finalImage);
		//				panel.add(btnArticulo);
		//
		//			}
		//			
		//		}

		int distancia = 128;
		for (int i = 0; i < s.getAlquileres().size(); i++) {
			if(s.getAlquileres().get(i).isEnCurso()) {
				JButton btnArticulo = new JButton();
				final Alquiler a = s.getAlquileres().get(i);
				btnArticulo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						VentanaConfirmacionDevolucion vcd = new VentanaConfirmacionDevolucion(VentanaDevolucion.this, ventanaQueMeLlama, a, s);
						vcd.setVisible(true);
						VentanaDevolucion.this.setVisible(false);
					}
				});
				btnArticulo.setBounds(distancia, 200, 87, 120);
				background.add(btnArticulo);
				ImageIcon img1 = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+s.getAlquileres().get(i).getAlquilado().getCaratula());

				Image image = img1.getImage();
				image = getScaledImage(image, 87, 120);
				ImageIcon finalImage = new ImageIcon(image);

				btnArticulo.setIcon(finalImage);
				distancia = distancia+120;
			}

		}
	}
}

