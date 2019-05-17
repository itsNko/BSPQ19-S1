package es.deusto.server.dto;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class PeliculaDTOTest {

	PeliculaDTO p;
	
	@Before
	public void setUp() {
		p = new PeliculaDTO("nombre", 0.0, "sinopsis", "genero", "fechaEstreno", 0.0, "caratula", 0.0);
	}
	
	@Test
	public void constructorTest()
	{
		PeliculaDTO p2 = new PeliculaDTO("nombre", 0.0, "sinopsis", "genero", "fechaEstreno", 0.0, "caratula", 0.0);
		assertNotNull(p2);
	}
	
	@Test
	public void getSinopsisTest()
	{
		assertEquals(p.getSinopsis(), "sinopsis");
	}
	
	@Test
	public void setSinopsisTest()
	{
		p.setSinopsis("test");
		assertEquals(p.getSinopsis(), "test");
	}
	
	@Test
	public void getGeneroTest()
	{
		assertEquals(p.getGenero(), "genero");
	}
	
	@Test
	public void setGeneroTest()
	{
		p.setGenero("test");
		assertEquals(p.getGenero(), "test");
	}
	
	@Test
	public void getFechaEstrTest()
	{
		assertEquals(p.getFecha_estr(), "fechaEstreno");
	}
	
	@Test
	public void setFechaEstrTest()
	{
		p.setFecha_estr("test");
		assertEquals(p.getFecha_estr(), "test");
	}
	
	@Test
	public void getPuntuacionTest()
	{
		assertEquals(p.getPuntuacion(), 0.0, 0.00001);
	}
	
	@Test
	public void setPuntuacionTest()
	{
		p.setPuntuacion(2.0);
		assertEquals(p.getPuntuacion(), 2.0, 0.00001);
	}
	
	@Test
	public void getClassNameTest()
	{
		assertEquals(p.getClassName(), "PeliculaDTO");
	}
}
