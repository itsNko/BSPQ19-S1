package es.deusto.server.db;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import es.deusto.client.data.Socio;

public class MySQL_DB_Test {

	@Mock
	private IDAO db;
	
	private Socio s;

	@Before
	public void setUp() {
		s = new Socio("Test1", "12345678A", 20.25, "imagenTest.png");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInsertarSocioBien() {
		when(db.insertarSocio(s)).thenReturn(true);
		when(db.existeSocio(s.getNombre())).thenReturn(true);
		
		assertTrue(db.insertarSocio(s) && db.existeSocio(s.getNombre()));
	}
	
}
