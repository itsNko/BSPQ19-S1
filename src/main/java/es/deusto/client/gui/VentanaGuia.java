package es.deusto.client.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaGuia extends JFrame {
	
	private JFrame MenuSocio;
	
	public VentanaGuia(final JFrame VentanaAnterior) {
		MenuSocio = VentanaAnterior;
		
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
		
		
		JTextArea areaTexto = new JTextArea(25,80);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(areaTexto);
		background.add(scroll);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				MenuSocio.setVisible(true);
				VentanaGuia.this.dispose();
			}
		});
		btnVolver.setBounds(780, 455, 140, 50);
//		btnVolver.setOpaque(false);
//		btnVolver.setContentAreaFilled(false);
//		btnVolver.setBorderPainted(false);
		background.add(btnVolver);
	}
}
