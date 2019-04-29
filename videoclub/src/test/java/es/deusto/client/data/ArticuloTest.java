package es.deusto.client.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArticuloTest {

	private Articulo v;
	
	@Before
	public void setUp() { 
		v = new Videojuego("GTA", 7.5, "descripcion","categoria","fechalan",3.0,"caratula", 0.0);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("GTA", v.getNombre());
	}
	
	@Test
	public void testGetCaratula() {
		assertEquals("caratula", v.getCaratula());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(7.5, v.getPrecio(), 0.0001);
	}
	
	@Test
	public void testSetNombre() {
		v.setNombre("nombre");
		assertEquals("nombre", v.getNombre());
	}
	
	@Test
	public void testSetCaratula() {
		v.setCaratula("test");
		assertEquals("test", v.getCaratula());
	}
	
	@Test
	public void testSetPrecio() {
		v.setPrecio(0.0);
		assertEquals(0.0, v.getPrecio(), 0.0001);
	}
	
	@Test
	public void testGetDescuento() {
		assertEquals(0.0, v.getDescuento(), 0);
	}
	
	@Test
	public void testSetDescuento() {
		v.setDescuento(15.0);
		assertEquals(15.0, v.getDescuento(), 0);
	}
	
	@Test
	public void testGetUnidades() {
		assertEquals(1, v.getUnidades());
	}
	
	@Test
	public void testSetUnidades() {
		v.setUnidades(10);
		assertEquals(10, v.getUnidades());
	}

}
