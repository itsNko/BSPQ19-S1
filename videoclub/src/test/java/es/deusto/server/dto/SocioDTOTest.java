package es.deusto.server.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;

public class SocioDTOTest {

	private SocioDTO socio;
	private String nombre = "nombre";
	private String password = "password";
	private double monedero = 0.0;
	private String imagen = "imagen";
	private List<Alquiler> lista;
	private Alquiler alquiler;
	private Articulo alquilado;
	private double coste = 0.0;
	private String fecha_inicio = "fechaInicio";
	private String fecha_fin = "fechaFin";
	private boolean enCurso = false;
	private String nombreArticulo = "nombreArticulo";
	
	@Before
	public void setUp() {
		socio = new SocioDTO(nombre, password, monedero, imagen);
		alquiler = new Alquiler(alquilado, coste, fecha_inicio, fecha_fin, enCurso, nombreArticulo);
		lista = new ArrayList<Alquiler>();
		lista.add(alquiler);
	}
	
	@Test
	public void constructorTest(){
		SocioDTO soc = new SocioDTO("","",0.0,"");
		assertNotNull(soc);
	}
	
	@Test
	public void getNombreTest() {
		assertEquals("nombre",socio.getNombre());

	}
	
	@Test
	public void setNombreTest() {
		socio.setNombre("Pepe");
		assertEquals("Pepe", socio.getNombre());
	}
	
	@Test
	public void getPasswordTest() {
		assertEquals("password", socio.getPassword());
	}
	
	@Test
	public void setPasswordTest() {
		socio.setPassword("contrasenya");
		assertEquals("contrasenya", socio.getPassword());
	}
	
	@Test
	public void getMonederoTest() {
		assertEquals(0.0, socio.getMonedero(), 0.00001);
	}
	
	@Test
	public void setMonederoTest() {
		socio.setMonedero(1.0);
		assertEquals(1.0, socio.getMonedero(), 0.00001);
	}
	
	@Test
	public void getImagenTest() {
		assertEquals("imagen", socio.getImagen());
	}
	
	@Test
	public void setImagenTest() {
		socio.setImagen("image");
		assertEquals("image", socio.getImagen());
	}
	
	@Test
	public void setAlquileresTest() {
		socio.setAlquileres(lista);
		assertEquals("nombreArticulo", socio.getAlquileres().get(0).getNombreArticulo());
	}


}
