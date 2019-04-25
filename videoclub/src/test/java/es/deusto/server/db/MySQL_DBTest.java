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
	
//	@After
//	public void tearDown() {
//		db.deleteSocio(s);
//	}
	
}
