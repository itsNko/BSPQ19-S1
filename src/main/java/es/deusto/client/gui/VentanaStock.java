package es.deusto.client.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.PeliculaDTO;
import es.deusto.server.dto.VideojuegoDTO;
import javax.swing.JTextArea;

/**
 * Clase de la ventana VentanaStock.
 */
public class VentanaStock extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;

	/**
	 * Constructor de la ventana VentanaStock.
	 * @param VentanaAnterior
	 * @param articulos
	 */
	public VentanaStock(final JFrame VentanaAnterior, List<ArticuloDTO> articulos) {
		
		frame = VentanaAnterior;
		
		setTitle("Comprobación de stock");
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
				frame.setVisible(true);
				articulos.clear();
				VentanaStock.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		background.add(btnVolver);
		btnVolver.setOpaque(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setBounds(100, 142, 760, 260);
		//background.add(textArea);
		
		JScrollPane sp = new JScrollPane(textArea);
		background.add(sp);
		sp.setOpaque(false);
		sp.setBounds(100, 142, 760, 260);
		
		for (int i = 0; i < articulos.size(); i++) {
			final Articulo a1 = getArticuloDeDTO(articulos.get(i));
			textArea.append("El artículo " + a1.getNombre() + " tiene un stock de "+a1.getUnidades()+" unidades.\n\n");
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
	
	/**
	 * Devuelve un nuevo Articulo creado con los atributos del ArticuloDTO introducido por parametro.
	 * @param artDTO
	 * @return Articulo
	 */
	public Articulo getArticuloDeDTO(ArticuloDTO artDTO) {
		if(artDTO.getClassName().equals("VideojuegoDTO")) {
			VideojuegoDTO juego = (VideojuegoDTO) artDTO;
			return new Videojuego(juego.getNombre(), juego.getPrecio(), juego.getDescripcion(),
					juego.getCategoria(), juego.getFecha_lan(), juego.getPuntuacion(), juego.getCaratula(), juego.getDescuento());
		} else {
			PeliculaDTO peli = (PeliculaDTO) artDTO;
			return new Pelicula(peli.getNombre(), peli.getPrecio(), peli.getSinopsis(), peli.getGenero(), 
					peli.getFecha_estr(), peli.getPuntuacion(), peli.getCaratula(),peli.getDescuento());
		}
	}
}