package es.deusto.server.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

public class MySQL_DBTest {

	private IDAO db;

	Socio s = new Socio("Test1", "12345678A", "Test", "Uno Nuevo", "Direccion Test1", 20.25, "imagenTest.png");

	Articulo art1 = new Pelicula("Los vengadores", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
	Articulo art2 = new Pelicula("Harry Potter",5, "Descripcion de Harry Potter", "Acción","29/01/2009", 9, "harryPotter.jpg",0);
	Articulo art3 = new Pelicula("Star Wars I", 6.25, "Descripcion de Star Wars I", "Ciencia ficción","13/06/2010", 9, "starWars.jpg",0);

	Articulo art4 = new Videojuego("Sonic", 5, "Descripcion de Sonic", "Plataformas","10/02/2004", 7, "sonic.JPG",0);
	Articulo art5 = new Videojuego("Mario Bros", 4.75, "Descripcion de Mario", "Aventura","31/03/2008", 8.5, "mario.jpg",0);
	Articulo art6 = new Videojuego("GTA V", 7, "Descripcion de GTA V", "Acción","20/03/2015", 9, "GTAV.jpg",0);

	Pelicula test1 = new Pelicula("Prueba", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
	Pelicula test2 = new Pelicula("Prueba2", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
	
	@Before
	public void setUp() {
		db = new MySQL_DB();
	}

	@Test
	public void testInsertarSocioBien() {
		assertTrue(db.insertarSocio(s));
	}

	@Test
	public void testInsertarSocioMal() {
		Socio socio = new Socio("Test2", "12345678A", "Test", "Dos Nuevo", "Direccion Test2", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertFalse(db.insertarSocio(socio));
	}

	@Test
	public void testExisteSocioBien() {
		Socio socio = new Socio("Test3", "12345678A", "Test", "Tres Nuevo", "Direccion Test3", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertTrue(db.existeSocio(socio.getNombre()));
	}

	@Test
	public void testExisteSocioMal() {
		Socio socio = new Socio("Test4", "12345678A", "Test", "Cuatro Nuevo", "Direccion Test4", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertFalse(db.existeSocio("Test"));
	}

	@Test
	public void testInicioSesionBien() {
		Socio socio = new Socio("Test5", "12345678A", "Test", "Cinco Nuevo", "Direccion Test5", 20.25, "imagenTest.png");
		String nombreSocio = socio.getNombre(); String passSocio = socio.getPassword();
		double monederoSocio = socio.getMonedero(); String imagenSocio = socio.getImagen();
		db.insertarSocio(socio);

		Socio recuperado = db.inicioSesion(nombreSocio, passSocio);

		boolean igual = false;
		if(nombreSocio.equals(recuperado.getNombre()) && passSocio.equals(recuperado.getPassword())
				&& monederoSocio == recuperado.getMonedero() && imagenSocio.equals(recuperado.getImagen())) {
			igual = true;
		}

		assertTrue(igual);

	}

	@Test
	public void testInicioSesionMal() {
		Socio socio = new Socio("Test6", "12345678A", "Test", "Seis Nuevo", "Direccion Test6", 20.25, "imagenTest.png");
		String nombreSocio = "Prueba"; String passSocio = "1234";

		db.insertarSocio(socio);
		Socio recuperado = db.inicioSesion(nombreSocio, passSocio);

		boolean distinto = false;
		if(recuperado.getNombre().equals("")) {
			distinto = true;
		}

		assertTrue(distinto);

	}

//	@Test
//	public void testListadoArticulosBien() {
//		List<Articulo> articulos = new ArrayList<Articulo>();
//		articulos.add(art1); articulos.add(art2); articulos.add(art3);
//		articulos.add(art4); articulos.add(art5); articulos.add(art6);
//
//		List<Articulo> articulosDB = db.listadoArticulos();
//
//		int cont = 0;
//		for(Articulo art : articulosDB) {
//			for(Articulo a : articulos) {
//				if(art.getNombre().equals(a.getNombre())
//						&& art.getPrecio() == a.getPrecio()
//						&& art.getCaratula().equals(a.getCaratula())
//						&& art.getUnidades() == a.getUnidades()
//						&& art.getDescuento() == a.getDescuento()) {
//					cont++;
//				}
//			}
//		}
//
//		boolean igual = false;
//		if(cont == articulosDB.size()) {
//			igual = true;
//		}
//
//		assertTrue(igual);
//	}
	
	@Test
	public void testUpdatePrecio() {
		db.insertarPelicula(test1);
		double precio = 3;
		assertTrue(db.updatePrecio(test1.getNombre(), precio));

	}

	@Test
	public void testUpdateDescuento() {
		db.insertarPelicula(test2);
		double descuento = 20;
		assertTrue(db.updateDescuento(test2.getNombre(), descuento));

	}
	
	//	public void testListadoArticulosMal() {
	//	}

	//	@After
	//	public void tearDown() {
	//		db.deleteSocio(s);
	//	}

}