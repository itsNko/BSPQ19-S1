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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.client.controllers.ControllerConfirmacionDevolucion;
import es.deusto.server.dto.AlquilerDTO;

import java.awt.Color;

public class VentanaConfirmacionDevolucion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaQueMeLlama;
	private JFrame menuSocio;
	private ControllerConfirmacionDevolucion controllerConfirmacionDevolucion;


	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					VentanaConfirmacion frame = new VentanaConfirmacion(null, null, null, null);
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

	public VentanaConfirmacionDevolucion(JFrame ventanaAnterior, JFrame menSocio, final AlquilerDTO a, String nombreUsuario) {
		ventanaQueMeLlama = ventanaAnterior;
		menuSocio = menSocio;
		try {
			this.controllerConfirmacionDevolucion = new ControllerConfirmacionDevolucion();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setResizable(false);
		setTitle("Confirmación");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 962, 540);

		contentPane = new JPanel();
		setContentPane(contentPane);

		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"confirmarDevolucion.png");
		Image im = img.getImage();
		im = getScaledImage(im, 960, 540);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(0,0,956, 511);



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

		JLabel valoracionLabel = new JLabel("Valoración: ");
		valoracionLabel.setBounds(450,220,500,150);
		valoracionLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		valoracionLabel.setForeground(Color.WHITE);;
		background.add(valoracionLabel);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		contentPane.add(comboBox);
		comboBox.setBounds(568,287,49,20);

		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);

		JButton bConfirmar = new JButton("");
		bConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres devolver el artículo seleccionado?");
				if(result == 0) {
					controllerConfirmacionDevolucion.devolverAlquiler(nombreUsuario, a.getAlquilado().getNombre(), (int)comboBox.getSelectedItem());
				}

				JOptionPane.showMessageDialog(null, "Se ha devuelto el árticulo correctamente!", "Devolución exitosa", JOptionPane.INFORMATION_MESSAGE);
				menuSocio.setVisible(true);
				VentanaConfirmacionDevolucion.this.dispose();

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
				VentanaConfirmacionDevolucion.this.dispose();
			}
		});
		contentPane.setLayout(null);
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
