package es.deusto.server.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ArticuloDTOTest {
	ArticuloDTO art;
	
	@Before
	public void setUp() {
		art = new VideojuegoDTO("nombre", 0, "descripcion", "categoria", "fechaLan", 0, "caratula", 0);
	}
	
	@Test
	public void constructorTest()
	{
		ArticuloDTO art2 = new VideojuegoDTO("nombre", 0.0, "descripcion", "categoria", "fechaLan", 0.0, "caratula", 0);
		assertNotNull(art2);
	}
	
	@Test
	public void getNombreTest()
	{
		assertEquals(art.getNombre(), "nombre");
	}
	
	@Test
	public void setNombreTest()
	{
		art.setNombre("test");
		assertEquals(art.getNombre(), "test");
	}
	
	@Test
	public void getCaratulaTest()
	{
		assertEquals(art.getCaratula(), "caratula");
	}
	
	@Test
	public void setCaratulaTest()
	{
		art.setCaratula("test");
		assertEquals(art.getCaratula(), "test");
	}
	
	@Test
	public void getPrecioTest()
	{
		assertEquals(art.getPrecio(), 0.0, 0.0001);
	}
	
	@Test
	public void setPrecioTest()
	{
		art.setPrecio(2.0);
		assertEquals(art.getPrecio(), 2.0, 0.00001);
	}
	
	@Test
	public void getDescuentoTest()
	{
		assertEquals(art.getDescuento(), 0.0, 0.0001);
	}
	
	@Test
	public void setDescuentoTest()
	{
		art.setDescuento(1.0);
		assertEquals(art.getDescuento(), 1.0,0.0001);
	}
	
	@Test
	public void getClassNameTest()
	{
		assertEquals(art.getClassName(), "VideojuegoDTO");
	}
}
