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
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.SocioDTO;

public class VentanaDescuento extends JFrame {

	private JFrame MenuEmpleado;
	private JPanel panel; 
	private JList<Articulo> listaArticulos;
	private DefaultListModel<Articulo> modelo;
	
	public VentanaDescuento(final JFrame VentanaAnterior, final SocioDTO iniciado) {
		MenuEmpleado = VentanaAnterior;
		
		setTitle("Lista de articulos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 960, 540); // Cambiar
		final JLabel background;
		
		//Descomentar cuando se empiece el diseño
//		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"articulos.png");
//		Image im = img.getImage();
//		im = getScaledImage(im, 960,540);
//		ImageIcon finalImg= new ImageIcon(im);
//		getContentPane().setLayout(null);
		
		background = new JLabel("", null, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);
		
		
		panel = new JPanel(); 
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 935, 450);
		
		
		
		Pelicula p1 = new Pelicula("1", 2 , "", "", "", 0, "");
		
		modelo = new DefaultListModel<>();
		
		
		
		listaArticulos = new JList<>();
		listaArticulos.setModel(modelo);
		
		JScrollPane barraDesplazamiento = new JScrollPane(listaArticulos); 
		barraDesplazamiento.setBounds(10,30,200,110);
		
		
		panel.add(listaArticulos);
		panel.add(barraDesplazamiento);
		background.add(panel);
		
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				MenuEmpleado.setVisible(true);
				VentanaDescuento.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		background.add(btnVolver);

	}
	
	//Quitar cuando se empiece con el diseño
//	private Image getScaledImage(Image srcImg, int w, int h){
//		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = resizedImg.createGraphics();
//
//		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		g2.drawImage(srcImg, 0, 0, w, h, null);
//		g2.dispose();
//
//		return resizedImg;
//	}
	
}
