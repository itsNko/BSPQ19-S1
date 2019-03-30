package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.data.Alquiler;
import es.deusto.data.Pelicula;
import es.deusto.data.Videojuego;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

public class VentanaAlquileres extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

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

	/**
	 * Crea la ventana.
	 */
	public VentanaAlquileres() {

		cargaArticulos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("HISTORIAL DE TUS ALQUILERES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("ALQUILERES EN CURSO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);

		JLabel lblNewLabel_2 = new JLabel("ALQUILERES DEVUELTOS/FINALIZADOS");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);

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

		table = new JTable(tableModel);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		table_1 = new JTable(tableModel2);
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
