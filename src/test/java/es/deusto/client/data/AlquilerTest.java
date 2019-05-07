package es.deusto.client.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlquilerTest {

	Alquiler alq;
	Articulo a;
	
	@Before
	public void setUp() {
		a = new Pelicula("Pelicula A", 3.5, "Sinopsis", "Género", "20/10/2000", 6, "caratula.jpg", 0);
		alq = new Alquiler(a, a.getPrecio(), "15/10/2014", "20/10/2014", true, a.getNombre() + "-Pelicula");
	}
	
	@Test
	public void getNombreArticuloTest() {
		assertEquals("Pelicula A-Pelicula", alq.getNombreArticulo());

	}
	
	@Test
	public void setNombreArticuloTest() {
		alq.setNombreArticulo("Nuevo-Pelicula");
		assertEquals("Nuevo-Pelicula", alq.getNombreArticulo());
	}
	
	@Test
	public void getAlquiladoTest() {
		assertEquals(a, alq.getAlquilado());
	}
	
	@Test
	public void setAlquiladoTest() {
		Articulo a = new Videojuego("Pelicula B", 4, "Sinopsis 2", "Género", "20/10/2012", 8, "caratula2.jpg",0);
		alq.setAlquilado(a);
		assertEquals(a, alq.getAlquilado());
	}
	
	@Test
	public void getCosteTest() {
		assertEquals(3.5, alq.getCoste(), 0);
	}
	
	@Test
	public void setCosteTest() {
		alq.setCoste(5);
		assertEquals(5, alq.getCoste(), 0);
	}
	
	@Test
	public void gestFechaInicioTest() {
		assertEquals("15/10/2014", alq.getFecha_inicio());
	}
	
	@Test
	public void setFechaInicioTest() {
		alq.setFecha_inicio("01/01/2015");
		assertEquals("01/01/2015", alq.getFecha_inicio());
	}
	
	@Test
	public void getFechaFinTest() {
		assertEquals("20/10/2014", alq.getFecha_fin());
	}
	
	@Test
	public void setFechaFinTest() {
		alq.setFecha_fin("10/01/2015");
		assertEquals("10/01/2015", alq.getFecha_fin());
	}
	
	@Test
	public void isEnCursoTest() {
		assertTrue(alq.isEnCurso());
	}
	
	@Test
	public void setEnCursoTest() {
		alq.setEnCurso(false);
		assertFalse(alq.isEnCurso());
	}
	
	@Test
	public void getValoracionTest() {
		assertEquals(alq.getValoracion(), 0.0, 0.0001);
	}
	
	@Test
	public void setValoracionTest() {
		alq.setValoracion(1.0);
		assertEquals(alq.hashCode(), 1.0, 0.0001);
	}

}
