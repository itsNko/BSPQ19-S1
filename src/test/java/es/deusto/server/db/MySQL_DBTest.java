package es.deusto.server.db;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

public class MySQL_DBTest {

	private IDAO db;
	
	private String datosNuevos = "NombreCompleto;Apellido1 Apellido2;1111111A;Nueva Direccion";
	
	private Socio s = new Socio("Test1", "12345678A", "Test", "Uno Nuevo", "Direccion Test1", 20.25, "imagenTest.png");

//	private Articulo art1 = new Pelicula("Los vengadores", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
//	private Articulo art2 = new Pelicula("Harry Potter",5, "Descripcion de Harry Potter", "Acción","29/01/2009", 9, "harryPotter.jpg",0);
//	private Articulo art3 = new Pelicula("Star Wars I", 6.25, "Descripcion de Star Wars I", "Ciencia ficción","13/06/2010", 9, "starWars.jpg",0);
//
//	private Articulo art4 = new Videojuego("Sonic", 5, "Descripcion de Sonic", "Plataformas","10/02/2004", 7, "sonic.JPG",0);
//	private Articulo art5 = new Videojuego("Mario Bros", 4.75, "Descripcion de Mario", "Aventura","31/03/2008", 8.5, "mario.jpg",0);
//	private Articulo art6 = new Videojuego("GTA V", 7, "Descripcion de GTA V", "Acción","20/03/2015", 9, "GTAV.jpg",0);

	private Pelicula test1 = new Pelicula("Test1", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
	private Pelicula test2 = new Pelicula("Test2", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
	
	private Socio socioUpdates;
	
	@BeforeClass
	public static void init() {
		IDAO db = new MySQL_DB();
		Socio socioUpdates = new Socio("Test7", "12345678A", "Test", "Dos Nuevo", "Direccion Test", 20.25, "imagenTest.png");
		db.insertarSocio(socioUpdates);
	}
	
	@Before
	public void setUp() {
		db = new MySQL_DB();
		socioUpdates = db.selectSocio("Test7");
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
	
//	public void testListadoArticulosMal() {
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
	
	@Test
	public void testUpdateDatosSocioBien() {
		socioUpdates = db.selectSocio("Test7");
		boolean result = db.updateDatosSocio(socioUpdates.getNombre(), datosNuevos);
		
		boolean result2 = false;
		Socio s = db.selectSocio(socioUpdates.getNombre());
		if (s.getNombreCompleto().equals("NombreCompleto") && s.getApellidos().equals("Apellido1 Apellido2") 
				&& s.getPassword().equals("1111111A") && s.getDireccion().equals("Nueva Direccion")) {
			result2 = true;
		}
		
		assertTrue(result);
		assertTrue(result2);
	}
	
	@Test
	public void testUpdateDatosSocioPrimerIf() {
		String datosNuevos = " ;Apellido1 Apellido2;1111111A;Nueva Direccion";
		boolean result = db.updateDatosSocio(socioUpdates.getNombre(), datosNuevos);
		
		boolean result2 = false;
		Socio s = db.selectSocio(socioUpdates.getNombre());
		if (s.getNombreCompleto().equals(socioUpdates.getNombreCompleto()) && s.getApellidos().equals("Apellido1 Apellido2") 
				&& s.getPassword().equals("1111111A") && s.getDireccion().equals("Nueva Direccion")) {
			result2 = true;
		}
		
		assertTrue(result);
		assertTrue(result2);
	}
	
	@Test
	public void testUpdateDatosSocioSegundoIf() {
		String datosNuevos = "NombreCompleto; ;1111111A;Nueva Direccion";
		boolean result = db.updateDatosSocio(socioUpdates.getNombre(), datosNuevos);
		
		boolean result2 = false;
		Socio s = db.selectSocio(socioUpdates.getNombre());
		if (s.getNombreCompleto().equals("NombreCompleto") && s.getApellidos().equals(socioUpdates.getApellidos()) 
				&& s.getPassword().equals("1111111A") && s.getDireccion().equals("Nueva Direccion")) {
			result2 = true;
		}
		
		assertTrue(result);
		assertTrue(result2);
	}
	
	@Test
	public void testUpdateDatosSocioTercerIf() {
		String datosNuevos = "NombreCompleto;Apellido1 Apellido2; ;Nueva Direccion";
		boolean result = db.updateDatosSocio(socioUpdates.getNombre(), datosNuevos);
		
		boolean result2 = false;
		Socio s = db.selectSocio(socioUpdates.getNombre());
		if (s.getNombreCompleto().equals("NombreCompleto") && s.getApellidos().equals("Apellido1 Apellido2") 
				&& s.getPassword().equals(socioUpdates.getPassword()) && s.getDireccion().equals("Nueva Direccion")) {
			result2 = true;
		}
		
		assertTrue(result);
		assertTrue(result2);
	}
	
	@Test
	public void testUpdateDatosSocioCuartoIf() {
		String datosNuevos = "NombreCompleto;Apellido1 Apellido2;1111111A; ";
		boolean result = db.updateDatosSocio(socioUpdates.getNombre(), datosNuevos);
		
		boolean result2 = false;
		Socio s = db.selectSocio(socioUpdates.getNombre());
		if (s.getNombreCompleto().equals("NombreCompleto") && s.getApellidos().equals("Apellido1 Apellido2") 
				&& s.getPassword().equals("1111111A") && s.getDireccion().equals(socioUpdates.getDireccion())) {
			result2 = true;
		}
		
		assertTrue(result);
		assertTrue(result2);
	}
	
	@Test
	public void testBloquearMaquinaBien() {
		boolean result = db.bloquearMaquina(socioUpdates.getNombre());
		Socio t = db.selectSocio(socioUpdates.getNombre());
		
		assertTrue(result);
		assertTrue(t.isBloquearMaquina());
	}
	
	@Test
	public void testBloquearMaquinaBien2() {
		Socio t = new Socio("Test8", "12345678A", "Test", "Dos Nuevo", "Direccion Test", 20.25, "imagenTest.png");
		t.setBloquearMaquina(true);
		db.insertarSocio(t);
		boolean result = db.bloquearMaquina(t.getNombre());
		Socio t2 = db.selectSocio(t.getNombre());
		
		assertTrue(result);
		assertFalse(t2.isBloquearMaquina());
	}
	
	//	@After
	//	public void tearDown() {
	//		db.deleteSocio(s);
	//	}

}