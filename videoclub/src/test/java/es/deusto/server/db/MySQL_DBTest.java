package es.deusto.server.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import es.deusto.client.data.Socio;

public class MySQL_DBTest {

	private IDAO db;
	private Socio s;

	@Before
	public void setUp() {
		db = new MySQL_DB();
		s = new Socio("Test1", "12345678A", 20.25, "imagenTest.png");
	}

	@Test
	public void testInsertarSocioBien() {
		assertTrue(db.insertarSocio(s));
	}

	@Test
	public void testInsertarSocioMal() {
			Socio socio = new Socio("Test4", "12345678A", 20.25, "imagenTest.png");
			db.insertarSocio(socio);
			assertFalse(db.insertarSocio(socio));
	}

	@Test
	public void testExisteSocioBien() {
		Socio socio = new Socio("Test2", "12345678A", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertTrue(db.existeSocio(socio.getNombre()));
	}
	
	@Test
	public void testExisteSocioMal() {
		Socio socio = new Socio("Test6", "12345678A", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertFalse(db.existeSocio("Test"));
	}

	@Test
	public void testInicioSesionBien() {
		Socio socio = new Socio("Test3", "12345678A", 20.25, "imagenTest.png");
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
		Socio socio = new Socio("Test5", "12345678A", 20.25, "imagenTest.png");
		String nombreSocio = "Prueba"; String passSocio = "1234";
		
		db.insertarSocio(socio);
		Socio recuperado = db.inicioSesion(nombreSocio, passSocio);
	
		boolean distinto = false;
		if(recuperado.getNombre().equals("")) {
			distinto = true;
		}

		assertTrue(distinto);

	}
	
	//	@After
	//	public void tearDown() {
	//		db.deleteSocio(s);
	//	}

}