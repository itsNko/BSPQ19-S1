package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.data.Pelicula;
import es.deusto.data.Videojuego;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JScrollPane;

public class VentanaListaArticulosSencilla extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
	private ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_1;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Lanza la ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaArticulosSencilla frame = new VentanaListaArticulosSencilla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana.
	 */
	public VentanaListaArticulosSencilla() {

		cargaArticulos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() - 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Listados de artículos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane);

		// Creación y carga de la primera tabla (películas)
		String col[] = {"Nombre", "Sinopsis", "Género", "Fecha de estreno", "Puntuación"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for(int i = 0; i < peliculas.size(); i++) {
			Object[] data = {peliculas.get(i).getNombre(), peliculas.get(i).getSinopsis(),
					peliculas.get(i).getGenero(), peliculas.get(i).getFecha_estr(),
					peliculas.get(i).getPuntuacion()};
			tableModel.addRow(data);
		}
		table = new JTable(tableModel);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);

		// Creación y carga de la segunda tabla (videojuegos)
		String col2[] = {"Nombre", "Descripción", "Categoria", "Fecha de lanzamiento", "Puntuación"};
		DefaultTableModel tableModel2 = new DefaultTableModel(col2, 0);
		for(int i = 0; i < videojuegos.size(); i++) {
			Object[] data = {videojuegos.get(i).getNombre(), videojuegos.get(i).getDescripcion(),
					videojuegos.get(i).getCategoria(), videojuegos.get(i).getFecha_lan(),
					videojuegos.get(i).getPuntuacion()};
			tableModel2.addRow(data);
		}
		table_1 = new JTable(tableModel2);
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);
	}

	// Método que carga datos de prueba aleatoriamente en los ArrayLists
	public void cargaArticulos() {
		Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2);
		Pelicula p2 = new Pelicula("Harry Potter", "Sinopsis Harry Potter", "Ciencia Ficción", "01/07/2002", 4.5);
		Pelicula p3 = new Pelicula("Star Wars II", "Sinopsis Star Wars II", "Ciencia Ficción", "24/02/2006", 10);

		Random random = new Random();
		int r;
		int j = 0;
		while(j < 100) {
			r = random.nextInt(3);
			if(r == 0) {
				peliculas.add(p1);
			} else if(r == 1) {
				peliculas.add(p2);
			} else {
				peliculas.add(p3);
			}
			j++;
		}


		Videojuego v1 = new Videojuego("GTA V", "Descripción GTA V", "Acción", "20/09/2014", 9);
		Videojuego v2 = new Videojuego("Fallout: New Vegas", "Descrición Fallout", "Aventura", "15/03/2012", 6.25);
		Videojuego v3 = new Videojuego("Dark Souls III", "Descripción Dark Souls", "Aventura", "10/04/2016", 7.5);
	
		j = 0;
		while(j < 100) {
			 r = random.nextInt(3);
			if(r == 0) {
				videojuegos.add(v1);
			} else if(r == 1) {
				videojuegos.add(v2);
			} else {
				videojuegos.add(v3);
			}
			j++;
		}
	}

}
