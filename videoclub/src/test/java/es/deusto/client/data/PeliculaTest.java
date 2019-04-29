package es.deusto.client.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {

	private Pelicula p;
	
	@Before
	public void setUp() { 
		p = new Pelicula("Los vengadores", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg", 0);
	}
	
	@Test
	public void getSinopsisTest() {
		assertEquals("Descripcion de Los Vengadores", p.getSinopsis());
	}
	
	@Test
	public void setSinopsisTest() {
		p.setSinopsis("Sinopsis");
		assertEquals("Sinopsis", p.getSinopsis());
	}
	
	@Test
	public void getGeneroTest() {
		assertEquals("Acción", p.getGenero());
	}
	
	@Test
	public void setGeneroTest() {
		p.setGenero("Genero");
		assertEquals("Genero", p.getGenero());
	}
	
	@Test
	public void getFechaEstrenoTest() {
		assertEquals("20/09/2014", p.getFecha_estr());
	}
	
	@Test
	public void setFechaEstrenoTest() {
		p.setFecha_estr("21/09/2014");
		assertEquals("21/09/2014", p.getFecha_estr());
	}
	
	@Test
	public void getPuntuacionTest() {
		assertEquals(9, p.getPuntuacion(), 0);
	}
	
	@Test
	public void setPuntuacionTest() {
		p.setPuntuacion(7.5);
		assertEquals(7.5, p.getPuntuacion(), 0);
	}
	
}
