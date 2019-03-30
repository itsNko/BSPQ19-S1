package es.deusto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class VentanaConfirmación extends JFrame {

	
	private JPanel contentPane;
	private JTable tabla;
	private ArrayList<Alquiler> articulos = new ArrayList<Alquiler>();
	//Socio de prueba
	private Socio tester = new Socio("Pablo", "P123", 80);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfirmación frame = new VentanaConfirmación();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaConfirmación() {
		
		cargaArticulos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("CONFIRMACIÓN DE COMPRA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		
		JButton bConfirmar = new JButton("Confirmar");
		bConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Para mas acciones
				
			}
		});
		panelBotones.add(bConfirmar);
		
		String col[] = {"Artículo", "Precio", "Monedero" , "Fecha devolución"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		
		for(int i = 0; i < articulos.size(); i++) {
			Object[] data = {articulos.get(i).getAlquilado().getNombre(),
					articulos.get(i).getCoste(), tester.getMonedero(), articulos.get(i).getFecha_fin()};
			tableModel.addRow(data);
			
		}
		
		tabla = new JTable(tableModel);
		tabla.setEnabled(false);
		scrollPane.setViewportView(tabla);
	}
	
	// Método que carga datos de prueba en el ArrayList
		public void cargaArticulos() {
			Pelicula p1 = new Pelicula("Los Vengadores", "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2);
			Videojuego v1 = new Videojuego("GTA V", "Descripción GTA V", "Acción", "20/09/2014", 9);

			
			Alquiler a1 = new Alquiler(p1, 20.25 , "20/03/2019", "30/03/2019", false);
			Alquiler a2 = new Alquiler(v1, 50 , "15/02/2019", "03/04/2019", false);
			
			articulos.add(a1);
			articulos.add(a2);

			
		}
	
}
