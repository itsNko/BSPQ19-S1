package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Videojuego;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Graphics2D;

public class VentanaAlquileres extends JFrame {

	private static final long serialVersionUID = 1L;

	private ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
	private JTable table;
	private JTable table_1;

	/**
	 * Lanza la ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlquileres frame = new VentanaAlquileres();
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

	/**
	 * Crea la ventana.
	 */
	public VentanaAlquileres() {

		cargaArticulos();

		setTitle("Tu historial de artículos alquilados/devueltos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		final JLabel background;
		ImageIcon img = new ImageIcon("."+File.separator+"src"+File.separator+"resources"+File.separator+"fondoAzul1.jpg");
		Image im = img.getImage();
		im = getScaledImage(im, 800, 400);
		ImageIcon finalImg= new ImageIcon(im);
		background = new JLabel("", finalImg, JLabel.CENTER);
		background.setBounds(100,100,450, 300);
		getContentPane().add(background);
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		background.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("HISTORIAL DE TUS ALQUILERES");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		background.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		background.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);

		String col[] = {"Artículo", "Precio", "Fecha inicio", "Fecha fin"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		DefaultTableModel tableModel2 = new DefaultTableModel(col, 0);
		for(int i = 0; i < alquileres.size(); i++) {
			if(alquileres.get(i).isEnCurso()) {
				Object[] data = {alquileres.get(i).getAlquilado().getNombre(),
						alquileres.get(i).getCoste(), alquileres.get(i).getFecha_inicio(),
						alquileres.get(i).getFecha_fin()};
				tableModel.addRow(data);
			} else {
				Object[] data = {alquileres.get(i).getAlquilado().getNombre(),
						alquileres.get(i).getCoste(), alquileres.get(i).getFecha_inicio(),
						alquileres.get(i).getFecha_fin()};
				tableModel2.addRow(data);
			}

		}

		table = new JTable(tableModel)
		{

			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(
					TableCellRenderer renderer, int row, int column)
			{
				Component returnComp = super.prepareRenderer(renderer, row, column);
				Color colorFuerte = new Color(137, 144, 255);
				Color colorSuave = new Color(168, 194, 255);
				if (!returnComp.getBackground().equals(getSelectionBackground())){
					Color bg = (row % 2 == 0 ? colorFuerte : colorSuave);
					returnComp .setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
		};
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		table_1 = new JTable(tableModel2)
		{

			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(
					TableCellRenderer renderer, int row, int column)
			{
				Component returnComp = super.prepareRenderer(renderer, row, column);
				Color colorSuave = new Color(168, 194, 255);
				Color colorFuerte = new Color(137, 144, 255);
				if (!returnComp.getBackground().equals(getSelectionBackground())){
					Color bg = (row % 2 == 0 ? colorSuave : colorFuerte);
					returnComp .setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
		};
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);

	}

	// Método que carga datos de prueba aleatoriamente en el ArrayList
	public void cargaArticulos() {
		Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2);
		Videojuego v1 = new Videojuego("GTA V", "Descripción GTA V", "Acción", "20/09/2014", 9);

		Alquiler a1 = new Alquiler(p1, 20.25 , "20/03/2019", "30/03/2019", true);
		Alquiler a2 = new Alquiler(v1, 50 , "15/02/2019", "03/04/2019", false);
		Alquiler a3 = new Alquiler(v1, 5.5 , "01/31/2018", "12/31/2018", true);
		Alquiler a4 = new Alquiler(p1, 25.15 , "29/03/2019", "11/04/2019", false);
		Alquiler a5 = new Alquiler(v1, 10.50 , "01/02/2019", "12/02/2019", false);

		Random random = new Random();
		int r;
		int j = 0;
		while(j < 30) {
			r = random.nextInt(5);
			if(r == 0) {
				alquileres.add(a1);
			} else if(r == 1) {
				alquileres.add(a2);
			} else if(r == 2) {
				alquileres.add(a3);
			} else if(r == 3) {
				alquileres.add(a4);
			} else {
				alquileres.add(a5);
			}
			j++;
		}

	}
}
