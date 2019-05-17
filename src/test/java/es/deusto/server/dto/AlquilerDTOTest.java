package es.deusto.server.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AlquilerDTOTest {
	
	AlquilerDTO a;
	ArticuloDTO alquilado;
	ArticuloDTO alquilado2;
	double coste = 0.0;
	String fecha_inicio = "fechaInicio";
	String fecha_fin = "fechaFin";
	boolean enCurso = false;	
	
	@Before
	public void setUp() {
		alquilado = new PeliculaDTO("",0.0, "","", "", 0.0,"", 0.0);
		a = new AlquilerDTO(alquilado, coste, fecha_inicio, fecha_fin, enCurso);
	}
	
	@Test
	public void constructorTest()
	{
		AlquilerDTO alquiler = new AlquilerDTO(alquilado, 0.0, "", "",false);
		assertNotNull(alquiler);
	}
	
	@Test
	public void getAlquiladoTest()
	{
		assertNotNull(a.getAlquilado());
	}
	
	@Test
	public void setAlquiladoTest()
	{
		alquilado2 = new PeliculaDTO("test",0.0, "","", "", 0.0,"", 0.0);
		a.setAlquilado(alquilado2);
		assertEquals(a.getAlquilado().getNombre(), "test");

	}
	
	@Test
	public void getCosteTest()
	{
		assertEquals(a.getCoste(), 0.0, 0.0001);
	}
	
	@Test
	public void setCosteTest()
	{
		a.setCoste(2.0);
		assertEquals(a.getCoste(), 2.0, 0.00001);
	}
	
	@Test
	public void getFechaInicioTest()
	{
		assertEquals(a.getFecha_inicio(), "fechaInicio");
	}
	
	@Test
	public void setFechaInicioTest()
	{
		a.setFecha_inicio("test");
		assertEquals(a.getFecha_inicio(), "test");
	}
	
	@Test
	public void getFechaFinTest()
	{	
		assertEquals(a.getFecha_fin(), "fechaFin");
	}
	
	@Test
	public void setFechaFinTest()
	{
		a.setFecha_fin("test");
		assertEquals(a.getFecha_fin(), "test");
	}
	
	@Test
	public void isEnCursoTest()
	{
		assertFalse(a.isEnCurso());
	}
	
	@Test
	public void setEnCursoTest()
	{
		a.setEnCurso(true);
		assertTrue(a.isEnCurso());
	}
	
	
}
