package es.deusto.server.db;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import es.deusto.client.data.Socio;

public class MySQL_DBTest {

	@Mock
	private IDAO db;
	
	private Socio s;

	@Before
	public void setUp() {
		db = new MySQL_DB();
		s = new Socio("Test1", "12345678A", 20.25, "imagenTest.png");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInsertarSocioBien() {
		when(db.insertarSocio(s)).thenReturn(true);
		boolean b = db.insertarSocio(s);
		assertTrue(b);
	}
	
	@Test
	public void testInsertarSocioMal() {
		when(db.insertarSocio(s)).thenReturn(false);
		boolean b = db.insertarSocio(s);
		assertFalse(b);
	}
	
}
