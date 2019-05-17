package es.deusto.server.dto;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class VideojuegoDTOTest {

	VideojuegoDTO v;
	@Before
	public void setUp() {
		v = new VideojuegoDTO("nombre", 0.0, "descripcion", "categoria", "fechaLan", 0, "caratula", 0.0);
	}
	
	@Test
	public void constructorTest()
	{
		VideojuegoDTO v2 = new VideojuegoDTO("nombre", 0, "descripcion", "categoria", "fechaLan", 0, "caratula", 0);
		assertNotNull(v2);
	}
	
	@Test
	public void getDescripcionTest()
	{
		assertEquals(v.getDescripcion(), "descripcion");
	}
	
	@Test
	public void setDescripcionTest()
	{
		v.setDescripcion("test");
		assertEquals(v.getDescripcion(), "test");
	}
	
	@Test
	public void getCategoriaTest()
	{
		assertEquals(v.getCategoria(), "categoria");
	}
	
	@Test
	public void setCategoriaTest()
	{
		v.setCategoria("test");
		assertEquals(v.getCategoria(), "test");
	}
	
	@Test
	public void getFechaLanTest()
	{
		assertEquals(v.getFecha_lan(), "fechaLan");
	}
	
	@Test
	public void setFechaLanTest()
	{
		v.setFecha_lan("test");
		assertEquals(v.getFecha_lan(), "test");
	}
	
	@Test
	public void getPuntuacionTest()
	{
		assertEquals(v.getPuntuacion(), 0.0, 0.00001);
	}
	
	@Test
	public void setPuntuacionTest()
	{
		v.setPuntuacion(2.0);
		assertEquals(v.getPuntuacion(), 2.0,0.00001);
	}
	
	@Test
	public void getClassNameTest()
	{
		assertEquals(v.getClassName(), "VideojuegoDTO");
	}
}
