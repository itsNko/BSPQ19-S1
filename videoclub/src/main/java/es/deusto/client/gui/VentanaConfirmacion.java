package es.deusto.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.client.controllers.ControllerAlquiler;
import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Socio;

import java.awt.Color;

public class VentanaConfirmacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaQueMeLlama;
	private ControllerAlquiler controllerAlquiler;
	
	//Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2, "vengadores.jpg");

	
	//private Alquiler a1 = new Alquiler(p1, 20.25 , "20/03/2019", "30/03/2019", false);
	
	//Socio de prueba
	//private Socio tester = new Socio("Pablo", "P123", 30);
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaConfirmacion frame = new VentanaConfirmacion(null, null,null);
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
	
	public VentanaConfirmacion(final JFrame MenuSocio,JFrame ventanaAnterior, final Socio s1, final Alquiler a, final JLabel labelSaldo) {
		ventanaQueMeLlama = ventanaAnterior;
		
		setResizable(false);
		setTitle("Confirmación de alquiler");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 962, 540);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"confirmarAlquiler.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960, 540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(100,100,450, 300);
		
		
		
		JLabel imagen;
		ImageIcon imge = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+a.getAlquilado().getCaratula());
		Image imgen = imge.getImage();
		imgen = getScaledImage(imgen, 200, 270);
		ImageIcon finalImgen = new ImageIcon(imgen);
		imagen = new JLabel("", finalImgen, JLabel.CENTER);
		imagen.setBounds(150, 125, 200, 270);
		background.add(imagen);
		
		
		
		JLabel lblArtculo = new JLabel("Artículo: ");
		lblArtculo.setBounds(450,70,150,150);
		lblArtculo.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblArtculo.setForeground(Color.WHITE);;
		background.add(lblArtculo);
		
		JLabel label = new JLabel("" + a.getAlquilado().getNombre());
		label.setBounds(540,70,500,150);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label.setForeground(Color.WHITE);
		background.add(label);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(450,120,150,150);
		lblPrecio.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblPrecio.setForeground(Color.WHITE);
		background.add(lblPrecio);
		
		JLabel label_1 = new JLabel("" + a.getCoste() + "€");
		label_1.setBounds(530,120,500,150);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label_1.setForeground(Color.WHITE);
		background.add(label_1);
		
		JLabel lblFechaDevolucin = new JLabel("Fecha devolución: ");
		lblFechaDevolucin.setBounds(450,170,300,150);
		lblFechaDevolucin.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblFechaDevolucin.setForeground(Color.WHITE);;
		background.add(lblFechaDevolucin);
		
		JLabel label_2 = new JLabel("" + a.getFecha_fin());
		label_2.setBounds(630,170,500,150);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label_2.setForeground(Color.WHITE);;
		background.add(label_2);
		
		JLabel lblMonedero = new JLabel("Monedero: ");
		lblMonedero.setBounds(450,220,300,150);
		lblMonedero.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblMonedero.setForeground(Color.WHITE);
		background.add(lblMonedero);
		
		JLabel label_3 = new JLabel("" + s1.getMonedero()+ "€");
		label_3.setBounds(560,220,500,150);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label_3.setForeground(Color.WHITE);;
		background.add(label_3);
		
		
		JLabel lblSaldo = new JLabel("Saldo restante: ");
		lblSaldo.setBounds(450,270,300,150);
		lblSaldo.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblSaldo.setForeground(Color.WHITE);
		background.add(lblSaldo);		
		
		
		JLabel label_4 = new JLabel("" + (s1.getMonedero() - a.getCoste() ) + "€" );
		label_4.setBounds(600,270,500,150);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label_4.setForeground(Color.WHITE);;
		background.add(label_4);
		
		
		JButton bConfirmar = new JButton("");
		bConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a.getCoste() > s1.getMonedero()) {
					JOptionPane.showMessageDialog(null, "No tiene suficiente dinero en el monedero, porfavor introduzca más para seguir con la compra",
							"Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
					MenuSocio.setVisible(true);
					VentanaConfirmacion.this.dispose();
				} else {
					int eleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro?");
					if(eleccion == 0) {
						s1.setMonedero(s1.getMonedero() - a.getCoste());
						ArrayList<Alquiler> alquileres = s1.getAlquileres();
						a.setEnCurso(true);
						alquileres.add(a);
						System.out.println("Alquiler añadido a la lista de alquileres");
						s1.setAlquileres(alquileres);
						JOptionPane.showMessageDialog(null, "Artículo alquilado correctamente!", "Alquiler exitoso", JOptionPane.INFORMATION_MESSAGE);
						MenuSocio.setVisible(true);
						labelSaldo.setText("Tu saldo actual es de "+s1.getMonedero()+" €");
						VentanaConfirmacion.this.dispose();
					} 
				}
				repaint();
				
			}
		});
		bConfirmar.setBounds(735,452,142,50);
		bConfirmar.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		bConfirmar.setForeground(Color.WHITE);
		bConfirmar.setOpaque(false);
		bConfirmar.setContentAreaFilled(false);
		bConfirmar.setBorderPainted(false);
		background.add(bConfirmar);
		
		JButton bCancelar = new JButton("");
		
		bCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaQueMeLlama.setVisible(true);
				VentanaConfirmacion.this.dispose();
			}
		});
		bCancelar.setBounds(80,452,142,50);
		bCancelar.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		bCancelar.setForeground(Color.WHITE);
		bCancelar.setOpaque(false);
		bCancelar.setContentAreaFilled(false);
		bCancelar.setBorderPainted(false);
		background.add(bCancelar);
		
		getContentPane().add(background);
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.setLayout(new BorderLayout(0, 0));
		
	}
	
}
