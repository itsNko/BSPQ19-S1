package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Socio;
import es.deusto.data.Videojuego;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaConfirmacion extends JFrame {

	
	private JPanel contentPane;
	Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2);

	
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
		setBounds(100, 100, 504, 241);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"fondoAzul1.jpg");
		Image im = img.getImage();
		im = getScaledImage(im, 504, 241);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0,0,504, 241);
		getContentPane().add(background);
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblNewLabel = new JLabel("CONFIRMACIÓN DE ALQUILER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		background.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		background.add(panel, BorderLayout.CENTER);
		
		JLabel lblArtculo = new JLabel("Artículo: ");
		
		JLabel lblPrecio = new JLabel("Precio: ");
		
		JLabel lblFechaDevolucin = new JLabel("Fecha devolución: ");
		
		JLabel lblMonedero = new JLabel("Monedero: ");
		
		JLabel lblSaldo = new JLabel("Saldo restante: ");
		
		JLabel label = new JLabel("" + a1.getAlquilado().getNombre());
		
		
		JLabel label_1 = new JLabel("" + a1.getCoste());
		
		JLabel label_2 = new JLabel("" + a1.getFecha_fin());
		
		JLabel label_3 = new JLabel("" + tester.getMonedero());
		
		JLabel label_4 = new JLabel("" + (tester.getMonedero() - a1.getCoste() ) );
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblArtculo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(lblFechaDevolucin))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPrecio)
							.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSaldo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_4))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMonedero)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_3)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArtculo)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecio)
						.addComponent(label_1)
						.addComponent(lblMonedero)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDevolucin)
						.addComponent(label_2))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSaldo)
						.addComponent(label_4))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panelBotones = new JPanel();
		background.add(panelBotones, BorderLayout.SOUTH);
		
		
		JButton bConfirmar = new JButton("Confirmar");
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
		panelBotones.add(bConfirmar);
		
		
	}
	
}
