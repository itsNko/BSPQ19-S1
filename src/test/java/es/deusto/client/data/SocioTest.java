package es.deusto.client.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class SocioTest {

	private Socio s;
	private ArrayList<Alquiler> alquileres;
	
//	public static junit.framework.Test suite() {
//		return new JUnit4TestAdapter(SocioTest.class);
//	}
	
	@Before
	public void setUp() { 
		s = new Socio("Lucas", "12345678A", "Lucas", "Montolla Molinero", "Aveninda Miranda Num.1 3C",  7.5, "imagePrueba.jpg");
		s.setBloquearMaquina(false);
		alquileres = new ArrayList<Alquiler>();
		alquileres.add(new Alquiler(new Pelicula("Pelicula A", 5, "Sinopsis", "Genero", "01/01/2019", 5.5, "caratula.jpg",0), 5, "01/03/2019", "10/03/2019", true, "Pelicula A"));
		s.setAlquileres(alquileres);
	}
	
//	@Rule 
//	public ContiPerfRule i = new ContiPerfRule();
//	@Test 
//    @PerfTest(invocations = 100, threads = 5)
//	@Required(max = 1500, average = 1000, throughput = 5)
	public void testGetNombre() {
		assertEquals("Lucas", s.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		s.setNombre("Pablo");
		assertEquals("Pablo", s.getNombre());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("12345678A", s.getPassword());
	}

	@Test
	public void testSetPassword() {
		s.setPassword("87654321A");
		assertEquals("87654321A", s.getPassword());
	}
	
	@Test
	public void testGetNombreCompleto() {
		assertEquals("Lucas", s.getNombreCompleto());
	}
	
	@Test
	public void testSetNombreCompleto() {
		s.setNombreCompleto("Lu");
		assertEquals("Lu", s.getNombreCompleto());
	}
	
	@Test
	public void testGetApellidos() {
		assertEquals("Montolla Molinero", s.getApellidos());
	}
	
	@Test
	public void testSetApellidos() {
		s.setApellidos("Montolla");
		assertEquals("Montolla", s.getApellidos());
	}
	
	@Test
	public void testGetDireccion() {
		assertEquals("Aveninda Miranda Num.1 3C", s.getDireccion());
	}
	
	@Test
	public void testSetDireccion() {
		s.setDireccion("Nueva direccion");
		assertEquals("Nueva direccion", s.getDireccion());
	}
	
	@Test
	public void testGetMonedero() {
		assertEquals(7.5, s.getMonedero(), 0);
	}
	
	@Test
	public void testSetMonedero() {
		s.setMonedero(10.75);
		assertEquals(10.75, s.getMonedero(), 0);
	}
	
	@Test
	public void testGetImagen() {
		assertEquals("imagePrueba.jpg", s.getImagen());
	}
	
	@Test
	public void testSetImagen() {
		s.setImagen("nuevaImagen.png");
		assertEquals("nuevaImagen.png", s.getImagen());
	}

	@Test
	public void testGetAlquileres() {
		assertEquals(alquileres, s.getAlquileres());
	}

	@Test
	public void testSetAlquileres() {
		s.setAlquileres(alquileres);
		assertEquals(alquileres, s.getAlquileres());
	}
	
	@Test
	public void testIsBloquearMaquina() {
		assertFalse(s.isBloquearMaquina());
	}
	
	@Test
	public void testSetBloquearMaquina() {
		s.setBloquearMaquina(true);
		assertTrue(s.isBloquearMaquina());
	}
	
	@After
	public void tearDown() {
		s.getAlquileres().clear();
	}
}
