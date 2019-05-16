package es.deusto.client.gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.PeliculaDTO;
import es.deusto.server.dto.VideojuegoDTO;

/**
 * Clase de la ventana VentanaEstadisticas.
 */
public class VentanaEstadisticas extends JFrame{

	private static final long serialVersionUID = 1L;

	private JFrame frame;

	/**
	 * Constructor de la ventana VentanaEstadisticas.
	 * @param VentanaAnterior
	 */
	public VentanaEstadisticas(JFrame VentanaAnterior, List<AlquilerDTO> alquileres) {
		
		Font fuente = new Font("Times New Roman", Font.BOLD, 16);
		Font fuente2 = new Font("Times New Roman", Font.PLAIN, 14);
		frame = VentanaAnterior;

		setTitle("Estadísticas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"estadisticas.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);
		
		
		
		String texto = "";
		int contador = 0;
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		
		boolean repetido = false;
		for(int i = 0; i < alquileres.size(); i++) {
			String titulo = alquileres.get(i).getAlquilado().getNombre();
			contador = 0;
			if(hmap.get(titulo)== null) {
				for(int j = 0;j<alquileres.size();j++) {
					if (titulo == alquileres.get(j).getAlquilado().getNombre()) {
						contador++;
					}	
				}
				hmap.put(titulo, contador);
			}	
			
		}
		Set set = hmap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("El artículo "+ mentry.getKey() + " ha sido alquilado "+mentry.getValue()+" veces.\n");
	         texto = texto + "El artículo "+ mentry.getKey() + " ha sido alquilado "+mentry.getValue()+" veces.\n";
	      }
		

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				frame.setVisible(true);
				VentanaEstadisticas.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		background.add(btnVolver);

		JTextArea textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setBounds(100, 142, 760, 260);
		textArea.setEditable(false);
		//background.add(textArea);
		textArea.setText(texto);

		JScrollPane sp = new JScrollPane(textArea);
		background.add(sp);
		sp.setOpaque(false);
		sp.setBounds(100, 142, 760, 260);

		/*for (int i = 0; i < articulos.size(); i++) {
			final Articulo a1 = getArticuloDeDTO(articulos.get(i));
			textArea.append("El artículo " + a1.getNombre() + " tiene un stock de "+a1.getUnidades()+" unidades.\n\n");
		}*/

		int distancia = 128;


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