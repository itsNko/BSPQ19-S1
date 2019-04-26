package es.deusto.client.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.client.controllers.ControllerListadoArticulos;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.SocioDTO;

public class VentanaDescuento extends JFrame {

	private JFrame MenuEmpleado;
	ControllerListadoArticulos controllerListadoArticulos;
	List<ArticuloDTO> articulos;
	
	public VentanaDescuento(final JFrame VentanaAnterior, final SocioDTO iniciado, final JLabel lblSaldo) {
		MenuEmpleado = VentanaAnterior;
		try {
			controllerListadoArticulos = new ControllerListadoArticulos();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		articulos = controllerListadoArticulos.listadoArticulos();
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
		
		JButton btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				MenuEmpleado.setVisible(true);
				articulos.clear();
				VentanaDescuento.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
		background.add(btnVolver);
		btnVolver.setOpaque(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
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
