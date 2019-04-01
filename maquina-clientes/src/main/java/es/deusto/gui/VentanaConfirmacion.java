package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Socio;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaConfirmacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2, true);

	
	private Alquiler a1 = new Alquiler(p1, 20.25 , "20/03/2019", "30/03/2019", false);
	
	//Socio de prueba
	private Socio tester = new Socio("Pablo", "P123", 30);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfirmacion frame = new VentanaConfirmacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	public VentanaConfirmacion() {
		setResizable(false);
		setTitle("Confirmación");
		
		
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
		
		
		
		JLabel lblArtculo = new JLabel("Artículo: ");
		lblArtculo.setBounds(100,70,150,150);
		lblArtculo.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblArtculo.setForeground(Color.WHITE);;
		background.add(lblArtculo);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(100,150,150,150);
		lblPrecio.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblPrecio.setForeground(Color.WHITE);;
		background.add(lblPrecio);
		
		JLabel lblFechaDevolucin = new JLabel("Fecha devolución: ");
		lblFechaDevolucin.setBounds(100,230,300,150);
		lblFechaDevolucin.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblFechaDevolucin.setForeground(Color.WHITE);;
		background.add(lblFechaDevolucin);
		
		JLabel lblMonedero = new JLabel("Monedero: ");
		lblMonedero.setBounds(500,150,300,150);
		lblMonedero.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblMonedero.setForeground(Color.WHITE);;
		background.add(lblMonedero);
		
		JLabel lblSaldo = new JLabel("Saldo restante: ");
		lblSaldo.setBounds(500,230,300,150);
		lblSaldo.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		lblSaldo.setForeground(Color.WHITE);;
		background.add(lblSaldo);
		
		JLabel label = new JLabel("" + a1.getAlquilado().getNombre());
		label.setBounds(190,70,500,150);
		label.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		label.setForeground(Color.WHITE);;
		background.add(label);
		
		JLabel label_1 = new JLabel("" + a1.getCoste() + "€");
		label_1.setBounds(180,150,500,150);
		label_1.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		label_1.setForeground(Color.WHITE);;
		background.add(label_1);
		
		JLabel label_2 = new JLabel("" + a1.getFecha_fin());
		label_2.setBounds(280,230,500,150);
		label_2.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		label_2.setForeground(Color.WHITE);;
		background.add(label_2);
		
		JLabel label_3 = new JLabel("" + tester.getMonedero()+ "€");
		label_3.setBounds(615,150,500,150);
		label_3.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		label_3.setForeground(Color.WHITE);;
		background.add(label_3);
		
		JLabel label_4 = new JLabel("" + (tester.getMonedero() - a1.getCoste() ) + "€" );
		label_4.setBounds(650,230,500,150);
		label_4.setFont(new Font("AppleGothic", Font.PLAIN, 22));
		label_4.setForeground(Color.WHITE);;
		background.add(label_4);
		
		
		JButton bConfirmar = new JButton("");
		bConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Para mas acciones
				if(a1.getCoste() > tester.getMonedero()) {
					JOptionPane.showMessageDialog(null, "No tiene suficiente dinero en el monedero, porfavor introduzca más para seguir con la compra",
							"Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
					repaint();
				} else {
					JOptionPane.showConfirmDialog(null, "¿Esta seguro?");
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
				// Para mas acciones
				System.out.println("Página anterior.");				
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
