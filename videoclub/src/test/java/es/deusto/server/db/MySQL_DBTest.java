package es.deusto.server.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Alquiler;
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

	//	@Test
	//	public void testInsertarSocioMal() {
	//		boolean b = db.insertarSocio(s);
	//		assertFalse(b);
	//	}

	@Test
	public void testExisteSocio() {
		Socio socio = new Socio("Test2", "12345678A", 20.25, "imagenTest.png");
		db.insertarSocio(socio);
		assertTrue(db.existeSocio(socio.getNombre()));
	}

	@Test
	public void testInicioSesion() {
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
	//	@After
	//	public void tearDown() {
	//		db.deleteSocio(s);
	//	}

}
