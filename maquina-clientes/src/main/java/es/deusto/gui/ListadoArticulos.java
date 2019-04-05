package es.deusto.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.data.Alquiler;
import es.deusto.data.Articulo;
import es.deusto.data.Socio;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoArticulos extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame MenuSocio;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					ListadoArticulos window = new ListadoArticulos(null, null, null, null);
	//					window.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public ListadoArticulos(final JFrame VentanaAnterior, final ArrayList<Articulo> articulos, final Socio iniciado, final JLabel lblSaldo) {
		MenuSocio = VentanaAnterior;
		setTitle("Artículos disponibles para alquilar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"articulos.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);

		JButton btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				MenuSocio.setVisible(true);
				ListadoArticulos.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		background.add(btnVolver);
		btnVolver.setOpaque(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);

		int distancia = 128;
		for (int i = 0; i < articulos.size(); i++) {

			JButton btnJuego = new JButton();
			final Articulo a1 = articulos.get(i);
			btnJuego.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Calendar fecha = new GregorianCalendar();
					fecha.add(Calendar.MONTH, 1);
					
					int año = fecha.get(Calendar.YEAR);
			        int mes = fecha.get(Calendar.MONTH);
			        int dia = fecha.get(Calendar.DAY_OF_MONTH);
			        
			        String fechaIni = "" + dia +"/" + mes + "/"+ año +"";
			        
			        fecha.add(Calendar.DAY_OF_YEAR, 10);
			        
			        int añoF = fecha.get(Calendar.YEAR);
			        int mesF = fecha.get(Calendar.MONTH);
			        int diaF = fecha.get(Calendar.DAY_OF_MONTH);
			        String fechaFin = "" + diaF +"/" + mesF + "/"+ añoF +"";
					Alquiler a = new Alquiler(a1, a1.getPrecio() , fechaIni ,  fechaFin, false);

					VentanaConfirmacion confirm = new VentanaConfirmacion(VentanaAnterior , ListadoArticulos.this, iniciado, a, lblSaldo);
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
