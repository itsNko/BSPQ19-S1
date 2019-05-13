package es.deusto.client.gui;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaGuia extends JFrame {
	
	private JFrame MenuSocio;
	
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	public VentanaGuia(final JFrame VentanaAnterior) throws FileNotFoundException {
		MenuSocio = VentanaAnterior;
		
		setTitle("Guia usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setBounds(100, 100, 960, 540);
		final JLabel background;	
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"guia.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960,540);
		ImageIcon finalImg= new ImageIcon(im);
		getContentPane().setLayout(null);
		
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0, 0, 960, 518);
		getContentPane().add(background);
		background.setLayout(null);
		
		JTextArea areaTexto = new JTextArea(25,75);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setEditable(false);
		
		try {
			
			FileReader f = new FileReader("."+File.separator+"src"+File.separator+"resources"+File.separator+"Guia.txt");
			BufferedReader b = new BufferedReader(f);
			String texto = b.readLine();
			while(texto != null) {
		          areaTexto.append(texto+"\n");
		          texto = b.readLine();
		      }
		    b.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		panel.add(areaTexto);
		JScrollPane scroll = new JScrollPane(panel);
		panel.setOpaque(false);
		background.add(scroll);
		scroll.setBounds(50, 150, 850, 270);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
			
		
		
		
			
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
