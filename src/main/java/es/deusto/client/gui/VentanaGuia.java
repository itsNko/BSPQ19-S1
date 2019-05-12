package es.deusto.client.gui;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaGuia extends JFrame {
	
	public VentanaGuia() {
		
		
		setTitle("Guia usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 960, 540);
		final JLabel background;
		
//		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"articulosAAplicarDescuento.png");
//		Image im = img.getImage();
//		im = getScaledImage(im, 960,540);
//		ImageIcon finalImg= new ImageIcon(im);
//		getContentPane().setLayout(null);
//		
		background = new JLabel("", null, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);
	}
}
