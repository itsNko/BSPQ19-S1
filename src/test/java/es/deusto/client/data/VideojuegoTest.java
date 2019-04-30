package es.deusto.client.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideojuegoTest {

	private Videojuego v;

	@Before
	public void setUp() { 
		v = new Videojuego("Sonic", 5, "Descripcion de Sonic", "Plataformas","10/02/2004", 7, "sonic.JPG",0);
	}

	@Test
	public void getDescripcionTest() {
		assertEquals("Descripcion de Sonic", v.getDescripcion());
	}

	@Test
	public void setDescripcionTest() {
		v.setDescripcion("Descripcion");
		assertEquals("Descripcion", v.getDescripcion());
	}

	@Test
	public void getCategoriaTest() {
		assertEquals("Plataformas", v.getCategoria());
	}

	@Test
	public void setCategoriaTest() {
		v.setCategoria("Plt");
		assertEquals("Plt", v.getCategoria());
	}

	@Test
	public void getFechaLanzamientoTest() {
		assertEquals("10/02/2004", v.getFecha_lan());
	}

	@Test
	public void setFechaLanzamientoTest() {
		v.setFecha_lan("21/09/2014");
		assertEquals("21/09/2014", v.getFecha_lan());
	}

	@Test
	public void getPuntuacionTest() {
		assertEquals(7, v.getPuntuacion(), 0);
	}

	@Test
	public void setPuntuacionTest() {
		v.setPuntuacion(7.5);
		assertEquals(7.5, v.getPuntuacion(), 0);
	}

}
