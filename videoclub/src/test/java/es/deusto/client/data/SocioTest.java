package es.deusto.client.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SocioTest {

	private Socio s;
	private ArrayList<Alquiler> alquileres;
	
	@Before
	public void setUp() { 
		s = new Socio("Lucas", "12345678A", 7.5, "imagePrueba.jpg");
		
		alquileres = new ArrayList<Alquiler>();
		alquileres.add(new Alquiler(new Pelicula("Pelicula A", 5, "Sinopsis", "Genero", "01/01/2019", 5.5, "caratula.jpg",0), 5, "01/03/2019", "10/03/2019", true, "Pelicula A"));
		s.setAlquileres(alquileres);
	}
	
	@Test
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
	
	@After
	public void tearDown() {
		s.getAlquileres().clear();
	}
}
